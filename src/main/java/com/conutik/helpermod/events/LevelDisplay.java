package com.conutik.helpermod.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class LevelDisplay {

    private final int color = 0xFFFFFF; // Unless you wanna render transparent text, change to 0xFFFFFFFF
    private String text1, text2 = "";
    private FontRenderer fontRenderer;

    private void verifyRenderer() {
        if (fontRenderer != null) return;
        Minecraft minecraft = Minecraft.getMinecraft();
        fontRenderer = minecraft.fontRendererObj;
    }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent.Post event) {
        verifyRenderer();
        fontRenderer.drawStringWithShadow(text1, 10, 10, color);
        fontRenderer.drawStringWithShadow(text2, 10, 20, color);
    }

    @SubscribeEvent
    public void onPickaxeHold(TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().thePlayer == null) return;

        ServerData serverData = Minecraft.getMinecraft().getCurrentServerData();
        if (serverData == null) return;
        String serverIp = serverData.serverIP;
        ScoreObjective sidebarObjective = Minecraft.getMinecraft().theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        String level = "";
        String pickXP = "";

        if (serverIp.equals("akumamc.net")) {
            ItemStack stack = Minecraft.getMinecraft().thePlayer.getHeldItem();
            if (stack != null && stack.hasTagCompound() && stack.getItem() == Items.diamond_pickaxe) {
                NBTTagCompound tag = stack.getTagCompound();
                NBTTagList lore = tag.getCompoundTag("display").getTagList("Lore", Constants.NBT.TAG_STRING);
                if (!text1.equals(lore.getStringTagAt(lore.tagCount() - 3))) {
                    level = lore.getStringTagAt(lore.tagCount() - 3);
                    pickXP = lore.getStringTagAt(lore.tagCount() - 2);
                }
            }
        }

        text1 = level;
        text2 = pickXP;
    }

}
