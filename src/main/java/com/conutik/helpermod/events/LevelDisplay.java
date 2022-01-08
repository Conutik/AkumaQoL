package com.conutik.helpermod.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class LevelDisplay {

    private static String text1, text2 = "";
    private final int color = 0xFFFFFF;
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

        String serverIp = Minecraft.getMinecraft().getCurrentServerData().serverIP;
        ScoreObjective sidebarObjective = Minecraft.getMinecraft().theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        String level, pickXP;

        if (serverIp.equals("akumamc.net")) {
            ItemStack stack = Minecraft.getMinecraft().thePlayer.getHeldItem();
            if (stack != null && stack.hasTagCompound() && stack.getItem().getRegistryName().equals("minecraft:diamond_pickaxe") && !text1.equals(stack.getTagCompound().getCompoundTag("display").getTagList("Lore", 8).getStringTagAt(stack.getTagCompound().getCompoundTag("display").getTagList("Lore", 8).tagCount() - 3))) {
                NBTTagCompound tag = stack.getTagCompound();

                NBTTagCompound ea = tag.getCompoundTag("display");

                NBTTagList lore = ea.getTagList("Lore", 8);
                level = lore.getStringTagAt(lore.tagCount() - 3);
                pickXP = lore.getStringTagAt(lore.tagCount() - 2);

                text1 = level;
                text2 = pickXP;

                fontRenderer.drawStringWithShadow(text1, 10, 10, color);
                fontRenderer.drawStringWithShadow(text2, 10, 20, color);

            } else {
                text1 = "";
                text2 = "";
            }
        } else {
            text1 = "";
            text2 = "";
        }
    }

}
