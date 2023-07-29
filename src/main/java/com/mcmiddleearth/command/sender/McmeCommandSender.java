package com.mcmiddleearth.command.sender;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;

public interface McmeCommandSender {

    void sendMessage(BaseComponent... message);

    boolean hasPermission(String permissionNode);

    default void sendMessage(String message) {
        sendMessage(new ComponentBuilder(message).color(ChatColor.AQUA).create());
    }

    default void sendError(String message) {
        sendMessage(new ComponentBuilder(message).color(ChatColor.RED).create());
    }

}
