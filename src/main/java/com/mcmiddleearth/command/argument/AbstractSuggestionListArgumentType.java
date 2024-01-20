package com.mcmiddleearth.command.argument;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractSuggestionListArgumentType implements ArgumentType<String>, HelpfulArgumentType {

    private String tooltip = "Choose an item from list.";

    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        return reader.readUnquotedString();
    }

    @Override
    public Collection<String> getExamples() {
        return Collections.singletonList("anything");
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context, final SuggestionsBuilder builder) {
        for (String option : getSuggestions()) {
            if (option.toLowerCase().startsWith(builder.getRemaining().toLowerCase())) {
                if(tooltip == null) {
                    builder.suggest(option);
                } else {
                    builder.suggest(option, new LiteralMessage(tooltip));
                }
            }
        }
        return builder.buildFuture();
    }

    protected abstract Collection<String> getSuggestions();

    @Override
    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }
}
