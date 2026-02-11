package net.rk.longroads.network;

import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.rk.longroads.ThingamajigsLongRoads;

public class TLRHandler {
    public static void register(final RegisterPayloadHandlersEvent event){
        final PayloadRegistrar regex_reg = event.registrar(ThingamajigsLongRoads.MODID);
    }
}
