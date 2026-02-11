package net.rk.longroads.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.rk.longroads.ThingamajigsLongRoads;

public class TLRBlockModel extends BlockModelProvider {
    public TLRBlockModel(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ThingamajigsLongRoads.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }

    private BlockModelBuilder allSidedBlock(DeferredBlock<Block> block, String textureLocation){
        return withExistingParent(block.getId().getPath(),
                ResourceLocation.parse("minecraft:block/cube_all"))
                .texture("all", ResourceLocation.parse(textureLocation));
    }
}
