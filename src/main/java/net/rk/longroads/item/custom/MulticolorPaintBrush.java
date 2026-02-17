package net.rk.longroads.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
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

public class MulticolorPaintBrush extends AbstractPaintbrush{
    public MulticolorPaintBrush(Properties properties){
        super(properties);
    }

    @Override
    public void typeToName(int type){
        switch(type){
            case 0: currentName = "tooltip.thingamajigs.paintbrush.pattern.multicolored.ahead_purple";break;
            case 1: currentName = "tooltip.thingamajigs.paintbrush.pattern.multicolored.stop_ahead_purple";break;
            case 2: currentName = "tooltip.thingamajigs.paintbrush.pattern.multicolored.toll_purple";break;
            case 3: currentName = "tooltip.thingamajigs.paintbrush.pattern.multicolored.plaza_purple";break;
            default:
                currentName = "tooltip.thingamajigs.paintbrush.pattern.undefined";break;
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext){
        if(!pContext.getLevel().isClientSide()) {
            LevelAccessor levelAccessor = pContext.getLevel();
            Level level = pContext.getLevel();
            BlockPos positionClicked = pContext.getClickedPos();
            BlockState blockState = level.getBlockState(positionClicked);
            Block blockClicked = level.getBlockState(positionClicked).getBlock();
            ItemStack stack = pContext.getItemInHand();
            Block marking = TLRBlocks.MULTICOLORED_ROAD_MARKING.get();
            Player ply = pContext.getPlayer();
            int marking_type = 0;
            int length = 1;

            // direct painting of asphalt slabs
            if (blockClicked instanceof AsphaltSlab && !ply.isShiftKeyDown()){
                BlockState oldState = pContext.getLevel().getBlockState(pContext.getClickedPos());

                if (ply.getItemInHand(ply.getUsedItemHand()).has(roadMarkingComponent)) {
                    int type = ply.getItemInHand(ply.getUsedItemHand()).get(roadMarkingComponent).intValue();

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
            if ((blockClicked instanceof Asphalt || blockClicked instanceof AsphaltLayer) && !ply.isShiftKeyDown()){
                if (ply.getItemInHand(ply.getUsedItemHand()).has(roadMarkingComponent)) {
                    int type = ply.getItemInHand(ply.getUsedItemHand()).get(roadMarkingComponent).intValue();
                    int age = 0;
                    boolean notValid = true;
                }
            }

            if(pContext.getHand() == InteractionHand.MAIN_HAND){
                if(pContext.getPlayer().isShiftKeyDown()){
                    if(!stack.getComponents().has(TLRDataComponents.LENGTH.get())){
                        stack.set(TLRDataComponents.LENGTH.get(),1);
                    }
                    if(stack.getComponents().has(roadMarkingComponent)) {
                        increaseType(stack);
                        marking_type = stack.get(roadMarkingComponent).intValue();
                        AbstractPaintbrush.placeMarkingSound(level,positionClicked);
                    }
                    else{
                        stack.set(roadMarkingComponent,0);
                        AbstractPaintbrush.setupMarkingBrush(level,positionClicked);
                    }
                }
                else{
                    if(stack.getComponents().has(roadMarkingComponent)){
                        marking_type = stack.get(roadMarkingComponent).intValue();
                    }
                    if(stack.getComponents().has(TLRDataComponents.LENGTH.get())){
                        length = stack.get(TLRDataComponents.LENGTH.get()).intValue();
                    }
                    AbstractPaintbrush.newPaintLogic(levelAccessor,
                            positionClicked.getX(),positionClicked.getY(),positionClicked.getZ(),
                            ply,stack,marking_type,length,TLRBlocks.MULTICOLORED_ROAD_MARKING.get());
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
        if (stack.has(roadMarkingComponent)){
            stack.set(roadMarkingComponent,stack.get(roadMarkingComponent).intValue() + 1);
            if(stack.get(roadMarkingComponent).intValue() >= MulticoloredRoadMarking.getMaxTypes()){
                stack.set(roadMarkingComponent,0);
            }
        }
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack,context,tooltipComponents,tooltipFlag);
        tooltipComponents.add(Component.translatable("item.thingamajigs.multicolor_paint_brush.desc")
                .withStyle(ChatFormatting.GRAY));
        if(stack.has(roadMarkingComponent)) {
            typeToName(stack.get(roadMarkingComponent).intValue());
            tooltipComponents.add(Component.translatable("item.paint_brush.data.pattern_type",stack.get(roadMarkingComponent).intValue(),MulticoloredRoadMarking.MAX_TYPES - 1));
            tooltipComponents.add(Component.translatable(currentName).withStyle(ChatFormatting.GREEN));
        }
    }
}
