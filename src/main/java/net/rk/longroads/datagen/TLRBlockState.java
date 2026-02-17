package net.rk.longroads.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rk.longroads.ThingamajigsLongRoads;

public class TLRBlockState extends BlockStateProvider {
    public TLRBlockState(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ThingamajigsLongRoads.MODID, exFileHelper);
    }

    public BlockModelProvider blockModels;
    public ItemModelProvider itemModels;

    @Override
    public void registerStatesAndModels() {
        blockModels = models();
        itemModels = itemModels();

        
    }

    @Override
    public String getName() {
        return "TLRBlockState Provider";
    }
}
