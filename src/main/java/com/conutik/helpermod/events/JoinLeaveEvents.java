package com.conutik.helpermod.events;

import com.conutik.helpermod.HelperMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class JoinLeaveEvents {

    @SubscribeEvent
    public void onJoin(PlayerEvent.PlayerLoggedInEvent e) {
        ServerData serverData = Minecraft.getMinecraft().getCurrentServerData();
        if (serverData == null) return;
        HelperMod.rpcs.setDetailsLine("Playing" + serverData.serverIP);
    }

    @SubscribeEvent
    public void onLeave(PlayerEvent.PlayerLoggedOutEvent e) {
        HelperMod.rpcs.setDetailsLine("IDLE");
    }
}
