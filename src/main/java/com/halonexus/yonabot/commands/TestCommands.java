package com.halonexus.yonabot.commands;

import com.google.common.eventbus.Subscribe;
import com.halonexus.yonabot.base.*;
import com.halonexus.yonabot.CommandRegistry;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;
import java.io.File;
import java.util.Random;

@CommandModule
@SuppressWarnings("UnstableApiUsage")
public class TestCommands {
    @Subscribe
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

    @Subscribe
    public void kiss(CommandRegistry registry) {
        registry.register("kiss", new ActionCommand("assets\\kiss\\", "kissed") {
            @Override
            public HelpInfo help() {
                return new HelpInfo("kiss another user", "y?kiss {user}")
                        .addArgument("user", "user to kiss");
            }
        });
    }

    @Subscribe
    public void lick(CommandRegistry registry) {
        registry.register("lick", new ActionCommand("assets\\lick\\", "licked") {
            @Override
            public HelpInfo help() {
                return new HelpInfo("lick another user", "y?lick {user}")
                        .addArgument("user", "user to lick");
            }
        });
    }
}