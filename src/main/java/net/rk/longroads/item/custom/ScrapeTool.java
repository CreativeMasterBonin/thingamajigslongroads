package net.rk.longroads.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.rk.longroads.ThingamajigsLongRoads;
import net.rk.longroads.block.TLRBlocks;
import net.rk.longroads.block.custom.*;
import net.rk.longroads.datagen.TLRTag;

import java.util.List;
import java.util.logging.Logger;

@SuppressWarnings("deprecated")
public class ScrapeTool extends Item{
    public ScrapeTool(Properties p) {
        super(p.durability(3000).stacksTo(1));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BRUSH;
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Block blk = ctx.getLevel().getBlockState(ctx.getClickedPos()).getBlock();
        BlockState bs = ctx.getLevel().getBlockState(ctx.getClickedPos());
        Level lvl = ctx.getLevel();
        BlockPos bp = ctx.getClickedPos();

        // if an addon (or this mod) adds a PavementMarking and doesn't tag it properly, warn the developer and player of this issue
        if(blk instanceof PavementMarking && !bs.is(TLRTag.ROAD_MARKING)){
            String name = "Block: " + blk.getDescriptionId();
            Logger.getAnonymousLogger().info(name + " is a PavementMarking and should be tagged as thingamajigslongroads:road_marking but is not");
        }

        if(bs.is(TLRTag.ROAD_MARKING)){
            for(int i = 0; i < 17; ++i) {
                Vec3 vec3 = Vec3.atCenterOf(bp).add(0.0D, (double)0.75F, 0.0D);
                lvl.addParticle(new BlockParticleOption(ParticleTypes.BLOCK,bs), vec3.x, vec3.y, vec3.z, 0.0D, 0.0D, 0.0D);
            }
            lvl.playSound(null,bp, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS,1f,1f);
            lvl.destroyBlock(bp,false);
            ctx.getPlayer().swing(ctx.getHand());
            return InteractionResult.CONSUME;
        }

        if(blk instanceof MarkedAsphalt){
            if(bs.getValue(MarkedAsphalt.AGE) == 0){
                changeIt(lvl,bs,TLRBlocks.ASPHALT.get().defaultBlockState(),bp);
                ctx.getPlayer().swing(ctx.getHand());
                return InteractionResult.CONSUME;
            }
            else if(bs.getValue(MarkedAsphalt.AGE) == 1){
                changeIt(lvl,bs,TLRBlocks.ASPHALT_OK.get().defaultBlockState(),bp);
                ctx.getPlayer().swing(ctx.getHand());
                return InteractionResult.CONSUME;
            }
            else if(bs.getValue(MarkedAsphalt.AGE) == 2){
                changeIt(lvl,bs,TLRBlocks.ASPHALT_MEDIOCRE.get().defaultBlockState(),bp);
                ctx.getPlayer().swing(ctx.getHand());
                return InteractionResult.CONSUME;
            }
            else if(bs.getValue(MarkedAsphalt.AGE) == 3){
                changeIt(lvl,bs,TLRBlocks.ASPHALT_OLD.get().defaultBlockState(),bp);
                ctx.getPlayer().swing(ctx.getHand());
                return InteractionResult.CONSUME;
            }
        }
        else if(blk instanceof MarkedAsphaltSlab){
            if(bs.is(TLRBlocks.WHITE_PARKING_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.WHITE_DT_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.WHITE_D_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.YELLOW_PARKING_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.YELLOW_DT_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.YELLOW_D_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.BLUE_PARKING_ASPHALT_SLAB.get())){
                changeIt(lvl,bs,
                        TLRBlocks.ASPHALT_SLAB.get().defaultBlockState()
                                .setValue(MarkedAsphaltSlab.TYPE,bs.getValue(SlabBlock.TYPE)),bp);
                ctx.getPlayer().swing(ctx.getHand());
                return InteractionResult.CONSUME;
            }
            else if(bs.is(TLRBlocks.WHITE_PARKING_OK_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.WHITE_DT_OK_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.WHITE_D_OK_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.YELLOW_PARKING_OK_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.YELLOW_DT_OK_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.YELLOW_D_OK_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.BLUE_PARKING_OK_ASPHALT_SLAB.get())){
                changeIt(lvl,bs,
                        TLRBlocks.ASPHALT_OK_SLAB.get().defaultBlockState()
                                .setValue(MarkedAsphaltSlab.TYPE,bs.getValue(SlabBlock.TYPE)),bp);
                ctx.getPlayer().swing(ctx.getHand());
                return InteractionResult.CONSUME;
            }
            else if(bs.is(TLRBlocks.WHITE_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.WHITE_DT_MEDIOCRE_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.WHITE_D_MEDIOCRE_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.YELLOW_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.YELLOW_DT_MEDIOCRE_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.YELLOW_D_MEDIOCRE_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.BLUE_PARKING_MEDIOCRE_ASPHALT_SLAB.get())){
                changeIt(lvl,bs,
                        TLRBlocks.ASPHALT_MEDIOCRE_SLAB.get().defaultBlockState()
                                .setValue(MarkedAsphaltSlab.TYPE,bs.getValue(SlabBlock.TYPE)),bp);
                ctx.getPlayer().swing(ctx.getHand());
                return InteractionResult.CONSUME;
            }
            else if(bs.is(TLRBlocks.WHITE_PARKING_OLD_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.WHITE_DT_OLD_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.WHITE_D_OLD_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.YELLOW_PARKING_OLD_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.YELLOW_DT_OLD_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.YELLOW_D_OLD_ASPHALT_SLAB.get())
                    || bs.is(TLRBlocks.BLUE_PARKING_OLD_ASPHALT_SLAB.get())){
                changeIt(lvl,bs,
                        TLRBlocks.ASPHALT_OLD_SLAB.get().defaultBlockState()
                                .setValue(MarkedAsphaltSlab.TYPE,bs.getValue(SlabBlock.TYPE)),bp);
                ctx.getPlayer().swing(ctx.getHand());
                return InteractionResult.CONSUME;
            }
        }
        else{
            return InteractionResult.PASS;
        }
        return InteractionResult.PASS;
    }

    private static void changeIt(Level lvl, BlockState bs, BlockState nBs, BlockPos bp){
        for(int i = 0; i < 17; ++i) {
            Vec3 vec3 = Vec3.atCenterOf(bp).add(0.0D, (double)0.75F, 0.0D);
            lvl.addParticle(new BlockParticleOption(ParticleTypes.BLOCK,bs), vec3.x, vec3.y, vec3.z, 0.0D, 0.0D, 0.0D);
        }
        lvl.setBlock(bp,nBs,3);
        lvl.playSound(null,bp, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS,1f,1f);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.thingamajigslongroads.scrape_tool.desc")
                .withStyle(ChatFormatting.GRAY));
    }
}
