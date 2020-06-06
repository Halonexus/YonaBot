package com.halonexus.yonabot.commands;

import com.google.common.eventbus.Subscribe;
import com.halonexus.yonabot.CommandRegistry;
import com.halonexus.yonabot.base.NoTargetActionCommand;
import com.halonexus.yonabot.base.TargetActionCommand;
import com.halonexus.yonabot.base.CommandModule;
import com.halonexus.yonabot.base.HelpInfo;

@CommandModule
@SuppressWarnings("UnstableApiUsage")
public class ActionCommands {
    @Subscribe
    public void registerActionCommands(CommandRegistry registry) {
        registry.register("kiss", new TargetActionCommand("assets\\kiss\\", "kissed") {
            @Override
            public HelpInfo help() {
                return new HelpInfo("kiss another user", "y?kiss {user}")
                        .addArgument("user", "user to kiss");
            }
        });

        registry.register("lick", new TargetActionCommand("assets\\lick\\", "licked") {
            @Override
            public HelpInfo help() {
                return new HelpInfo("lick another user", "y?lick {user}")
                        .addArgument("user", "user to lick");
            }
        });

        registry.register("nom", new TargetActionCommand("assets\\nom\\", "nommed") {
            @Override
            public HelpInfo help() {
                return new HelpInfo("nom another user", "y?nom {user}")
                        .addArgument("user", "user to nom");
            }
        });

        registry.register("pat", new TargetActionCommand("assets\\pat\\", "patted") {
            @Override
            public HelpInfo help() {
                return new HelpInfo("pat another user", "y?pat {user}")
                        .addArgument("user", "user to pat");
            }
        });

        registry.register("suck", new TargetActionCommand("assets\\suck\\", "sucked") {
            @Override
            public HelpInfo help() {
                return new HelpInfo("suck another user", "y?suck {user}")
                        .addArgument("user", "user to suck owo");
            }
        });

        registry.register("dance", new NoTargetActionCommand("assets\\dance\\", "is dancing") {
            @Override
            public HelpInfo help() {
                return new HelpInfo("get dance gif", "y?dance");
            }
        });

        registry.register("wiggle", new NoTargetActionCommand("assets\\wiggle\\", "wiggles their butt uwu") {
            @Override
            public HelpInfo help() {
                return new HelpInfo("get a butt gif", "y?wiggle");
            }
        });
    }
}
