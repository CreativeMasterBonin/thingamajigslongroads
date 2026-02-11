package net.rk.longroads.network.record;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.rk.longroads.ThingamajigsLongRoads;

public record DynamicSignPayload(BlockPos bp, float rotation, int id, boolean updateSelf) implements CustomPacketPayload{
    public static final CustomPacketPayload.Type<DynamicSignPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(ThingamajigsLongRoads.MODID,"dynamic_sign_update"));

    public static final StreamCodec<FriendlyByteBuf, DynamicSignPayload> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, DynamicSignPayload::bp,
            ByteBufCodecs.FLOAT, DynamicSignPayload::rotation,
            ByteBufCodecs.INT, DynamicSignPayload::id,
            ByteBufCodecs.BOOL,DynamicSignPayload::updateSelf,
            DynamicSignPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
