package com.mcmiddleearth.command.sender;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;

public class BungeeConsole implements McmeCommandSender {

    CommandSender console;

    public BungeeConsole(CommandSender commandSender) {
        console = commandSender;
    }

    public boolean hasPermission(String permissionNode) {
        return true;
    }

    @Override
    public void sendMessage(BaseComponent[] message) {
        console.sendMessage(message);
    }
}
