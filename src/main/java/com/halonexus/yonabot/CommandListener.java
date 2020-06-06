package com.halonexus.yonabot;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import javax.annotation.Nonnull;

public class CommandListener implements EventListener {
    private final CommandProcessor processor;

    public CommandListener(CommandProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void onEvent(@Nonnull GenericEvent event) {
        if (event instanceof GuildMessageReceivedEvent) {
            GuildMessageReceivedEvent msg = (GuildMessageReceivedEvent) event;
            if (msg.getAuthor().isBot() || msg.isWebhookMessage() || msg.getAuthor().equals(msg.getJDA().getSelfUser()))
                return;
            processor.process(msg);
        }
    }
}
