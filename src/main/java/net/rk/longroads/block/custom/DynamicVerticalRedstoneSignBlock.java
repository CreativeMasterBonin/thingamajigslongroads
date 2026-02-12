package net.rk.longroads.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.ticks.TickPriority;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.block.custom.VerticalPoleRedstone;
import net.rk.thingamajigs.datagen.TTag;
import org.jetbrains.annotations.Nullable;

public class DynamicVerticalRedstoneSignBlock extends DynamicRoadSignBlock{
    public static final BooleanProperty POWERED = VerticalPoleRedstone.POWERED;

    public DynamicVerticalRedstoneSignBlock(Properties properties){
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(POWERED,false));
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        this.neighborChangedOld(state,level,pos);
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POWERED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(POWERED,false);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        neighborChangedOld(state,level,pos);
    }

    public void neighborChangedOld(BlockState bs, Level lvl, BlockPos bp) {
        if (!lvl.isClientSide) {
            boolean allverticalredstoneblocks = lvl.getBlockState(bp.below()).is(TTag.VERTICAL_REDSTONE_BLOCKS);
            boolean allrrbells = lvl.getBlockState(bp.below()).is(TTag.RAILROAD_CROSSING_BELLS);
            boolean allrrbellsabove = lvl.getBlockState(bp.above()).is(TTag.RAILROAD_CROSSING_BELLS);
            if (allrrbellsabove) {
                if (allverticalredstoneblocks) {
                    if (lvl.getBlockState(bp.below()).getValue(POWERED)) {
                        lvl.setBlock(bp, bs.setValue(POWERED, true), 3);
                    } else if (!(Boolean)lvl.getBlockState(bp.below()).getValue(POWERED)) {
                        lvl.setBlock(bp, bs.setValue(POWERED, false), 3);
                    }
                } else {
                    lvl.setBlock(bp, bs.setValue(POWERED, false), 3);
                }

                return;
            }

            boolean cant4 = lvl.getBlockState(bp.above()).is(TTag.RR_CANTILEVERS);
            if (cant4) {
                if (allverticalredstoneblocks) {
                    if (lvl.getBlockState(bp.below()).getValue(POWERED)) {
                        lvl.setBlock(bp, bs.setValue(POWERED, true), 3);
                    } else if (!(Boolean)lvl.getBlockState(bp.below()).getValue(POWERED)) {
                        lvl.setBlock(bp, bs.setValue(POWERED, false), 3);
                    }
                } else if (lvl.getBlockState(bp.below()).is((Block) TBlocks.CROSSWALK_BUTTON.get())) {
                    if (lvl.getBlockState(bp.below()).getValue(POWERED)) {
                        lvl.setBlock(bp, bs.setValue(POWERED, true), 3);
                    } else {
                        lvl.setBlock(bp, bs.setValue(POWERED, false), 3);
                    }
                } else {
                    lvl.setBlock(bp, bs.setValue(POWERED, false), 3);
                }

                return;
            }

            if (!allrrbells) {
                if (allverticalredstoneblocks) {
                    if (lvl.getBlockState(bp.below()).getValue(POWERED)) {
                        lvl.setBlock(bp, bs.setValue(POWERED, true), 3);
                    } else if (!(Boolean)lvl.getBlockState(bp.below()).getValue(POWERED)) {
                        lvl.setBlock(bp, bs.setValue(POWERED, false), 3);
                    }
                } else if (lvl.hasNeighborSignal(bp)) {
                    lvl.setBlock(bp, bs.setValue(POWERED, true), 3);
                    lvl.scheduleTick(bp.above(), this, 3, TickPriority.LOW);
                } else if (!lvl.hasNeighborSignal(bp)) {
                    lvl.setBlock(bp, bs.setValue(POWERED, false), 3);
                    lvl.scheduleTick(bp.above(), this, 3, TickPriority.LOW);
                }
            }
        }

    }

    @Override
    public void tick(BlockState bs, ServerLevel sl, BlockPos bp, RandomSource rs) {
        sl.updateNeighborsAt(bp.above(), this);
    }
}
