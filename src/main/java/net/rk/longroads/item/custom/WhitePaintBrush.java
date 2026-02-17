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

public class WhitePaintBrush extends AbstractPaintbrush{
    public String currentName = "tooltip.thingamajigs.paintbrush.pattern.undefined";

    public WhitePaintBrush(Properties properties) {
        super(properties);
    }

    public void typeToName(int type) {
        switch(type){
            case 0: currentName = "tooltip.thingamajigs.paintbrush.pattern.full_cover";break;
            case 1: currentName = "tooltip.thingamajigs.paintbrush.pattern.thin_corner_dot";break;
            case 2: currentName = "tooltip.thingamajigs.paintbrush.pattern.center_double_line_turn";break;
            case 3: currentName = "tooltip.thingamajigs.paintbrush.pattern.center_double_line";break;
            case 4: currentName = "tooltip.thingamajigs.paintbrush.pattern.center_single_corner";break;
            case 5: currentName = "tooltip.thingamajigs.paintbrush.pattern.center_single_line";break;
            case 6: currentName = "tooltip.thingamajigs.paintbrush.pattern.center_dashed_single_line";break;
            case 7: currentName = "tooltip.thingamajigs.paintbrush.pattern.thick_parking_line";break;
            case 8: currentName = "tooltip.thingamajigs.paintbrush.pattern.thin_corner";break;
            case 9: currentName = "tooltip.thingamajigs.paintbrush.pattern.thin_parking_line";break;
            case 10: currentName = "tooltip.thingamajigs.paintbrush.pattern.left_arrow_center_line";break;
            case 11: currentName = "tooltip.thingamajigs.paintbrush.pattern.left_arrow_center_arrow";break;
            case 12: currentName = "tooltip.thingamajigs.paintbrush.pattern.left_arrow";break;
            case 13: currentName = "tooltip.thingamajigs.paintbrush.pattern.right_arrow_center_line";break;
            case 14: currentName = "tooltip.thingamajigs.paintbrush.pattern.right_arrow_center_arrow";break;
            case 15: currentName = "tooltip.thingamajigs.paintbrush.pattern.right_arrow";break;
            case 16: currentName = "tooltip.thingamajigs.paintbrush.pattern.u_turn";break;
            case 17: currentName = "tooltip.thingamajigs.paintbrush.pattern.up_arrow";break;
            case 18: currentName = "tooltip.thingamajigs.paintbrush.pattern.disabled_symbol";break;
            case 19: currentName = "tooltip.thingamajigs.paintbrush.pattern.ahead";break;
            case 20: currentName = "tooltip.thingamajigs.paintbrush.pattern.only";break;
            case 21: currentName = "tooltip.thingamajigs.paintbrush.pattern.parking_p";break;
            case 22: currentName = "tooltip.thingamajigs.paintbrush.pattern.stop";break;
            case 23: currentName = "tooltip.thingamajigs.paintbrush.pattern.school";break;
            case 24: currentName = "tooltip.thingamajigs.paintbrush.pattern.shcool";break;
            case 25: currentName = "tooltip.thingamajigs.paintbrush.pattern.sidewalk_line";break;
            case 26: currentName = "tooltip.thingamajigs.paintbrush.pattern.disabled_symbol_alt";break;
            case 27: currentName = "tooltip.thingamajigs.paintbrush.pattern.bicycle";break;
            case 28: currentName = "tooltip.thingamajigs.paintbrush.pattern.short_line";break;
            case 29: currentName = "tooltip.thingamajigs.paintbrush.pattern.short_t";break;
            case 30: currentName = "tooltip.thingamajigs.paintbrush.pattern.plus";break;
            case 31: currentName = "tooltip.thingamajigs.paintbrush.pattern.square";break;
            case 32: currentName = "tooltip.thingamajigs.paintbrush.pattern.center_t";break;
            case 33: currentName = "tooltip.thingamajigs.paintbrush.pattern.long_t";break;
            case 34: currentName = "tooltip.thingamajigs.paintbrush.pattern.arrow_three_way";break;
            case 35: currentName = "tooltip.thingamajigs.paintbrush.pattern.uturn_connector";break;
            case 36: currentName = "tooltip.thingamajigs.paintbrush.pattern.diagonal";break;
            case 37: currentName = "tooltip.thingamajigs.paintbrush.pattern.inverted_n";break;
            case 38: currentName = "tooltip.thingamajigs.paintbrush.pattern.exit_up_arrow";break;
            case 39: currentName = "tooltip.thingamajigs.paintbrush.pattern.stop_line";break;
            case 40: currentName = "tooltip.thingamajigs.paintbrush.pattern.triangle";break;
            case 41: currentName = "tooltip.thingamajigs.paintbrush.pattern.inverted_triangle";break;
            case 42: currentName = "tooltip.thingamajigs.paintbrush.pattern.toll";break;
            case 43: currentName = "tooltip.thingamajigs.paintbrush.pattern.plaza";break;
            default:
                currentName = "tooltip.thingamajigs.paintbrush.pattern.undefined";
                break;
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
            Block marking = TLRBlocks.WHITE_ROAD_MARKING.get();
            Player ply = pContext.getPlayer();
            int marking_type = 0;
            int length = 1;

            // direct painting of asphalt slabs
            if (blockClicked instanceof AsphaltSlab && !ply.isShiftKeyDown()) {
                BlockState oldState = pContext.getLevel().getBlockState(pContext.getClickedPos());
                BlockState paintAsp = TLRBlocks.WHITE_PARKING_ASPHALT_SLAB.get().defaultBlockState();

                if (ply.getItemInHand(ply.getUsedItemHand()).getComponents().has(TLRDataComponents.ROAD_MARKING_PATTERN.get())) {
                    int type = ply.getItemInHand(ply.getUsedItemHand()).getComponents().get(TLRDataComponents.ROAD_MARKING_PATTERN.get()).intValue();

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


                    if (blockClicked == TLRBlocks.ASPHALT_SLAB.get()) {
                        if (type == 2) {
                            paintAsp = TLRBlocks.WHITE_DT_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else if (type == 3) {
                            paintAsp = TLRBlocks.WHITE_D_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else if (type == 9) {
                            paintAsp = TLRBlocks.WHITE_PARKING_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else{
                            return InteractionResult.PASS;
                        }
                    }
                    else if (blockClicked == TLRBlocks.ASPHALT_OK_SLAB.get()) {
                        if (type == 2) {
                            paintAsp = TLRBlocks.WHITE_DT_OK_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else if (type == 3) {
                            paintAsp = TLRBlocks.WHITE_D_OK_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else if (type == 9) {
                            paintAsp = TLRBlocks.WHITE_PARKING_OK_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else{
                            return InteractionResult.PASS;
                        }
                    }
                    else if (blockClicked == TLRBlocks.ASPHALT_MEDIOCRE_SLAB.get()) {
                        if (type == 2) {
                            paintAsp = TLRBlocks.WHITE_DT_MEDIOCRE_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else if (type == 3) {
                            paintAsp = TLRBlocks.WHITE_D_MEDIOCRE_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else if (type == 9) {
                            paintAsp = TLRBlocks.WHITE_PARKING_MEDIOCRE_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else{
                            return InteractionResult.PASS;
                        }
                    }
                    else if (blockClicked == TLRBlocks.ASPHALT_OLD_SLAB.get()) {
                        if (type == 2) {
                            paintAsp = TLRBlocks.WHITE_DT_OLD_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else if (type == 3) {
                            paintAsp = TLRBlocks.WHITE_D_OLD_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else if (type == 9) {
                            paintAsp = TLRBlocks.WHITE_PARKING_OLD_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING, ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else{
                            return InteractionResult.PASS;
                        }
                    }
                }
            }

            // direct painting of asphalt blocks
            if ((blockClicked instanceof Asphalt || blockClicked instanceof AsphaltLayer) && !ply.isShiftKeyDown()) {
                BlockState paintAsp = TLRBlocks.WHITE_PARKING_ASPHALT.get().defaultBlockState();
                if (ply.getItemInHand(ply.getUsedItemHand()).getComponents().has(TLRDataComponents.ROAD_MARKING_PATTERN.get())) {
                    int type = ply.getItemInHand(ply.getUsedItemHand()).getComponents().get(TLRDataComponents.ROAD_MARKING_PATTERN.get()).intValue();
                    int age = 0;
                    boolean notValid = false;

                    if(blockClicked == TLRBlocks.ASPHALT.get()){
                        age = 0;
                    }
                    else if (blockClicked == TLRBlocks.ASPHALT_OK.get()) {
                        age = 1;
                    } else if (blockClicked == TLRBlocks.ASPHALT_MEDIOCRE.get()) {
                        age = 2;
                    } else if (blockClicked == TLRBlocks.ASPHALT_OLD.get()) {
                        age = 3;
                    }
                    else{
                        if(blockClicked == TLRBlocks.ASPHALT_LAYER.get()){
                            if(blockState.getValue(LayeredBlock.LAYERS).intValue() > 7){
                                age = 0;
                            }
                            else{
                                notValid = true;
                            }
                        }
                        else if(blockClicked == TLRBlocks.OK_ASPHALT_LAYER.get()){
                            if(blockState.getValue(LayeredBlock.LAYERS).intValue() > 7){
                                age = 1;
                            }
                            else{
                                notValid = true;
                            }
                        }
                        else if(blockClicked == TLRBlocks.MEDIOCRE_ASPHALT_LAYER.get()){
                            if(blockState.getValue(LayeredBlock.LAYERS).intValue() > 7){
                                age = 2;
                            }
                            else{
                                notValid = true;
                            }
                        }
                        else if(blockClicked == TLRBlocks.OLD_ASPHALT_LAYER.get()){
                            if(blockState.getValue(LayeredBlock.LAYERS).intValue() > 7){
                                age = 3;
                            }
                            else{
                                notValid = true;
                            }
                        }
                        else{
                            notValid = true;
                        }
                    }

                    if(notValid == false){
                        if (type == 2) {
                            paintAsp = TLRBlocks.DOUBLE_CORNER_WHITE_ASPHALT.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(MarkedAsphalt.FACING, ply.getDirection().getOpposite()).setValue(MarkedAsphalt.AGE, age), 3);
                            return finished(ply);
                        }
                        else if (type == 3) {
                            paintAsp = TLRBlocks.DOUBLE_WHITE_ASPHALT.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(MarkedAsphalt.FACING, ply.getDirection().getOpposite()).setValue(MarkedAsphalt.AGE, age), 3);
                            return finished(ply);
                        }
                        else if (type == 9) {
                            paintAsp = TLRBlocks.WHITE_PARKING_ASPHALT.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(MarkedAsphalt.FACING, ply.getDirection().getOpposite()).setValue(MarkedAsphalt.AGE, age), 3);
                            return finished(ply);
                        }
                    }
                    else{
                        notValid = true;
                    }
                }
            }

            if(pContext.getHand() == InteractionHand.MAIN_HAND) {
                if(pContext.getPlayer().isShiftKeyDown()){
                    if(!stack.getComponents().has(TLRDataComponents.LENGTH.get())){
                        stack.set(TLRDataComponents.LENGTH.get(),1);
                    }
                    if(stack.getComponents().has(TLRDataComponents.ROAD_MARKING_PATTERN.get())) {
                        increaseType(stack);
                        marking_type = stack.get(TLRDataComponents.ROAD_MARKING_PATTERN.get()).intValue();
                        AbstractPaintbrush.placeMarkingSound(level,positionClicked);
                    }
                    else {
                        stack.set(TLRDataComponents.ROAD_MARKING_PATTERN.get(),0);
                        AbstractPaintbrush.setupMarkingBrush(level,positionClicked);
                    }
                }
                else{
                    if(stack.getComponents().has(TLRDataComponents.ROAD_MARKING_PATTERN.get())){
                        marking_type = stack.get(TLRDataComponents.ROAD_MARKING_PATTERN.get()).intValue();
                    }
                    if(stack.getComponents().has(TLRDataComponents.LENGTH.get())){
                        length = stack.get(TLRDataComponents.LENGTH.get()).intValue();
                    }
                    AbstractPaintbrush.newPaintLogic(level,
                            positionClicked.getX(),positionClicked.getY(),positionClicked.getZ(),
                            ply,stack,
                            marking_type,length,
                            TLRBlocks.WHITE_ROAD_MARKING.get());
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
        if(!stack.getComponents().has(TLRDataComponents.LENGTH.get())){
            stack.set(TLRDataComponents.LENGTH.get(),1);
        }
        if(stack.getComponents().has(TLRDataComponents.ROAD_MARKING_PATTERN.get())){
            stack.set(TLRDataComponents.ROAD_MARKING_PATTERN.get(),stack.get(TLRDataComponents.ROAD_MARKING_PATTERN.get()).intValue() + 1);
            if(stack.get(TLRDataComponents.ROAD_MARKING_PATTERN.get()).intValue() >= WhiteRoadMarking.getMaxTypes()){
                stack.set(TLRDataComponents.ROAD_MARKING_PATTERN.get(),0);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack,context,tooltipComponents,tooltipFlag);
        if(stack.has(TLRDataComponents.ROAD_MARKING_PATTERN.get())) {
            typeToName(stack.get(TLRDataComponents.ROAD_MARKING_PATTERN.get()).intValue());
            tooltipComponents.add(Component.translatable("item.paint_brush.data.pattern_type",stack.get(TLRDataComponents.ROAD_MARKING_PATTERN.get()).intValue(),WhiteRoadMarking.MAX_TYPES - 1));
            tooltipComponents.add(Component.translatable(currentName).withStyle(ChatFormatting.GREEN));
        }
    }
}
