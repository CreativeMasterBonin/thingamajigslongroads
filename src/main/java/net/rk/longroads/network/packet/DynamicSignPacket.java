package net.rk.longroads.network.packet;

import net.minecraft.core.SectionPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.rk.longroads.entity.blockentity.custom.DynamicRoadSignBE;
import net.rk.longroads.network.record.DynamicSignPayload;

import java.util.logging.Logger;

public class DynamicSignPacket{
    public static final DynamicSignPacket INSTANCE = new DynamicSignPacket();

    public static DynamicSignPacket get(){return INSTANCE;}

    public void handle(final DynamicSignPayload payload, final IPayloadContext context){
        Player ply = context.player();
        Level lvl = ply.level();
        if(ply == null){
            return;
        }

        if(!lvl.hasChunk(
                SectionPos.blockToSectionCoord(payload.bp().getX()),
                SectionPos.blockToSectionCoord(payload.bp().getZ()))){
            return;
        }

        DynamicRoadSignBE dsbe = (DynamicRoadSignBE)lvl.getBlockEntity(payload.bp());

        if(dsbe == null){
            Logger.getAnonymousLogger().warning("Dynamic Sign BE at: " + payload.bp() + " is null! This is not normal!");
            return;
        }

        if(payload.updateSelf()){
            dsbe.indexId = payload.id();
            dsbe.updateSign();
            dsbe.updateBlock();
        }
        else{
            dsbe.yAngle = payload.rotation();
            dsbe.updateBlock();
        }
        return;
    }
}
