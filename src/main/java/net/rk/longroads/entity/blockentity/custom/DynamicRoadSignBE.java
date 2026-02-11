package net.rk.longroads.entity.blockentity.custom;

import com.mojang.logging.LogUtils;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.longroads.entity.blockentity.TLRBlockEntity;
import net.rk.longroads.registries.SignType;
import net.rk.longroads.registries.TLRRegistries;
import net.rk.longroads.util.SignTypeHolderObject;
import net.rk.longroads.util.Utilities;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DynamicRoadSignBE extends BlockEntity{
    BlockPos bp;
    public float yAngle = 0.0f;
    public int ticks;

    public String signTexture = Utilities.missingLocation;
    public String modelType = "square";
    public final String fallbackSignTexture = Utilities.missingLocation;

    public SignTypeHolderObject holderList;
    public int indexId;


    public void updateSign(){
        List<SignType> list = this.level.registryAccess().registryOrThrow(TLRRegistries.SIGN_TYPE).stream().toList();

        if(Utilities.verboseLogging)
            LogUtils.getLogger().info("The indexID is now: {}", indexId);

        try{
            holderList = new SignTypeHolderObject.Builder()
                    .add(new SignTypeHolderObject.HolderSignType(Holder.direct(list.get(this.indexId))))
                    .buildSignTypeHolderBuilder();
            if(Utilities.verboseLogging)
                LogUtils.getLogger().info("new list holder {}", holderList.toString());
        }
        catch (Exception e){
            LogUtils.getLogger().error("Something went wrong when trying to apply a change to SignType in DynamicRoadSignBE! ERR: {}",e.getMessage());
            holderList = SignTypeHolderObject.makeDefaultSign(level);
        }

        String tempTexture = "thingamajigslongroads:textures/entity/signs/" + holderList.typesHolderObjectList().get(0).getSignType().assetId().getPath() + ".png";
        signTexture = tempTexture;
        modelType = holderList.typesHolderObjectList().get(0).getSignType().signModeltype();
    }

    public DynamicRoadSignBE(BlockPos pos, BlockState blockState){
        super(TLRBlockEntity.DYNAMIC_ROAD_SIGN_BE.get(), pos, blockState);
        bp = pos;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if(level != null){
            if(holderList == null){
                holderList = SignTypeHolderObject.makeDefaultSign(level);
            }
        }
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
        return this.saveWithoutMetadata(lp);
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
        pTag.putString("model_type",modelType);

        if (!(this.holderList == null)) {
            pTag.put("sign_types", SignTypeHolderObject.CODEC.encodeStart(slp.createSerializationContext(NbtOps.INSTANCE),this.holderList).getOrThrow());
        }
        else{
            holderList = SignTypeHolderObject.makeDefaultSign(this.level);
            pTag.put("sign_types", SignTypeHolderObject.CODEC.encodeStart(slp.createSerializationContext(NbtOps.INSTANCE),this.holderList).getOrThrow());
        }
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider lp) {
        yAngle = pTag.getFloat("y_angle");

        if(pTag.contains("sign_texture")){
            signTexture = pTag.getString("sign_texture");
        }

        if(pTag.contains("model_type")){
            modelType = pTag.getString("model_type");
        }

        if (pTag.contains("sign_types")) {
            SignTypeHolderObject.CODEC
                    .parse(lp.createSerializationContext(NbtOps.INSTANCE), pTag.get("sign_types"))
                    .resultOrPartial(str -> LogUtils.getLogger().error("Failed to parse sign types: '{}'", str))
                    .ifPresent(str -> this.holderList = str);
        }
    }

    public static void serverTick(Level slvl, BlockPos sbp, BlockState sbs, DynamicRoadSignBE rrcbe){
        ++rrcbe.ticks;
        // hard reset tick counter
        if(rrcbe.ticks >= 32767){
            rrcbe.ticks = 0;
        }
    }

    public static void clientTick(Level lvl, BlockPos bp, BlockState bs, DynamicRoadSignBE rrcbe){
        ++rrcbe.ticks;
        // hard reset tick counter
        if(rrcbe.ticks >= 32767){
            rrcbe.ticks = 0;
        }
    }
}
