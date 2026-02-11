package net.rk.longroads.entity.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.longroads.entity.blockentity.TLRBlockEntity;

public class RedRoadwaySign extends SignBlockEntity{
    public RedRoadwaySign(BlockPos pos, BlockState blockState) {
        super(TLRBlockEntity.RED_ROADWAY_SIGN.get(), pos, blockState);
    }
}
