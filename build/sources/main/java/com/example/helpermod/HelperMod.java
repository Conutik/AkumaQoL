package com.example.helpermod;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = HelperMod.MODID, version = HelperMod.VERSION)
public class HelperMod {
    public static final String MODID = "akuma_helper_mod";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);

    }

    private static String text1, text2 = "";
    private static int color = 0xFFFFFF;
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
        String objectiveName = "";
        String level, pickXP;
        if (sidebarObjective != null) {
            objectiveName = sidebarObjective.getDisplayName();
        }
//        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(serverIp));

        if (serverIp.equals("akumamc.net")) {
//            if(!objectiveName.equals("§4§lAkuma§f§lPrison")) return;
            ItemStack stack = Minecraft.getMinecraft().thePlayer.getHeldItem();
            if (stack != null && stack.hasTagCompound() && stack.getItem().getRegistryName().equals("minecraft:diamond_pickaxe")) {
                NBTTagCompound tag = stack.getTagCompound();

                NBTTagCompound ea = tag.getCompoundTag("display");

//                NBTTagList lore = ea.getTagList("Lore", 12); // need 13 and 12

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
