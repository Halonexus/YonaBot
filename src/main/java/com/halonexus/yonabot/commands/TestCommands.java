package com.halonexus.yonabot.commands;

import com.google.common.eventbus.Subscribe;
import com.halonexus.yonabot.base.*;
import com.halonexus.yonabot.CommandRegistry;

@CommandModule
public class TestCommands {

    @Subscribe
    @SuppressWarnings("UnstableApiUsage")
    public void ping(CommandRegistry registry) {
        registry.register("ping", new SimpleCommand(Category.MISC) {
            @Override
            public void execute(Context context, String content) {
                context.getChannel().sendMessage("pong!").queue();
            }

            @Override
            public HelpInfo help() {
                return new HelpInfo("does the ping pong thing" , "y?ping");
            }
        });
    }
}