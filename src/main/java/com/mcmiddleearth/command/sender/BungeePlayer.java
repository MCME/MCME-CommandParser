package com.mcmiddleearth.command.sender;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeePlayer implements McmeCommandSender {

    ProxiedPlayer player;

    public BungeePlayer(ProxiedPlayer player) {
        this.player = player;
    }

    public boolean hasPermission(String permissionNode) {
        return player.hasPermission(permissionNode);
    }

    @Override
    public void sendMessage(BaseComponent... message) {
        player.sendMessage(message);

    }
}
