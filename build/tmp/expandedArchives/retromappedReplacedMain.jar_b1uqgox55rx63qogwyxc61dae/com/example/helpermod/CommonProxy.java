package com.example.helpermod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CommonProxy {
    /**
     * We will call this method from our main mod class' FMLPreInitializationEvent method
     */
    public void initialize() {
// since we are not registering a tick handler that ticks on the server, we will not put anything here for now
// but if you had a WorldTickEvent or PlayerTickEvent, for example, this is where you should register it
// if you try to register the RenderTickHandler here, your game WILL crash
    }
}

class ClientProxy extends CommonProxy {
    // Our ClientProxy method only gets run on the client side, so it is safe to register our RenderTickHandler here
    @Override
    public void initialize() {
// calling super will register any 2-sided tick handlers you have that are registered in the CommonProxy
// this is important since the CommonProxy will only register it on the server side, and you will need it
// registered on the client as well; however, we do not have any at this point
        super.initialize();

// here we register our RenderTickHandler - be sure to pass in the instance of Minecraft!
        FMLCommonHandler.instance().bus().register(new RenderTickHandler(Minecraft.getMinecraft()));

// this is also an ideal place to register things like KeyBindings
    }
}
