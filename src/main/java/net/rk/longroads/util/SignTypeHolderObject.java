package net.rk.longroads.util;

import com.google.common.collect.ImmutableList;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.rk.longroads.registries.SignType;
import net.rk.longroads.registries.TLRRegistries;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

public record SignTypeHolderObject(List<HolderSignType> typesHolderObjectList) {
    private static final Logger signTypeHolderObjectLogger = LogUtils.getLogger();
    public static final SignTypeHolderObject NOTHING = new SignTypeHolderObject(List.of());

    @Override
    public String toString() {
        return "SignTypeHolderObject has list of: " + this.typesHolderObjectList().stream().toList();
    }

    public static SignTypeHolderObject makeDefaultSign(Level level){
        HolderGetter<SignType> registry = level.registryAccess().lookupOrThrow(TLRRegistries.SIGN_TYPE);
        return new SignTypeHolderObject.Builder().addIfRegistered(registry,SignTypeKeys.PlACEHOLDER).buildSignTypeHolderBuilder();
    }

    public static final Codec<SignTypeHolderObject> CODEC =
            SignTypeHolderObject.HolderSignType.CODEC.listOf().xmap(SignTypeHolderObject::new, SignTypeHolderObject::typesHolderObjectList);

    public static final StreamCodec<RegistryFriendlyByteBuf, SignTypeHolderObject> STREAM_CODEC =
            SignTypeHolderObject.HolderSignType.STREAM_CODEC.apply(ByteBufCodecs.list()).map(SignTypeHolderObject::new, SignTypeHolderObject::typesHolderObjectList);

    public static record HolderSignType(Holder<SignType> signType){
        public static final Codec<SignTypeHolderObject.HolderSignType> CODEC = RecordCodecBuilder.create((builder) -> {
            return builder.group(SignType.HOLDER_CODEC.fieldOf("signType").forGetter(SignTypeHolderObject.HolderSignType::signType)
            ).apply(builder, SignTypeHolderObject.HolderSignType::new);
        });

        public static final StreamCodec<RegistryFriendlyByteBuf,HolderSignType> STREAM_CODEC =
                StreamCodec.composite(SignType.STREAM_CODEC,
                        HolderSignType::signType,HolderSignType::new);

        public HolderSignType(Holder<SignType> signType){
            this.signType = signType;
        }

        public MutableComponent getDescription(){
            return Component.literal(this.toString()).append(Component.translatable(this.signType.value().translationKey()));
        }

        public SignType getSignType(){
            return this.signType.value();
        }
    }

    public static class Builder{
        private final ImmutableList.Builder<HolderSignType> holderSignTypesList = ImmutableList.builder();

        public SignTypeHolderObject.Builder add(Holder<SignType> holder) {
            return this.add(new SignTypeHolderObject.HolderSignType(holder));
        }

        public SignTypeHolderObject.Builder add(SignTypeHolderObject.HolderSignType holders) {
            this.holderSignTypesList.add(holders);
            return this;
        }

        public SignTypeHolderObject.Builder addAll(SignTypeHolderObject holders) {
            this.holderSignTypesList.addAll(holders.typesHolderObjectList);
            return this;
        }

        public SignTypeHolderObject.Builder addIfRegistered(HolderGetter<SignType> signTypes, ResourceKey<SignType> signTypeKey) {
            Optional<Holder.Reference<SignType>> optional = signTypes.get(signTypeKey);
            if (optional.isEmpty()) {
                signTypeHolderObjectLogger.warn("Unable to find SignType with ID: '{}'", signTypeKey.location());
                return this;
            }
            else {
                return this.add(optional.get());
            }
        }

        public SignTypeHolderObject buildSignTypeHolderBuilder() {
            return new SignTypeHolderObject(this.holderSignTypesList.build());
        }
    }
}
