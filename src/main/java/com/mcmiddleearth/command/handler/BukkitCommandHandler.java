package com.mcmiddleearth.command.handler;

import com.google.common.base.Joiner;
import com.mcmiddleearth.command.SimpleTabCompleteRequest;
import com.mcmiddleearth.command.TabCompleteRequest;
import com.mcmiddleearth.command.sender.BukkitConsole;
import com.mcmiddleearth.command.sender.BukkitPlayer;
import com.mcmiddleearth.command.sender.McmeCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

/**
 * Command handler for Bukkit plugins:
 * 1. Create a subclass which creates a command tree
 * 2. Set as CommandExecutor and TabCompleter for a command of your plugin
 */
public abstract class BukkitCommandHandler extends AbstractCommandHandler implements TabExecutor {

    public BukkitCommandHandler(String command) {
        super(command);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        McmeCommandSender wrappedSender;
        if(commandSender instanceof Player) {
            wrappedSender = new BukkitPlayer((Player)commandSender);
        } else if(commandSender instanceof ConsoleCommandSender) {
            wrappedSender = new BukkitConsole((ConsoleCommandSender) commandSender);
        } else {
            return false;
        }
        execute(wrappedSender, args);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        McmeCommandSender wrappedSender;
        if(commandSender instanceof Player) {
            wrappedSender = new BukkitPlayer((Player)commandSender);
        } else if(commandSender instanceof ConsoleCommandSender) {
            wrappedSender = new BukkitConsole((ConsoleCommandSender) commandSender);
        } else {
            return Collections.emptyList();
        }
        TabCompleteRequest request = new SimpleTabCompleteRequest(wrappedSender,
                String.format("/%s %s", alias, Joiner.on(' ').join(args)));
        onTabComplete(request);
        return request.getSuggestions();
    }
}
