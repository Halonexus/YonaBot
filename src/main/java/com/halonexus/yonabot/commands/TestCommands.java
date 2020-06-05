package com.halonexus.yonabot.commands;

import com.google.common.eventbus.Subscribe;
import com.halonexus.yonabot.base.*;
import com.halonexus.yonabot.CommandRegistry;

@CommandModule
@SuppressWarnings("UnstableApiUsage")
public class TestCommands {
    @Subscribe
    public void ping(CommandRegistry registry) {
        registry.register("ping", new SimpleCommand(Category.MISC) {
            @Override
            public void execute(Context context, String content) {
                context.send("pong!");
            }

            @Override
            public HelpInfo help() {
                return new HelpInfo("does the ping pong thing", "y?ping");
            }
        });
    }
}