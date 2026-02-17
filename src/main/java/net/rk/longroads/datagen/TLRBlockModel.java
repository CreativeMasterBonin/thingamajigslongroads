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

        roadMarking("all_purple","purple/all_purple");
        roadMarking("diagonal_left_purple","purple/diagonal_left");
        roadMarking("dot_purple_corner","purple/dot_purple_corner");
        roadMarking("double_purple_line_corner","purple/double_purple_line_corner");
        roadMarking("double_purple","purple/double_purple");
        roadMarking("e_purple","purple/e_purple");
        roadMarking("e2_purple","purple/e2_purple");
        roadMarking("inverted_n_purple","purple/inverted_n");
        roadMarking("inverted_triangle_purple","purple/inverted_triangle");
        roadMarking("l_purple","purple/l_purple");
        roadMarking("left_lane_pass_purple","purple/left_lane_pass");
        roadMarking("plus_purple","purple/plus_purple");
        roadMarking("purple_corner_wide","purple/purple_corner_wide");
        roadMarking("purple_parking_line","purple/purple_parking_line");
        roadMarking("right_lane_pass_purple","purple/right_lane_pass");
        roadMarking("sidewalk_purple","purple/sidewalk_purple");
        roadMarking("single_purple","purple/single_purple");
        roadMarking("small_single_purple","purple/small_single_purple");
        roadMarking("square_purple","purple/square_purple");
        roadMarking("stop_line_purple","purple/stop_line");
        roadMarking("t_purple","purple/t_purple");
        roadMarking("t2_purple","purple/t2_purple");
        roadMarking("thick_purple_pl","purple/thick_purple_pl");
        roadMarking("triangle_purple","purple/triangle");
        roadMarking("u_turn_connect_purple","purple/u_turn_connect");

        roadMarking("triangle_white","white/triangle",true);
        roadMarking("inverted_triangle_white","white/inverted_triangle",true);
        roadMarking("triangle_yellow","yellow/triangle",true);
        roadMarking("inverted_triangle_yellow","yellow/inverted_triangle",true);

        roadMarkingWord("toll","worded/toll");
        roadMarkingWord("plaza","worded/plaza");
        roadMarkingMulticolored("ahead_purple","multi/ahead_purple");
        roadMarkingMulticolored("plaza_purple","multi/plaza_purple");
        roadMarkingMulticolored("stop_purple","multi/stop_purple");
        roadMarkingMulticolored("toll_purple","multi/toll_purple");
    }

    public BlockModelBuilder roadMarkingMulticolored(String fileName, String texture){
        String layerLocationBase = "thingamajigslongroads:bases/road_marking";
        return withExistingParent("block/road_markings/multicolored/" + fileName,
                layerLocationBase)
                .texture("all","thingamajigslongroads:block/pavement_markings/" + texture)
                .texture("particle","#all");
    }

    public BlockModelBuilder roadMarkingSymbol(String fileName, String texture){
        String layerLocationBase = "thingamajigslongroads:bases/road_marking";
        return withExistingParent("block/road_markings/symbols/" + fileName,
                layerLocationBase)
                .texture("all","thingamajigslongroads:block/pavement_markings/" + texture)
                .texture("particle","#all");
    }

    public BlockModelBuilder roadMarkingWord(String fileName, String texture){
        String layerLocationBase = "thingamajigslongroads:bases/road_marking";
        return withExistingParent("block/road_markings/words/" + fileName,
                layerLocationBase)
                .texture("all","thingamajigslongroads:block/pavement_markings/" + texture)
                .texture("particle","#all");
    }

    /**
     * A simple model maker method that creates a road marking model with a file name and texture specification
     * @param fileName The name of the file that the JSON will take
     * @param texture The road marking texture location that will replace the texture field in the model
     * @param useBlockLocation Whether to use the block/road_marking location instead of just the root location
     * @return The model builder that will assemble the model file
     */
    public BlockModelBuilder roadMarking(String fileName, String texture, boolean useBlockLocation){
        String layerLocationBase = "thingamajigslongroads:bases/road_marking";
        String addon = "block/road_markings/";
        addon = useBlockLocation ? "block/road_markings/" : "";
        return withExistingParent(addon + fileName,
                layerLocationBase)
                .texture("all","thingamajigslongroads:block/pavement_markings/" + texture)
                .texture("particle","#all");
    }

    /**
     * A simple model maker method that creates a road marking model with a file name and texture specification
     * @param fileName The name of the file that the JSON will take
     * @param texture The road marking texture location that will replace the texture field in the model
     * @return The model builder that will assemble the model file
     */
    public BlockModelBuilder roadMarking(String fileName, String texture){
        String layerLocationBase = "thingamajigslongroads:bases/road_marking";
        return withExistingParent(fileName,
                layerLocationBase)
                .texture("all","thingamajigslongroads:block/pavement_markings/" + texture)
                .texture("particle","#all");
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
