package com.conutik.helpermod.events;

import com.conutik.helpermod.APIHandler;
import com.conutik.helpermod.HelperMod;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;

public class VersionChecker {
    static boolean updateChecked = false;

    @SubscribeEvent
    public void updateCheck(PlayerEvent.PlayerLoggedInEvent event) {
        if (!updateChecked) {
            new Thread(() -> {
                EntityPlayer player = Minecraft.getMinecraft().thePlayer;

                System.out.println("Checking for updates...");
                JsonObject latestRelease = APIHandler.getResponse("https://api.github.com/repos/Conutik/AkumaQoL/releases/latest");

                String latestTag = latestRelease.get("tag_name").getAsString();
                DefaultArtifactVersion currentVersion = new DefaultArtifactVersion(HelperMod.VERSION);
                DefaultArtifactVersion latestVersion = new DefaultArtifactVersion(latestTag.substring(1));

                if (currentVersion.compareTo(latestVersion) < 0) {
                    String releaseURL = latestRelease.get("html_url").getAsString();

                    ChatComponentText update = new ChatComponentText(EnumChatFormatting.GREEN + "" + EnumChatFormatting.BOLD + "  [UPDATE]  ");
                    update.setChatStyle(update.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, releaseURL)));

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "AkumaQoL is outdated. Please update to " + latestTag + ".\n").appendSibling(update));
                }
            }).start();
        }
    }
}
