package com.mcmiddleearth.command.argument;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.stream.Collectors;

public class BukkitOnlinePlayerArgumentType extends AbstractPlayerArgumentType {

    @Override
    protected Collection<String> getPlayerSuggestions() {
        return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
    }

}
