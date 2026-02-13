package net.rk.longroads.datagen;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rk.longroads.ThingamajigsLongRoads;
import net.rk.longroads.block.TLRBlocks;
import net.rk.longroads.item.TLRItems;

public class TLRItemModel extends ItemModelProvider{
    public TLRItemModel(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ThingamajigsLongRoads.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "TLRItemModel Provider";
    }

    @Override
    protected void registerModels(){
        this.customSimpleTool(TLRItems.PAINT_BRUSH.asItem(),"paint_brush");
        this.customSimpleTool(TLRItems.WHITE_PAINT_BRUSH.asItem(),"white_paint_brush");
        this.customSimpleTool(TLRItems.YELLOW_PAINT_BRUSH.asItem(),"yellow_paint_brush");
        this.customSimpleTool(TLRItems.BLUE_PAINT_BRUSH.asItem(),"blue_paint_brush");
        this.customSimpleTool(TLRItems.SCRAPE_TOOL.asItem(),"scrape_tool");
        this.customSimpleItem(TLRItems.GREEN_ROADWAY_SIGN_ITEM.asItem(),"green_roadway_sign");
        this.customSimpleItem(TLRItems.RED_ROADWAY_SIGN_ITEM.asItem(),"red_roadway_sign");
        this.customSimpleItem(TLRItems.BLUE_ROADWAY_SIGN_ITEM.asItem(),"blue_roadway_sign");
        this.customSimpleItem(TLRItems.BROWN_ROADWAY_SIGN_ITEM.asItem(),"brown_roadway_sign");
        this.customSimpleItem(TLRItems.GREEN_HANGING_ROADWAY_SIGN_ITEM.asItem(),"green_hanging_roadway_sign");

        this.rotatableLayeredBlockItemModelFromMod(TLRBlocks.ASPHALT_LAYER.get());
        this.rotatableLayeredBlockItemModelFromMod(TLRBlocks.OK_ASPHALT_LAYER.get());
        this.rotatableLayeredBlockItemModelFromMod(TLRBlocks.MEDIOCRE_ASPHALT_LAYER.get());
        this.rotatableLayeredBlockItemModelFromMod(TLRBlocks.OLD_ASPHALT_LAYER.get());
        this.rotatableLayeredBlockItemModelFromMod(TLRBlocks.SIDEWALK_LAYER.get());
        this.rotatableLayeredBlockItemModelFromMod(TLRBlocks.SIDEWALK_LAYER_LEFT.get());
        this.rotatableLayeredBlockItemModelFromMod(TLRBlocks.SIDEWALK_LAYER_RIGHT.get());
        this.rotatableLayeredBlockItemModelFromMod(TLRBlocks.CRACKED_SIDEWALK_LAYER.get());
        this.rotatableLayeredBlockItemModelFromMod(TLRBlocks.CRACKED_SIDEWALK_LAYER_LEFT.get());
        this.rotatableLayeredBlockItemModelFromMod(TLRBlocks.CRACKED_SIDEWALK_LAYER_RIGHT.get());
        this.rotatableLayeredBlockItemModelFromMod(TLRBlocks.SECTIONED_SIDEWALK_LAYER.get());
        this.rotatableLayeredBlockItemModelFromMod(TLRBlocks.SECTIONED_SIDEWALK_LAYER_LEFT.get());
        this.rotatableLayeredBlockItemModelFromMod(TLRBlocks.SECTIONED_SIDEWALK_LAYER_RIGHT.get());
        this.rotatableLayeredBlockItemModelFromMod(TLRBlocks.BLOCKED_SIDEWALK_LAYER.get());
        this.rotatableLayeredBlockItemModelFromMod(TLRBlocks.BLOCKED_SIDEWALK_LAYER_LEFT.get());
        this.rotatableLayeredBlockItemModelFromMod(TLRBlocks.BLOCKED_SIDEWALK_LAYER_RIGHT.get());
    }

    // builtin anything isn't recognized with any namespace
    public ItemModelBuilder builtinEntityWithLighting(Item blockEntityBlockItem, BlockModel.GuiLight lightingType, String renderType){
        return withExistingParent(blockEntityBlockItem.toString(),
                ResourceLocation.parse("builtin/entity"))
                .guiLight(lightingType)
                .renderType(renderType);
    }

    // exclusively for use with layered rotatable block items
    private ItemModelBuilder rotatableLayeredBlockItemModelFromMod(Block layeredBlock){
        String source = layeredBlock.asItem().getDescriptionId().replaceAll("block.thingamajigslongroads.","");
        return withExistingParent(layeredBlock.asItem().toString(),
               ResourceLocation.fromNamespaceAndPath("thingamajigslongroads","block/" + source + "_2"));
    }

    private ItemModelBuilder defaultCustomSimpleItem(Block block1, String source){
        return withExistingParent(block1.asItem().toString(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath("thingamajigslongroads","item/" + source));
    }

    private ItemModelBuilder customSimpleItem(Item item, String source){
        return withExistingParent(item.toString(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath("thingamajigslongroads","item/" + source));
    }

    private ItemModelBuilder customSimpleTool(Item item, String source){
        return withExistingParent(item.toString(),
                ResourceLocation.withDefaultNamespace("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath("thingamajigslongroads","item/" + source));
    }

    private ItemModelBuilder defaultCustomSimpleItemOldBlock(Block block1, String source){
        return withExistingParent(block1.asItem().toString(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath("thingamajigs","block/" + source));
    }

    private ItemModelBuilder blockAll(Item item, String textureLocation){
        return withExistingParent(item.toString(),
                ResourceLocation.withDefaultNamespace("block/cube_all")).texture("all",
                ResourceLocation.parse(textureLocation));
    }
}
