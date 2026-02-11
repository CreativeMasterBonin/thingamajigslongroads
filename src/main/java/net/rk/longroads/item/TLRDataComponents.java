package net.rk.longroads.item;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rk.longroads.ThingamajigsLongRoads;

public class TLRDataComponents{
    public static final DeferredRegister<DataComponentType<?>> COMPONENTS =
            DeferredRegister.createDataComponents(ThingamajigsLongRoads.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> ROAD_MARKING_PATTERN =
            COMPONENTS.register("road_marking_pattern",
                    () -> DataComponentType.<Integer>builder()
                            .persistent(Codec.INT).networkSynchronized(ByteBufCodecs.VAR_INT).build());
}
