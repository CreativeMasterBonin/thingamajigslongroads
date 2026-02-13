package net.rk.longroads.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.rk.longroads.block.TLRBlocks;
import net.rk.longroads.block.custom.BlueRoadMarking;
import net.rk.longroads.block.custom.WhiteRoadMarking;
import net.rk.longroads.block.custom.YellowRoadMarking;
import net.rk.longroads.config.TLRServerConfig;
import net.rk.longroads.item.TLRDataComponents;
import net.rk.thingamajigs.xtras.TCalcStuff;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractPaintbrush extends Item {
    public static int defaultLength = 1;

    public AbstractPaintbrush(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.thingamajigs.paintbrush").withStyle(ChatFormatting.GRAY));
        int value = defaultLength;
        if(stack.getComponents().has(TLRDataComponents.LENGTH.get())){
            value = stack.getComponents().get(TLRDataComponents.LENGTH.get()).intValue();
            tooltipComponents.add(Component.translatable("tooltip.thingamajigs.length",value).withStyle(ChatFormatting.BLUE));
        }
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack itemStack = new ItemStack(this);
        itemStack.set(TLRDataComponents.ROAD_MARKING_PATTERN.get(),0);
        itemStack.set(TLRDataComponents.LENGTH.get(),1);
        return itemStack;
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        if(action.equals(ClickAction.SECONDARY)){
            if(stack.getComponents().has(TLRDataComponents.ROAD_MARKING_PATTERN.get()) && stack.getComponents().has(TLRDataComponents.LENGTH.get())){
                stack.set(TLRDataComponents.LENGTH.get(),stack.get(TLRDataComponents.LENGTH.get()).intValue() + 1);

                if(stack.get(TLRDataComponents.LENGTH.get()).intValue() > TLRServerConfig.SERVER.maxAmountOfRoadMarkingPerPaint.get().intValue()){
                    stack.set(TLRDataComponents.LENGTH.get(),1);
                }

                player.playSound(SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_OFF,0.75f, TCalcStuff.nextFloatBetweenInclusive(0.95f,1.0f));
                return true;
            }
            else{
                Logger.getAnonymousLogger().fine(stack + " does not have required components.");
            }
        }
        return false;
    }

    public static void placeMarkingSound(Level level, BlockPos pos){
        level.playSound(null,pos,SoundEvents.HONEYCOMB_WAX_ON,
                SoundSource.BLOCKS,1f, TCalcStuff.nextFloatBetweenInclusive(0.97f,1.0f));
    }

    public static void setupMarkingBrush(Level level, BlockPos pos){
        level.playSound(null,pos,SoundEvents.AXE_STRIP,
                SoundSource.BLOCKS,1f, TCalcStuff.nextFloatBetweenInclusive(0.97f,1.0f));
    }

    // https://github.com/Tutorials-By-Kaupenjoe/NeoForge-Tutorial-1.21.X/blob/main/src/main/java/net/kaupenjoe/tutorialmod/item/custom/HammerItem.java
    // MIT (2024 Kaupenjoe) - edited version (reduced code amount, changed names, added extra functionality and customization)
    public static List<BlockPos> getClippingBlocks(int blockRangeX, int blockRangeY, int blockRangeZ, ClipContext.Fluid allowedFluidClipType, ClipContext.Block allowedBlockClipType, BlockPos start, ServerPlayer serverPlayer){
        List<BlockPos> clippingBlocks = new ArrayList<>();

        BlockHitResult result = serverPlayer.level().clip(new ClipContext(
                serverPlayer.getEyePosition(1f),
                (serverPlayer.getEyePosition(1f).add(serverPlayer.getViewVector(1f).scale(3f))),
                allowedBlockClipType,
                allowedFluidClipType,
                serverPlayer
        ));

        // more functionality for staff strength
        Direction resultDir = result.getDirection();
        double resultDist = result.getBlockPos().distToCenterSqr(start.getX(),start.getY(),start.getZ());

        if(result.getType() == HitResult.Type.MISS){
            return clippingBlocks;
        }

        // first check x, then y, then z
        for(int x = -blockRangeX; x <= blockRangeX; x++){
            for(int y = -blockRangeY; y <= blockRangeY; y++){
                for(int z = -blockRangeZ; z <= blockRangeZ; z++){
                    if(resultDir == Direction.DOWN || resultDir == Direction.UP){
                        int posX = start.getX() + x;
                        int posY = start.getY() + z;
                        int posZ = start.getZ() + y;
                        clippingBlocks.add(new BlockPos(posX,posY,posZ));
                    }
                    if(resultDir == Direction.NORTH || resultDir == Direction.SOUTH){
                        int posX = start.getX() + x;
                        int posY = start.getY() + y;
                        int posZ = start.getZ() + z;
                        clippingBlocks.add(new BlockPos(posX,posY,posZ));
                    }
                    if(resultDir == Direction.EAST || resultDir == Direction.WEST){
                        int posX = start.getX() + z;
                        int posY = start.getY() + y;
                        int posZ = start.getZ() + x;
                        clippingBlocks.add(new BlockPos(posX,posY,posZ));
                    }
                }
            }
        }

        return clippingBlocks;
    }

    public static void newPaintLogic(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack stack, int marking_type, int length, Block blocktype){
        boolean badFlag = false;
        BlockPos pos = new BlockPos((int)x,(int)y,(int)z);
        boolean painted = false;

        if(!world.getBlockState(new BlockPos((int)x,(int)y + 1,(int)z)).is(BlockTags.REPLACEABLE)){
            world.playSound(null,new BlockPos((int)x,(int)y + 1,(int)z),
                    SoundEvents.VILLAGER_NO, SoundSource.BLOCKS, 1f, 1.1f);
            badFlag = true;
            return;
        }

        // check if paintbrush length is higher than the config value, and if so, set it to the config value
        if(stack.getComponents().has(TLRDataComponents.LENGTH.get())){
            if(stack.get(TLRDataComponents.LENGTH).intValue() > TLRServerConfig.SERVER.maxAmountOfRoadMarkingPerPaint.get().intValue()){
                stack.set(TLRDataComponents.LENGTH.get(),TLRServerConfig.SERVER.maxAmountOfRoadMarkingPerPaint.get().intValue());
                length = stack.get(TLRDataComponents.LENGTH.get()).intValue();
            }
        }

        // check if on server and player is a serverplayer
        if(!world.isClientSide() && entity instanceof ServerPlayer && !badFlag && length > 1){
            int dirX;
            int dirY;
            if(entity.getDirection().getOpposite() == Direction.NORTH || entity.getDirection().getOpposite() == Direction.SOUTH){
                dirY = length - 1;
                dirX = 0;
            }
            else if(entity.getDirection().getOpposite() == Direction.WEST || entity.getDirection().getOpposite() == Direction.EAST){
                dirX = length - 1;
                dirY = 0;
            }
            else{
                dirX = 0;
                dirY = 0;
            }
            // get a list of positions to place the paint on
            List<BlockPos> positions = AbstractPaintbrush.getClippingBlocks(dirX,dirY,0,
                    ClipContext.Fluid.NONE,
                    ClipContext.Block.VISUAL,
                    new BlockPos((int)x,(int)y + 1,(int)z),(ServerPlayer) entity);
            // iterate through all position
            for(BlockPos selectedPos : positions){
                // must be replaceable
                if(world.getBlockState(selectedPos).is(BlockTags.REPLACEABLE)){
                    world.setBlock(selectedPos, blocktype.defaultBlockState(), 3);
                    painted = true;

                    BlockState stateToManipulate = world.getBlockState(selectedPos);

                    if(blocktype == TLRBlocks.WHITE_ROAD_MARKING.get()){
                        marking_type = Mth.clamp(marking_type,WhiteRoadMarking.MIN_TYPES,WhiteRoadMarking.MAX_TYPES);
                        stateToManipulate = world.getBlockState(selectedPos).setValue(WhiteRoadMarking.TYPE,marking_type);
                    }
                    else if (blocktype == TLRBlocks.YELLOW_ROAD_MARKING.get()){
                        marking_type = Mth.clamp(marking_type,YellowRoadMarking.MIN_TYPES,YellowRoadMarking.MAX_TYPES);
                        stateToManipulate = world.getBlockState(selectedPos).setValue(YellowRoadMarking.TYPE,marking_type);
                    }
                    else if (blocktype == TLRBlocks.BLUE_ROAD_MARKING.get()){
                        marking_type = Mth.clamp(marking_type,BlueRoadMarking.MIN_TYPES,BlueRoadMarking.MAX_TYPES);
                        stateToManipulate = world.getBlockState(selectedPos).setValue(BlueRoadMarking.TYPE,marking_type);
                    }

                    Direction entityDirection = entity.getDirection().getOpposite();

                    // BLECK! This code needs to be de-ugly-ified!
                    Property<?> _property = stateToManipulate.getBlock().getStateDefinition().getProperty("facing");

                    // change block to face player
                    if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(entityDirection)) {
                        world.setBlock(selectedPos,stateToManipulate.setValue(_dp, entityDirection), 3);
                    }
                    else {
                        _property = stateToManipulate.getBlock().getStateDefinition().getProperty("axis");
                        if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(entityDirection.getAxis()))
                            world.setBlock(selectedPos,stateToManipulate.setValue(_ap, entityDirection.getAxis()), 3);
                    }
                }
            }
            if(painted){
                world.playSound(null,new BlockPos((int)x,(int)y,(int)z),
                        SoundEvents.CAKE_ADD_CANDLE,SoundSource.BLOCKS,
                        1f, 1f);
            }
        }
        else if(!world.isClientSide() && entity instanceof ServerPlayer && length <= 1){
            BlockPos selectedPosNoClipping = new BlockPos((int)x,(int)y + 1,(int)z);
            if(world.getBlockState(selectedPosNoClipping).is(BlockTags.REPLACEABLE)){
                world.setBlock(selectedPosNoClipping, blocktype.defaultBlockState(), 3);

                BlockState stateToManipulate = world.getBlockState(selectedPosNoClipping);

                if(blocktype == TLRBlocks.WHITE_ROAD_MARKING.get()){
                    marking_type = Mth.clamp(marking_type,WhiteRoadMarking.MIN_TYPES,WhiteRoadMarking.MAX_TYPES);
                    stateToManipulate = world.getBlockState(selectedPosNoClipping).setValue(WhiteRoadMarking.TYPE,marking_type);
                }
                else if (blocktype == TLRBlocks.YELLOW_ROAD_MARKING.get()){
                    marking_type = Mth.clamp(marking_type,YellowRoadMarking.MIN_TYPES,YellowRoadMarking.MAX_TYPES);
                    stateToManipulate = world.getBlockState(selectedPosNoClipping).setValue(YellowRoadMarking.TYPE,marking_type);
                }
                else if (blocktype == TLRBlocks.BLUE_ROAD_MARKING.get()){
                    marking_type = Mth.clamp(marking_type, BlueRoadMarking.MIN_TYPES,BlueRoadMarking.MAX_TYPES);
                    stateToManipulate = world.getBlockState(selectedPosNoClipping).setValue(BlueRoadMarking.TYPE,marking_type);
                }

                Direction _dir = ((entity.getDirection()).getOpposite());
                BlockState _bs = stateToManipulate;

                Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");

                // change block to face player
                if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
                    world.setBlock(selectedPosNoClipping, _bs.setValue(_dp, _dir), 3);
                }
                else {
                    _property = _bs.getBlock().getStateDefinition().getProperty("axis");
                    if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
                        world.setBlock(selectedPosNoClipping, _bs.setValue(_ap, _dir.getAxis()), 3);

                }

                world.playSound(null,new BlockPos((int)x,(int)y,(int)z),
                        SoundEvents.CAKE_ADD_CANDLE,SoundSource.BLOCKS,
                        1f, 1f);
            }
            else{
                world.playSound(null,new BlockPos((int)x,(int)y + 1,(int)z),
                        SoundEvents.VILLAGER_NO, SoundSource.BLOCKS, 1f, 1.1f);
                world.addParticle(ParticleTypes.CLOUD,pos.getX(),pos.getY(),pos.getZ(),0,1,0);
            }
        }
    }
}
