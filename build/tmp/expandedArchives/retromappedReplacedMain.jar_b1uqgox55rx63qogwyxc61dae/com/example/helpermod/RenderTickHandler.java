package com.example.helpermod;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTickHandler {

    private Minecraft mc;

    public RenderTickHandler(Minecraft mc) {
        this.mc = mc;
    }


    @SubscribeEvent
    public void onPickaxeHold(RenderTickHandler event) {
        if (Minecraft.func_71410_x().field_71439_g == null) return;

        String serverIp = Minecraft.func_71410_x().func_147104_D().field_78845_b;
        ScoreObjective sidebarObjective = Minecraft.func_71410_x().field_71441_e.func_96441_U().func_96539_a(1);
        String objectiveName = "";
        String current, maximum, level;
        if (sidebarObjective != null) {
            objectiveName = sidebarObjective.func_96678_d();
        }

        if (serverIp.equals("mc.akumamc.net")) {

            if(!objectiveName.equals("§4§lAkuma§f§lPrison")) return;

            ItemStack stack = Minecraft.func_71410_x().field_71439_g.func_70694_bm();
            if (stack != null && stack.func_77942_o()) {
                NBTTagCompound tag = stack.func_77978_p();


                NBTTagCompound ea = tag.func_74775_l("display");

                NBTTagList lore = ea.func_150295_c("Lore", 14); // need 13 and 12

                level = lore.func_179238_g(12).toString();

                Minecraft.func_71410_x().field_71439_g.func_71165_d("" + level);

//                if(ea.hasKey("mined_crops", 99)) {
//                    counter = ea.getInteger("mined_crops");
//                    cultivating = ea.getInteger("farmed_cultivating");
//                    counterQueue.add(0, counter);
//                } else if (ea.hasKey("farmed_cultivating", 99)) {
//                    counter = ea.getInteger("farmed_cultivating");
//                    cultivating = ea.getInteger("farmed_cultivating");
//                    counterQueue.add(0, counter);
//                }
            }
        }
    }
}
