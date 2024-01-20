package com.mcmiddleearth.command.sender;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;

public class BukkitPlayer implements McmeCommandSender {

    Player player;

    public BukkitPlayer(Player player) {
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
