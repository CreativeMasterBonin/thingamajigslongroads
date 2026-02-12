package net.rk.longroads.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class TLRServerConfig {
    public static class ServerConfig{
        public final ModConfigSpec.ConfigValue<Integer> maxAmountOfRoadMarkingPerPaint;

        public ServerConfig(ModConfigSpec.Builder builder){
            builder.translation("title.thingamajigs.options").push("General");
            this.maxAmountOfRoadMarkingPerPaint =
                    builder.translation("config.thingamajigs.max_amount_marking_per_paint")
                            .defineInRange("maxAmountOfRoadMarkingPerPaint",10,2,16);
            builder.pop();
        }
    }

    public static final ModConfigSpec SPEC; // used in thingamajigs setup phase
    public static final ServerConfig SERVER; // used internally in this class

    static {
        final Pair<ServerConfig,ModConfigSpec> pair = new ModConfigSpec.Builder().configure(ServerConfig::new);
        SPEC = pair.getRight();
        SERVER = pair.getLeft();
    }
}
