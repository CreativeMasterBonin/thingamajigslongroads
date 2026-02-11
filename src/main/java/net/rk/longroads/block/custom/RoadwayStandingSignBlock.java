package net.rk.longroads.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.datagen.TTag;

public class RoadwayStandingSignBlock extends StandingSignBlock {
    public RoadwayStandingSignBlock(WoodType type, Properties properties) {
        super(type, properties);
    }

    @Override
    public boolean canSurvive(BlockState bs, LevelReader lr, BlockPos bp) {
        return lr.getBlockState(bp.below()).isSolid()
                || lr.getBlockState(bp.below()).is(TBlocks.STRAIGHT_POLE.get())
                || lr.getBlockState(bp.below()).is(TBlocks.PLUS_POLE.get())
                || lr.getBlockState(bp.below()).is(TBlocks.HOLDER_POLE.get())
                || lr.getBlockState(bp.below()).is(TBlocks.TL_CONNECTOR.get())
                || lr.getBlockState(bp.below()).is(TBlocks.T_POLE_B.get())
                || lr.getBlockState(bp.below()).is(TBlocks.VERTICAL_T_POLE.get())
                || lr.getBlockState(bp.below()).is(TBlocks.TRI_POLE_B.get())
                || lr.getBlockState(bp.below()).is(TTag.VERTICAL_REDSTONE_BLOCKS)
                || lr.getBlockState(bp.below()).is(TBlocks.VERTICAL_AXIS_POLE.get())
                || lr.getBlockState(bp.below()).is(TBlocks.THREE_WAY_POLE.get())
                || lr.getBlockState(bp.below()).is(TBlocks.ALL_WAY_POLE.get())
                || lr.getBlockState(bp.below()).is(TBlocks.CROSSWALK_BUTTON.get());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos bp, BlockState bs) {
        return null;
    }
}
