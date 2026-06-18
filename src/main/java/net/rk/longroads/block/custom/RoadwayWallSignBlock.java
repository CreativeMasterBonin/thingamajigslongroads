package net.rk.longroads.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.rk.longroads.datagen.TLRTag;

public class RoadwayWallSignBlock extends WallSignBlock {
    public RoadwayWallSignBlock(WoodType type, Properties properties) {
        super(type, properties);
    }

    @Override
    public boolean canSurvive(BlockState bs, LevelReader lr, BlockPos bp) {
        return lr.getBlockState(bp.relative(bs.getValue(FACING).getOpposite())).isSolid() ||
                bs.is(TLRTag.SUPPORTS_ROAD_HANGING_SIGNS);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos bp, BlockState bs) {
        return null;
    }
}
