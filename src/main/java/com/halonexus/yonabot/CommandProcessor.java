package com.halonexus.yonabot;

import com.halonexus.yonabot.base.Context;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandProcessor {
    public static final CommandRegistry COMMAND_REGISTRY = new CommandRegistry();

    public boolean process(GuildMessageReceivedEvent message){
        String msg = message.getMessage().getContentRaw();
        if(!msg.startsWith(DataManager.getConfig().prefix)){
            return false;
        }
        if(msg.startsWith("y?ping")){
            COMMAND_REGISTRY.commands.get("ping").execute(new Context(message, msg),msg);
        }
        return true;
    }

}