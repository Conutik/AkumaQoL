package com.conutik.helpermod.events;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatClutter {

    private final String[] filterTexts = {
            "Lucky activated and gave",
            "Your PUMPKINATOR enchant has earned you 100 PUMPKINS",
            "Token merchant activated and gave you",
            "Your Crystal Finder has found you",
            "Your Booster enchant will now boost you for",
            "Your Crystal Merchant has found you an additional"
    };

    @SubscribeEvent
    public void procMsg(ClientChatReceivedEvent e) {
        for (String text : filterTexts) {
            if (e.message.getUnformattedText().contains(text)) {
                e.setCanceled(true);
                return;
            }
        }
    }
}
