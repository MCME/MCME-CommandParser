package com.mcmiddleearth.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class BukkitOfflinePlayerArgument extends AbstractPlayerArgumentType {

    OfflinePlayer[] cache;
    long lastUpdate;

    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        return reader.readUnquotedString();
    }

    @Override
    protected Collection<String> getPlayerSuggestions() {
        if(cache==null || System.currentTimeMillis() > lastUpdate + 20000) {
            cache = Bukkit.getOfflinePlayers();
        }
        return Arrays.stream(cache).map(OfflinePlayer::getName).collect(Collectors.toList());
    }
}
