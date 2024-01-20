package com.mcmiddleearth.command.sender;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.command.ConsoleCommandSender;

public class BukkitConsole implements McmeCommandSender {

    ConsoleCommandSender console;

    public BukkitConsole(ConsoleCommandSender sender) {
        console = sender;
    }

    public boolean hasPermission(String permissionNode) {
        return true;
    }

    @Override
    public void sendMessage(BaseComponent[] message) {
        console.sendMessage(message);
    }
}
