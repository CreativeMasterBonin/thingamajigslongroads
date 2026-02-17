package net.rk.longroads.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.rk.longroads.block.TLRBlocks;
import net.rk.longroads.block.custom.*;
import net.rk.longroads.item.TLRDataComponents;

import java.util.List;

public class PurplePaintBrush extends AbstractPaintbrush{
    private final DataComponentType<Integer> road_marking = TLRDataComponents.ROAD_MARKING_PATTERN.get();

    public PurplePaintBrush(Properties properties) {
        super(properties);
    }

    @Override
    public void typeToName(int type) {
        switch(type){
            case 0: currentName = "tooltip.thingamajigs.paintbrush.pattern.full_cover";break;
            case 1: currentName = "tooltip.thingamajigs.paintbrush.pattern.diagonal";break;
            case 2: currentName = "tooltip.thingamajigs.paintbrush.pattern.thin_corner_dot";break;
            case 3: currentName = "tooltip.thingamajigs.paintbrush.pattern.center_double_line_turn";break;
            case 4: currentName = "tooltip.thingamajigs.paintbrush.pattern.center_double_line";break;
            case 5: currentName = "tooltip.thingamajigs.paintbrush.pattern.short_line";break;
            case 6: currentName = "tooltip.thingamajigs.paintbrush.pattern.short_t";break;
            case 7: currentName = "tooltip.thingamajigs.paintbrush.pattern.inverted_n";break;
            case 8: currentName = "tooltip.thingamajigs.paintbrush.pattern.inverted_triangle";break;
            case 9: currentName = "tooltip.thingamajigs.paintbrush.pattern.center_single_corner";break;
            case 10: currentName = "tooltip.thingamajigs.paintbrush.pattern.left_lane_pass";break;
            case 11: currentName = "tooltip.thingamajigs.paintbrush.pattern.plus";break;
            case 12: currentName = "tooltip.thingamajigs.paintbrush.pattern.thin_corner";break;
            case 13: currentName = "tooltip.thingamajigs.paintbrush.pattern.thin_parking_line";break;
            case 14: currentName = "tooltip.thingamajigs.paintbrush.pattern.right_lane_pass";break;
            case 15: currentName = "tooltip.thingamajigs.paintbrush.pattern.sidewalk_line";break;
            case 16: currentName = "tooltip.thingamajigs.paintbrush.pattern.center_single_line";break;
            case 17: currentName = "tooltip.thingamajigs.paintbrush.pattern.center_dashed_single_line";break;
            case 18: currentName = "tooltip.thingamajigs.paintbrush.pattern.square";break;
            case 19: currentName = "tooltip.thingamajigs.paintbrush.pattern.stop_line";break;
            case 20: currentName = "tooltip.thingamajigs.paintbrush.pattern.center_t";break;
            case 21: currentName = "tooltip.thingamajigs.paintbrush.pattern.long_t";break;
            case 22: currentName = "tooltip.thingamajigs.paintbrush.pattern.thick_parking_line";break;
            case 23: currentName = "tooltip.thingamajigs.paintbrush.pattern.triangle";break;
            case 24: currentName = "tooltip.thingamajigs.paintbrush.pattern.uturn_connector";break;
            default: currentName = "tooltip.thingamajigs.paintbrush.pattern.undefined";break;
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            LevelAccessor levelAccessor = pContext.getLevel();
            Level level = pContext.getLevel();
            BlockPos positionClicked = pContext.getClickedPos();
            BlockState blockState = level.getBlockState(positionClicked);
            Block blockClicked = level.getBlockState(positionClicked).getBlock();
            ItemStack stack = pContext.getItemInHand();
            Block marking = TLRBlocks.PURPLE_ROAD_MARKING.get();
            Player ply = pContext.getPlayer();
            int marking_type = 0;
            int length = 1;

            // direct painting of asphalt slabs
            if (blockClicked instanceof AsphaltSlab && !ply.isShiftKeyDown()) {
                BlockState oldState = pContext.getLevel().getBlockState(pContext.getClickedPos());

                if (ply.getItemInHand(ply.getUsedItemHand()).has(road_marking)) {
                    int type = ply.getItemInHand(ply.getUsedItemHand()).get(road_marking).intValue();

                    SlabType top = SlabType.TOP;
                    SlabType bottom = SlabType.BOTTOM;
                    SlabType d = SlabType.DOUBLE;
                    SlabType slab = top;

                    if (oldState.getValue(SlabBlock.TYPE) == top) {
                        slab = top;
                    } else if (oldState.getValue(SlabBlock.TYPE) == bottom) {
                        slab = bottom;
                    } else if (oldState.getValue(SlabBlock.TYPE) == d) {
                        slab = d;
                    }
                }
            }

            // direct painting of asphalt blocks
            if ((blockClicked instanceof Asphalt || blockClicked instanceof AsphaltLayer) && !ply.isShiftKeyDown()) {
                BlockState paintAsp = TLRBlocks.PURPLE_ROAD_MARKING.get().defaultBlockState();
                if (ply.getItemInHand(ply.getUsedItemHand()).has(road_marking)) {
                    int type = ply.getItemInHand(ply.getUsedItemHand()).get(road_marking).intValue();
                    int age = 0;
                    boolean notValid = true;
                }
            }

            if(pContext.getHand() == InteractionHand.MAIN_HAND) {
                if(pContext.getPlayer().isShiftKeyDown()){
                    if(!stack.getComponents().has(TLRDataComponents.LENGTH.get())){
                        stack.set(TLRDataComponents.LENGTH.get(),1);
                    }
                    if(stack.getComponents().has(road_marking)) {
                        increaseType(stack);
                        marking_type = stack.get(road_marking).intValue();
                        AbstractPaintbrush.placeMarkingSound(level,positionClicked);
                    }
                    else{
                        stack.set(road_marking,0);
                        AbstractPaintbrush.setupMarkingBrush(level,positionClicked);
                    }
                }
                else{
                    if(stack.getComponents().has(road_marking)){
                        marking_type = stack.get(road_marking).intValue();
                    }
                    if(stack.getComponents().has(TLRDataComponents.LENGTH.get())){
                        length = stack.get(TLRDataComponents.LENGTH.get()).intValue();
                    }
                    AbstractPaintbrush.newPaintLogic(levelAccessor,
                            positionClicked.getX(),positionClicked.getY(),positionClicked.getZ(),
                            ply,stack,marking_type,length,TLRBlocks.PURPLE_ROAD_MARKING.get());
                }
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.SUCCESS;
    }

    private InteractionResult finished(Player ply){
        ply.swing(ply.getUsedItemHand());
        return InteractionResult.CONSUME;
    }

    private void increaseType(ItemStack stack) {
        if (stack.has(road_marking)){
            stack.set(road_marking,stack.get(road_marking).intValue() + 1);
            if(stack.get(road_marking).intValue() >= PurpleRoadMarking.getMaxTypes()){
                stack.set(road_marking,0);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack,context,tooltipComponents,tooltipFlag);
        if(stack.has(road_marking)) {
            typeToName(stack.get(road_marking).intValue());
            tooltipComponents.add(Component.translatable("item.paint_brush.data.pattern_type",stack.get(road_marking).intValue(),PurpleRoadMarking.getMaxTypes() - 1));
            tooltipComponents.add(Component.translatable(currentName).withStyle(ChatFormatting.GREEN));
        }
    }
}
