package com.conutik.helpermod.commands;


import com.conutik.helpermod.HelperMod;
import gg.essential.api.utils.GuiUtil;
import gg.essential.vigilance.gui.SettingsGui;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class Akuma extends CommandBase {

    @Override
    public String getCommandName() {
        return "akuma";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "akuma";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        SettingsGui gui = HelperMod.settings.gui();
        if (gui != null) {
            GuiUtil.open(gui);
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }


}
