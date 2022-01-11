package com.conutik.helpermod;

import com.conutik.helpermod.commands.akuma;
import com.conutik.helpermod.events.JoinLeaveEvents;
import com.conutik.helpermod.events.LevelDisplay;
import com.conutik.helpermod.events.VersionChecker;
import com.conutik.helpermod.events.ChatClutter;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = HelperMod.MODID, version = HelperMod.VERSION)
public class HelperMod {
    public static final String MODID = "AkumaQoL";
    public static final String VERSION = "1.2";
    public static Rpc rpcs;
    public static Settings settings = new Settings();

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        rpcs = new Rpc();
        rpcs.start();
        settings.preload();

        MinecraftForge.EVENT_BUS.register(new ChatClutter());
        MinecraftForge.EVENT_BUS.register(new JoinLeaveEvents());
        MinecraftForge.EVENT_BUS.register(new LevelDisplay());
        MinecraftForge.EVENT_BUS.register(new VersionChecker());
        ClientCommandHandler.instance.registerCommand(new akuma());

    }

}
