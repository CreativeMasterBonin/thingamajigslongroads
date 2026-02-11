package net.rk.longroads.network.packet;

import net.minecraft.core.SectionPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.logging.Logger;

public class DynamicSignPacket{
    /*
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
            dsbe.updateBlock();
        }
        else{
            dsbe.yAngle = payload.rotation();

            int tempA = payload.sign_type();

            if(tempA > dsbe.locs.size() - 1){
                tempA = dsbe.locs.size() - 1;
            }
            else if(tempA < 0){
                tempA = 0;
            }
            else{
                dsbe.signType = tempA;
            }

            dsbe.updateBlock();
        }
        return;
    }

     */
}
