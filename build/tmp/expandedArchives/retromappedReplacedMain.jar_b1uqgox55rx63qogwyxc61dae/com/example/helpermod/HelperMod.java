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
        Minecraft minecraft = Minecraft.func_71410_x();
        fontRenderer = minecraft.field_71466_p;
    }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent.Post event) {
        verifyRenderer();
        fontRenderer.func_175063_a(text1, 10, 10, color);
        fontRenderer.func_175063_a(text2, 10, 20, color);
    }

    @SubscribeEvent
    public void onPickaxeHold(TickEvent.ClientTickEvent event) {
        if (Minecraft.func_71410_x().field_71439_g == null) return;

        String serverIp = Minecraft.func_71410_x().func_147104_D().field_78845_b;
        ScoreObjective sidebarObjective = Minecraft.func_71410_x().field_71441_e.func_96441_U().func_96539_a(1);
        String objectiveName = "";
        String level, pickXP;
        if (sidebarObjective != null) {
            objectiveName = sidebarObjective.func_96678_d();
        }
//        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(serverIp));

        if (serverIp.equals("akumamc.net")) {
//            if(!objectiveName.equals("§4§lAkuma§f§lPrison")) return;
            ItemStack stack = Minecraft.func_71410_x().field_71439_g.func_70694_bm();
            if (stack != null && stack.func_77942_o() && stack.func_77973_b().getRegistryName().equals("minecraft:diamond_pickaxe")) {
                NBTTagCompound tag = stack.func_77978_p();

                NBTTagCompound ea = tag.func_74775_l("display");

//                NBTTagList lore = ea.getTagList("Lore", 12); // need 13 and 12

                NBTTagList lore = ea.func_150295_c("Lore", 8);
                level = lore.func_150307_f(lore.func_74745_c() - 3);
                pickXP = lore.func_150307_f(lore.func_74745_c() - 2);

                text1 = level;
                text2 = pickXP;

                fontRenderer.func_175063_a(text1, 10, 10, color);
                fontRenderer.func_175063_a(text2, 10, 20, color);

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
