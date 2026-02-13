package net.rk.longroads.datagen;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.rk.longroads.ThingamajigsLongRoads;
import net.rk.longroads.block.TLRBlocks;

public class TLRBlockModel extends BlockModelProvider {
    public TLRBlockModel(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ThingamajigsLongRoads.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "TLRBlockModel Provider";
    }

    @Override
    protected void registerModels(){
        for(int i = 0; i < 16; i++){
            if(i % 2 == 0 && i != 0){
                layeredBlockNoLayer(TLRBlocks.ASPHALT,"asphalt",i);
                layeredBlockNoLayer(TLRBlocks.ASPHALT_OK,"ok_asphalt",i);
                layeredBlockNoLayer(TLRBlocks.ASPHALT_MEDIOCRE,"mediocre_asphalt",i);
                layeredBlockNoLayer(TLRBlocks.ASPHALT_OLD,"old_asphalt",i);

                layeredBlockNoLayer(TLRBlocks.SIDEWALK,"sidewalk_new",i);
                layeredBlockNoLayerLeft(TLRBlocks.SIDEWALK,"sidewalk_new",i);
                layeredBlockNoLayerRight(TLRBlocks.SIDEWALK,"sidewalk_new",i);

                layeredBlockNoLayer(TLRBlocks.SIDEWALK_BLOCKED,"sidewalk_block",i);
                layeredBlockNoLayerLeft(TLRBlocks.SIDEWALK_BLOCKED,"sidewalk_block",i);
                layeredBlockNoLayerRight(TLRBlocks.SIDEWALK_BLOCKED,"sidewalk_block",i);

                layeredBlockNoLayer(TLRBlocks.SIDEWALK_SECTIONED,"sidewalk_new_sectioned",i);
                layeredBlockNoLayerLeft(TLRBlocks.SIDEWALK_SECTIONED,"sidewalk_new_sectioned",i);
                layeredBlockNoLayerRight(TLRBlocks.SIDEWALK_SECTIONED,"sidewalk_new_sectioned",i);

                layeredBlockNoLayer(TLRBlocks.SIDEWALK_CRACKED,"sidewalk_cracked",i);
                layeredBlockNoLayerLeft(TLRBlocks.SIDEWALK_CRACKED,"sidewalk_cracked",i);
                layeredBlockNoLayerRight(TLRBlocks.SIDEWALK_CRACKED,"sidewalk_cracked",i);
            }
        }
        layeredLeftFull(TLRBlocks.SIDEWALK,"sidewalk_new");
        layeredRightFull(TLRBlocks.SIDEWALK,"sidewalk_new");
        layeredLeftFull(TLRBlocks.SIDEWALK_BLOCKED,"sidewalk_block");
        layeredRightFull(TLRBlocks.SIDEWALK_BLOCKED,"sidewalk_block");
        layeredLeftFull(TLRBlocks.SIDEWALK_SECTIONED,"sidewalk_new_sectioned");
        layeredRightFull(TLRBlocks.SIDEWALK_SECTIONED,"sidewalk_new_sectioned");
        layeredLeftFull(TLRBlocks.SIDEWALK_CRACKED,"sidewalk_cracked");
        layeredRightFull(TLRBlocks.SIDEWALK_CRACKED,"sidewalk_cracked");
    }

    public BlockModelBuilder layeredRightFull(DeferredBlock<Block> block,String texture){
        String name = block.getId().toString();
        name = name + "_layer_right_full";
        String layerLocationBase = "thingamajigslongroads:bases/base_layer_right_full";
        return withExistingParent(name,
                layerLocationBase)
                .renderType("cutout")
                .texture("0","thingamajigslongroads:block/" + texture)
                .texture("1","minecraft:block/blast_furnace_top")
                .texture("particle","#0");
    }

    public BlockModelBuilder layeredLeftFull(DeferredBlock<Block> block,String texture){
        String name = block.getId().toString();
        name = name + "_layer_left_full";
        String layerLocationBase = "thingamajigslongroads:bases/base_layer_left_full";
        return withExistingParent(name,
                layerLocationBase)
                .renderType("cutout")
                .texture("0","thingamajigslongroads:block/" + texture)
                .texture("1","minecraft:block/blast_furnace_top")
                .texture("particle","#0");
    }

    public BlockModelBuilder layeredBlockNoLayerLeft(DeferredBlock<Block> block,String texture,int layer){
        String name = block.getId().toString();
        name = name + "_layer_left_" + layer;
        String layerLocationBase = "thingamajigslongroads:bases/base_layer_left_";
        return withExistingParent(name,
                layerLocationBase + layer)
                .renderType("cutout")
                .texture("0","thingamajigslongroads:block/" + texture)
                .texture("1","minecraft:block/blast_furnace_top")
                .texture("particle","#0");
    }

    public BlockModelBuilder layeredBlockNoLayerRight(DeferredBlock<Block> block,String texture,int layer){
        String name = block.getId().toString();
        name = name + "_layer_right_" + layer;
        String layerLocationBase = "thingamajigslongroads:bases/base_layer_right_";
        return withExistingParent(name,
                layerLocationBase + layer)
                .renderType("cutout")
                .texture("0","thingamajigslongroads:block/" + texture)
                .texture("1","minecraft:block/blast_furnace_top")
                .texture("particle","#0");
    }

    public BlockModelBuilder layeredBlockNoLayer(DeferredBlock<Block> block,String texture,int layer){
        String name = block.getId().toString();
        name = name + "_layer_" + layer;
        String layerLocationBase = "thingamajigslongroads:bases/base_layer_";
        return withExistingParent(name,
                layerLocationBase + layer)
                .renderType("cutout")
                .texture("0","thingamajigslongroads:block/" + texture)
                .texture("particle","#0");
    }

    public void layeredBlock(DeferredBlock<Block> block,String texture,int layer){
        String name = block.getRegisteredName().replaceAll("thingamajigslongroads.block.","");
        name = name + "_" + layer;
        String layerLocationBase = "thingamajigslongroads:bases/base_layer_";
        BlockModelBuilder layer_2 = withExistingParent(name,
                layerLocationBase + layer)
                .renderType("cutout")
                .texture("0","thingamajigslongroads:block/" + texture)
                .texture("particle","#0");
    }

    private BlockModelBuilder allSidedBlock(DeferredBlock<Block> block, String textureLocation){
        return withExistingParent(block.getId().getPath(),
                ResourceLocation.parse("minecraft:block/cube_all"))
                .texture("all", ResourceLocation.parse(textureLocation));
    }
}
