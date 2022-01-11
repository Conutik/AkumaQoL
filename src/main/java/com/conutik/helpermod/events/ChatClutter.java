package com.conutik.helpermod.events;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatClutter {

    @SubscribeEvent
    public void procMsg(ClientChatReceivedEvent e) {
        if (e.message.getUnformattedText().contains("Lucky activated and gave") || e.message.getUnformattedText().contains("Your PUMPKINATOR enchant has earned you 100 PUMPKINS") || e.message.getUnformattedText().contains("Token merchant activated and gave you") || e.message.getUnformattedText().contains("Your Crystal Finder has found you") || e.message.getUnformattedText().contains("Your Booster enchant will now boost you for") || e.message.getUnformattedText().contains("Your Crystal Merchant has found you an additional")) {
            e.setCanceled(true);
        }
    }
}
