package net.rk.longroads.network;

import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.rk.longroads.ThingamajigsLongRoads;
import net.rk.longroads.network.packet.DynamicSignPacket;
import net.rk.longroads.network.record.DynamicSignPayload;

public class TLRHandler {
    public static void register(final RegisterPayloadHandlersEvent event){
        final PayloadRegistrar regex_reg = event.registrar(ThingamajigsLongRoads.MODID);
        regex_reg.playToServer(DynamicSignPayload.TYPE,
                DynamicSignPayload.STREAM_CODEC,
                DynamicSignPacket.get()::handle);
    }
}
