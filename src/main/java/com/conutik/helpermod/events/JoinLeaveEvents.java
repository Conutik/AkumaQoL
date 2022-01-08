package com.conutik.helpermod.events;

import com.conutik.helpermod.HelperMod;
import com.conutik.helpermod.rpc;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class JoinLeaveEvents {

    @SubscribeEvent
    public void onJoin(PlayerEvent.PlayerLoggedInEvent e) {
        HelperMod.rpcs.setDetailsLine("Playing" + Minecraft.getMinecraft().getCurrentServerData().serverIP);
    }

    @SubscribeEvent
    public void onLeave(PlayerEvent.PlayerLoggedOutEvent e) {
        HelperMod.rpcs.setDetailsLine("IDLE");
    }
}
