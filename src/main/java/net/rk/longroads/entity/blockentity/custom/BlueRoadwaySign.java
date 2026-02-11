package net.rk.longroads.entity.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.longroads.entity.blockentity.TLRBlockEntity;

public class BlueRoadwaySign extends SignBlockEntity{
    public BlueRoadwaySign(BlockPos pos, BlockState blockState) {
        super(TLRBlockEntity.BLUE_ROADWAY_SIGN.get(),pos, blockState);
    }
}
