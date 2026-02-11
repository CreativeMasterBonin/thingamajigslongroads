package net.rk.longroads.entity.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.longroads.ThingamajigsLongRoads;
import net.rk.longroads.entity.blockentity.TLRBlockEntity;
import org.jetbrains.annotations.Nullable;

public class DynamicRoadSignBE extends BlockEntity{
    BlockPos bp;
    public float yAngle = 0.0f;
    public int ticks;

    public int signType = 0;
    public String signTexture = "-1";

    public DynamicRoadSignBE(BlockPos pos, BlockState blockState){
        super(TLRBlockEntity.DYNAMIC_ROAD_SIGN_BE.get(), pos, blockState);
        bp = pos;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        this.loadAdditional(tag,lookupProvider);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider lp) {
        CompoundTag ct = new CompoundTag();
        saveAdditional(ct,lp);
        return ct;
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
    }

    @Override
    public void clearRemoved() {
        super.clearRemoved();
    }

    public void updateBlock(){
        this.setChanged();
        if(this.getLevel() != null) {
            BlockState bs2 = this.getLevel().getBlockState(this.getBlockPos());
            this.getLevel().sendBlockUpdated(this.getBlockPos(), bs2, bs2, 3);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider slp) {
        super.saveAdditional(pTag, slp);
        pTag.putFloat("y_angle",yAngle);
        pTag.putString("sign_texture",signTexture);
        pTag.putInt("sign_type",signType);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider lp) {
        yAngle = pTag.getFloat("y_angle");
        signTexture = pTag.getString("sign_texture");
        signType = pTag.getInt("sign_type");
    }

    public static void serverTick(Level slvl, BlockPos sbp, BlockState sbs, DynamicRoadSignBE rrcbe){
        if(ThingamajigsLongRoads.badFileAccessFlag){
            slvl.removeBlock(sbp,false);
            return;
        }

        ++rrcbe.ticks;
        /*
        if(locs.isEmpty() || rrcbe.signTexture.equals("-1")){
            rrcbe.signType = -1;
        }
        else{
            if(rrcbe.signType > locs.size() - 1) {
                rrcbe.signType = locs.size() - 1;
            }
            else{
                rrcbe.signTexture = locs.get(rrcbe.signType);
            }
        }*/

        // hard reset tick counter
        if(rrcbe.ticks >= 32767){
            rrcbe.ticks = 0;
        }
    }

    public static void clientTick(Level lvl, BlockPos bp, BlockState bs, DynamicRoadSignBE rrcbe){
        if(ThingamajigsLongRoads.badFileAccessFlag){
            return;
        }
        ++rrcbe.ticks;
        // hard reset tick counter
        if(rrcbe.ticks >= 32767){
            rrcbe.ticks = 0;
        }
    }
}
