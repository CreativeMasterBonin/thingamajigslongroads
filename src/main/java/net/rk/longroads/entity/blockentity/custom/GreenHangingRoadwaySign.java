package net.rk.longroads.entity.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.longroads.entity.blockentity.TLRBlockEntity;

public class GreenHangingRoadwaySign extends SignBlockEntity {
    public GreenHangingRoadwaySign(BlockPos bp, BlockState bs) {
        super(TLRBlockEntity.GREEN_HANGING_ROADWAY_SIGN.get(),bp,bs);
    }

    @Override
    public int getTextLineHeight() {
        return 9;
    }

    @Override
    public int getMaxTextLineWidth() {
        return 60;
    }

    @Override
    public SoundEvent getSignInteractionFailedSoundEvent() {
        return SoundEvents.WAXED_HANGING_SIGN_INTERACT_FAIL;
    }
}
