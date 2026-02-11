package net.rk.longroads.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.rk.longroads.ThingamajigsLongRoads;
import net.rk.longroads.registries.SignType;
import net.rk.longroads.registries.TLRRegistries;

public class SignTypeKeys {
    public static final ResourceKey<SignType> PlACEHOLDER = ResourceKey.create(
            TLRRegistries.SIGN_TYPE,
            ResourceLocation.fromNamespaceAndPath(ThingamajigsLongRoads.MODID,"placeholder")
    );
}
