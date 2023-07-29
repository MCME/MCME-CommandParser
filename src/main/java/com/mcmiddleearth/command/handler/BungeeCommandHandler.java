package com.mcmiddleearth.command.handler;

import com.google.common.base.Joiner;
import com.mcmiddleearth.command.SimpleTabCompleteRequest;
import com.mcmiddleearth.command.TabCompleteRequest;
import com.mcmiddleearth.command.builder.HelpfulLiteralBuilder;
import com.mcmiddleearth.command.sender.BungeeConsole;
import com.mcmiddleearth.command.sender.BungeePlayer;
import com.mcmiddleearth.command.sender.McmeCommandSender;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.Collections;

/**
 * Command handler for Bungee plugins:
 * 1. Create a subclass which creates a command tree
 * 2. Register as command in your Bungee plugin
 */
public abstract class BungeeCommandHandler extends Command implements TabExecutor {

    AbstractCommandHandler commandHandler;

    public BungeeCommandHandler(String command) {
        super(command);
        commandHandler = new AbstractCommandHandler(command) {
            @Override
            protected HelpfulLiteralBuilder createCommandTree(HelpfulLiteralBuilder baseCommandNodeBuilder) {
                return BungeeCommandHandler.this.createCommandTree(baseCommandNodeBuilder);
            }
        };
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        McmeCommandSender wrappedSender;
        if(commandSender instanceof ProxiedPlayer) {
            wrappedSender = new BungeePlayer((ProxiedPlayer)commandSender);
        } else if(commandSender.getName().equals(ProxyServer.getInstance().getConsole().getName())) {
            wrappedSender = new BungeeConsole(commandSender);
        } else {
            return;
        }
        commandHandler.execute(wrappedSender, args);
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender commandSender, String[] args) {
            McmeCommandSender wrappedSender;
            if(commandSender instanceof ProxiedPlayer) {
                wrappedSender = new BungeePlayer((ProxiedPlayer)commandSender);
            } else if(commandSender.getName().equals(ProxyServer.getInstance().getConsole().getName())) {
                wrappedSender = new BungeeConsole(commandSender);
            } else {
                return Collections.emptyList();
            }
            TabCompleteRequest request = new SimpleTabCompleteRequest(wrappedSender,
                    String.format("/%s %s", commandHandler.getCommand(), Joiner.on(' ').join(args)));
            commandHandler.onTabComplete(request);
            return request.getSuggestions();
    }

    protected abstract HelpfulLiteralBuilder createCommandTree(HelpfulLiteralBuilder baseCommandNodeBuilder);

}
