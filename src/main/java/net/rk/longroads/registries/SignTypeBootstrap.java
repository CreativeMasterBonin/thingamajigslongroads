package net.rk.longroads.registries;

import com.mojang.logging.LogUtils;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceLocation;
import net.rk.longroads.util.SignTypeKeys;
import org.slf4j.Logger;

public class SignTypeBootstrap {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void bootstrap(BootstrapContext<SignType> bootstrapContext){
        LOGGER.info("Long Roads' Bootstrap is adding internal data driven sign types");
        bootstrapContext.register(SignTypeKeys.PlACEHOLDER,
                new SignType(ResourceLocation.parse("thingamajigslongroads:sign_error"),
                        "sign_type.placeholder.name",
                        "square"));
    }
}
