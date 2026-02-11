package net.rk.longroads.registries;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.rk.longroads.ThingamajigsLongRoads;

public class TLRRegistries{
    public static final ResourceKey<Registry<SignType>> SIGN_TYPE = key("sign_type");

    private static <T> ResourceKey<Registry<T>> key(String name) {
        return ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(ThingamajigsLongRoads.MODID, name));
    }
}
