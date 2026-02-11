package net.rk.longroads.entity.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.longroads.entity.blockentity.TLRBlockEntity;

public class BrownRoadwaySign extends SignBlockEntity{
    public BrownRoadwaySign(BlockPos pos, BlockState blockState) {
        super(TLRBlockEntity.BROWN_ROADWAY_SIGN.get(),pos, blockState);
    }
}
