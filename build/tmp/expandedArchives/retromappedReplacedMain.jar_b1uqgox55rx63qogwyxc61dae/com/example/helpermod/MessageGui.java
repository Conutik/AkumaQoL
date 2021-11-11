package com.example.helpermod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MessageGui extends Gui {
    private static String text = "Test Text";
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
        fontRenderer.func_175063_a(text, 10, 20, color);
    }
}
