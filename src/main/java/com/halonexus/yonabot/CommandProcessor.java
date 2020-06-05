package com.halonexus.yonabot;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandProcessor {
    public static final CommandRegistry COMMAND_REGISTRY = new CommandRegistry();

    public boolean process(GuildMessageReceivedEvent message){
        String msg = message.getMessage().getContentRaw();
        String prefix = DataManager.getConfig().prefix;
        if(!msg.toLowerCase().startsWith(prefix)){
            return false;
        }
        msg = msg.substring(prefix.length());
        String[] parts = Utility.splitArguments(msg, 2);
        String name = parts[0].toLowerCase();
        String content;
        if(parts.length == 1){
            content = "";
        }else{
            content = parts[1];
        }
        COMMAND_REGISTRY.run(message, name, content);
        return true;
    }

}