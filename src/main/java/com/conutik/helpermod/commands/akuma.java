package com.conutik.helpermod.commands;


import com.conutik.helpermod.HelperMod;
import com.conutik.helpermod.Settings;
import gg.essential.api.utils.GuiUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class akuma extends CommandBase {

    @Override
    public String getCommandName() {
        return "akuma";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "akuma";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        GuiUtil.open(HelperMod.settings.gui());
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
