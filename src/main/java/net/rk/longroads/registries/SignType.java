package net.rk.longroads.registries;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceLocation;

public record SignType(ResourceLocation assetId, String translationKey, String signModeltype){
    public static final Codec<SignType> CODEC = RecordCodecBuilder.create(
            codecBuilderInstance -> codecBuilderInstance.group(
                            ResourceLocation.CODEC.fieldOf("asset_id").forGetter(SignType::assetId),
                            Codec.STRING.fieldOf("translation_key").forGetter(SignType::translationKey),
                            Codec.STRING.fieldOf("sign_model_type").forGetter(SignType::signModeltype)
                    ).apply(codecBuilderInstance,SignType::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SignType> DIRECT_STREAM_CODEC =
            StreamCodec.composite(
                    ResourceLocation.STREAM_CODEC, SignType::assetId,
                    ByteBufCodecs.STRING_UTF8, SignType::translationKey,
                    ByteBufCodecs.STRING_UTF8, SignType::signModeltype,
                    SignType::new);

    public static final Codec<Holder<SignType>> HOLDER_CODEC =
            RegistryFileCodec.create(TLRRegistries.SIGN_TYPE, CODEC);

    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<SignType>> STREAM_CODEC =
            ByteBufCodecs.holder(TLRRegistries.SIGN_TYPE, DIRECT_STREAM_CODEC);

    @Override
    public ResourceLocation assetId() {
        return assetId;
    }

    @Override
    public String translationKey() {
        return translationKey;
    }

    @Override
    public String signModeltype() {
        return signModeltype;
    }
}
