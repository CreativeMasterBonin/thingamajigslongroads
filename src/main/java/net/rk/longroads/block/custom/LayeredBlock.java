package net.rk.longroads.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LayeredBlock extends SnowLayerBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final VoxelShape[] LAYER_SHAPES =
            new VoxelShape[]{
                    Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                    Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
                    Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
                    Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
                    Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
                    Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
                    Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0)};


    public LayeredBlock(Properties p) {
        super(p.noOcclusion());
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(LAYERS,1)
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED,false));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(LAYERS)){
            case 1 -> {
                return LAYER_SHAPES[0];
            }
            case 2 -> {
                return LAYER_SHAPES[1];
            }
            case 3 -> {
                return LAYER_SHAPES[2];
            }
            case 4 -> {
                return LAYER_SHAPES[3];
            }
            case 5 -> {
                return LAYER_SHAPES[4];
            }
            case 6 -> {
                return LAYER_SHAPES[5];
            }
            case 7 -> {
                return LAYER_SHAPES[6];
            }
            default -> {
                return Shapes.block();
            }
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return false;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource random){}

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return state.getValue(WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING,WATERLOGGED);
    }

    @Override
    public void spawnAfterBreak(BlockState state, ServerLevel serverLevel, BlockPos pos, ItemStack stack, boolean bool1) {
        for(int item = 0; item < state.getValue(LAYERS); item++){
            serverLevel.addFreshEntity(new ItemEntity(serverLevel,pos.getX(),pos.getY() + 0.2D,pos.getZ(),new ItemStack(this.asItem())));
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        if(blockstate.is(this)){
            int i = blockstate.getValue(LAYERS);
            return blockstate.setValue(LAYERS, Math.min(8, i + 1))
                    .setValue(FACING, context.getHorizontalDirection().getOpposite())
                    .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        }
        else {
            return this.defaultBlockState()
                    .setValue(FACING,context.getHorizontalDirection().getOpposite())
                    .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        }
    }
}
