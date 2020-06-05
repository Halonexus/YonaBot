package com.halonexus.yonabot.commands;

import com.google.common.eventbus.Subscribe;
import com.halonexus.yonabot.CommandRegistry;
import com.halonexus.yonabot.base.ActionCommand;
import com.halonexus.yonabot.base.CommandModule;
import com.halonexus.yonabot.base.HelpInfo;

@CommandModule
@SuppressWarnings("UnstableApiUsage")
public class ActionCommands {
    @Subscribe
    public void registerActionCommands(CommandRegistry registry){
        registry.register("kiss", new ActionCommand("assets\\kiss\\", "kissed") {
            @Override
            public HelpInfo help() {
                return new HelpInfo("kiss another user", "y?kiss {user}")
                        .addArgument("user", "user to kiss");
            }
        });

        registry.register("lick", new ActionCommand("assets\\lick\\", "licked") {
            @Override
            public HelpInfo help() {
                return new HelpInfo("lick another user", "y?lick {user}")
                        .addArgument("user", "user to lick");
            }
        });
    }
}
