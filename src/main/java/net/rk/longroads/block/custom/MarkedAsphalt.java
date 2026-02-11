package net.rk.longroads.block.custom;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class MarkedAsphalt extends Asphalt{
    public static final IntegerProperty AGE = IntegerProperty.create("age",0,3);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public MarkedAsphalt(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING,Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,AGE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(AGE,0);
    }
}
