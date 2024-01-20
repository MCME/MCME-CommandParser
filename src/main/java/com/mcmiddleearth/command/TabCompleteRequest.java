package com.mcmiddleearth.command;

import com.mcmiddleearth.command.sender.McmeCommandSender;

import java.util.List;

public interface TabCompleteRequest {

    void setCancelled(boolean cancelled);

    boolean isCancelled();

    McmeCommandSender getSender();

    List<String> getSuggestions();

    String getCursor();
}
