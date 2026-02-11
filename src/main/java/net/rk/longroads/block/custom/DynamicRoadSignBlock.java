package net.rk.longroads.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.longroads.ThingamajigsLongRoads;
import net.rk.longroads.entity.blockentity.TLRBlockEntity;
import net.rk.longroads.entity.blockentity.custom.DynamicRoadSignBE;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DynamicRoadSignBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final MapCodec<DynamicRoadSignBlock> CODEC = simpleCodec(DynamicRoadSignBlock::new);

    public static final VoxelShape NORTHSOUTH = Block.box(0, 0, 7, 16, 16, 9);
    public static final VoxelShape EASTWEST = Block.box(7, 0, 0, 9, 16, 16);

    public DynamicRoadSignBlock(Properties properties) {
        super(properties.sound(SoundType.LANTERN).pushReaction(PushReaction.BLOCK).strength(1f, 20f).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("block.thingamajigslongroads.road_sign.desc")
                .withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(Component.translatable("block.thingamajigslongroads.road_sign.desc_xtra")
                .withStyle(ChatFormatting.GREEN));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case NORTH, SOUTH -> {
                return NORTHSOUTH;
            }
            case EAST, WEST -> {
                return EASTWEST;
            }
            default -> {
                return Shapes.block();
            }
        }
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (ThingamajigsLongRoads.badFileAccessFlag) {
            level.removeBlock(pos, false);
        }
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new DynamicRoadSignBE(blockPos,blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level lvl, BlockState bs, BlockEntityType<T> bet) {
        return createTickerHelper(bet, TLRBlockEntity.DYNAMIC_ROAD_SIGN_BE.get(),
                lvl.isClientSide ? DynamicRoadSignBE::clientTick : DynamicRoadSignBE::serverTick);
    }
}
