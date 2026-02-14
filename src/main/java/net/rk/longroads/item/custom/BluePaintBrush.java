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
import net.minecraft.world.item.UseAnim;
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

public class BluePaintBrush extends AbstractPaintbrush{
    public String currentname = "tooltip.thingamajigs.paintbrush.pattern.undefined";

    private final DataComponentType<Integer> road_marking = TLRDataComponents.ROAD_MARKING_PATTERN.get();

    public BluePaintBrush(Properties p) {
        super(p.durability(3000).stacksTo(1));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BRUSH;
    }

    public void typeToName(int type){
        switch(type){
            case 0: currentname = "Thin Corner";break;
            case 1: currentname = "Thin Parking Line";break;
            case 2: currentname = "Thick Parking Line";break;
            case 3: currentname = "Sidewalk Line";break;
            case 4: currentname = "Thin Corner Dot";break;
            case 5: currentname = "Disabled Parking";break;
            case 6: currentname = "Disabled Parking (Alt)";break;
            case 7: currentname = "Short Line";break;
            case 8: currentname = "Short 'T'";break;
            case 9: currentname = "Plus";break;
            case 10: currentname = "Square";break;
            case 11: currentname = "Center 'T'";break;
            case 12: currentname = "Long 'T'";break;
            case 13: currentname = "blue marking";break;
            default: currentname = "tooltip.thingamajigs.paintbrush.pattern.undefined";break;
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
            Block marking = TLRBlocks.BLUE_ROAD_MARKING.get();
            Player ply = pContext.getPlayer();
            int marking_type = 0;
            int length = 1;


            // direct painting of asphalt slabs
            if (blockClicked instanceof AsphaltSlab && !ply.isShiftKeyDown()) {
                BlockState oldState = pContext.getLevel().getBlockState(pContext.getClickedPos());
                BlockState paintAsp = TLRBlocks.BLUE_PARKING_ASPHALT_SLAB.get().defaultBlockState();

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

                    if (blockClicked == TLRBlocks.ASPHALT_SLAB.get()) {
                        if (type == 1) {
                            paintAsp = TLRBlocks.BLUE_PARKING_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING,
                                    ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else{
                            return InteractionResult.PASS;
                        }
                    }
                    else if (blockClicked == TLRBlocks.ASPHALT_OK_SLAB.get()) {
                        if (type == 1) {
                            paintAsp = TLRBlocks.BLUE_PARKING_OK_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING,
                                    ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else{
                            return InteractionResult.PASS;
                        }
                    }
                    else if (blockClicked == TLRBlocks.ASPHALT_MEDIOCRE_SLAB.get()) {
                        if (type == 1) {
                            paintAsp = TLRBlocks.BLUE_PARKING_MEDIOCRE_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING,
                                    ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
                            return finished(ply);
                        }
                        else{
                            return InteractionResult.PASS;
                        }
                    }
                    else if (blockClicked == TLRBlocks.ASPHALT_OLD_SLAB.get()) {
                        if (type == 1) {
                            paintAsp = TLRBlocks.BLUE_PARKING_OLD_ASPHALT_SLAB.get().defaultBlockState();
                            level.setBlock(positionClicked, paintAsp.setValue(RotatingSlab.FACING,
                                    ply.getDirection().getOpposite()).setValue(RotatingSlab.TYPE, slab), 3);
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
                BlockState paintAsp = TLRBlocks.BLUE_PARKING_ASPHALT.get().defaultBlockState();
                if (ply.getItemInHand(ply.getUsedItemHand()).has(road_marking)) {
                    int type = ply.getItemInHand(ply.getUsedItemHand()).get(road_marking).intValue();
                    int age = 0;
                    boolean notValid = false;

                    if(blockClicked == TLRBlocks.ASPHALT.get()) {
                        age = 0;
                    }
                    else if (blockClicked == TLRBlocks.ASPHALT_OK.get()) {
                        age = 1;
                    }
                    else if (blockClicked == TLRBlocks.ASPHALT_MEDIOCRE.get()) {
                        age = 2;
                    }
                    else if (blockClicked == TLRBlocks.ASPHALT_OLD.get()) {
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
                        if (type == 1) {
                            level.setBlock(positionClicked, paintAsp.setValue(MarkedAsphalt.FACING, ply.getDirection().getOpposite()).setValue(MarkedAsphalt.AGE, age), 3);
                            return finished(ply);
                        }
                        else{
                            notValid = true;
                        }
                    }
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
                            ply,stack,marking_type,length,TLRBlocks.BLUE_ROAD_MARKING.get());
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
            if(stack.get(road_marking).intValue() >= BlueRoadMarking.getMaxTypes()){
                stack.set(road_marking,0);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack,context,tooltipComponents,tooltipFlag);
        if(stack.has(road_marking)) {
            typeToName(stack.get(road_marking).intValue());
            tooltipComponents.add(Component.translatable("item.paint_brush.data.pattern_type",stack.get(road_marking).intValue(),BlueRoadMarking.getMaxTypes() - 1));
            tooltipComponents.add(Component.translatable(currentname).withStyle(ChatFormatting.GREEN));
        }
    }
}
