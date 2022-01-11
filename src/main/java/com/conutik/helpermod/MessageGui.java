package com.conutik.helpermod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MessageGui extends Gui {
    private final String text = "Test Text";
    private final int color = 0xFFFFFF; // Unless you wanna render transparent text, change to 0xFFFFFFFF
    private FontRenderer fontRenderer;

    private void verifyRenderer() {
        if (fontRenderer != null) return;
        Minecraft minecraft = Minecraft.getMinecraft();
        fontRenderer = minecraft.fontRendererObj;
    }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent.Post event) {
        verifyRenderer();
        fontRenderer.drawStringWithShadow(text, 10, 20, color);
    }
}
