package net.rk.longroads.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.rk.longroads.entity.blockentity.custom.GreenHangingRoadwaySign;

public class GreenHangingSignBlock extends CeilingHangingSignBlock {
    public GreenHangingSignBlock(WoodType type, Properties properties) {
        super(type, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GreenHangingRoadwaySign(pos,state);
    }
}
