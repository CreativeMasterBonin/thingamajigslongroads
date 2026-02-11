package net.rk.longroads.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.rk.thingamajigs.block.TBlocks;

public class RoadwayWallSignBlock extends WallSignBlock {
    public RoadwayWallSignBlock(WoodType type, Properties properties) {
        super(type, properties);
    }

    @Override
    public boolean canSurvive(BlockState bs, LevelReader lr, BlockPos bp) {
        return lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).isSolid()
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(TBlocks.STRAIGHT_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(TBlocks.STRAIGHT_HORIZONTAL_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(TBlocks.T_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(TBlocks.T_POLE_B.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(TBlocks.T_POLE_C.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(TBlocks.HOLDER_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(TBlocks.AXIS_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(TBlocks.TL_CONNECTOR.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(TBlocks.PLUS_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(TBlocks.L_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(TBlocks.L_ONLY_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(TBlocks.VERTICAL_T_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(TBlocks.TRI_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(TBlocks.TRI_POLE_B.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(TBlocks.VERTICAL_AXIS_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(TBlocks.THREE_WAY_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(TBlocks.ALL_WAY_POLE.get())
                || lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).is(TBlocks.T_HORZ_ONLY_POLE.get());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos bp, BlockState bs) {
        return null;
    }
}
