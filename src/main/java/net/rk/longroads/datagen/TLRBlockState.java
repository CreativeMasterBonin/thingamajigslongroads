package net.rk.longroads.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rk.longroads.ThingamajigsLongRoads;

public class TLRBlockState extends BlockStateProvider {
    public TLRBlockState(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ThingamajigsLongRoads.MODID, exFileHelper);
    }

    @Override
    public void registerStatesAndModels() {

    }

    @Override
    public String getName() {
        return "TLRBlockState Provider";
    }
}
