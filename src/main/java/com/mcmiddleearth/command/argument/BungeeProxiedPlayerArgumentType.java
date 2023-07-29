package com.mcmiddleearth.command.argument;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;

import java.util.Collection;
import java.util.stream.Collectors;

public class BungeeProxiedPlayerArgumentType extends AbstractPlayerArgumentType {

    @Override
    protected Collection<String> getPlayerSuggestions() {
        return ProxyServer.getInstance().getPlayers().stream().map(CommandSender::getName).collect(Collectors.toList());
    }
}
