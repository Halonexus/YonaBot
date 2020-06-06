package com.halonexus.yonabot;

import com.halonexus.yonabot.base.Command;
import com.halonexus.yonabot.base.Context;
import lombok.Getter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    @Getter
    private final Map<String, Command> commands = new HashMap<>();

    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public boolean run(GuildMessageReceivedEvent message, String commandName, String content) {
        Command command = commands.get(commandName);
        if (command == null) {
            return false;
        }
        command.execute(new Context(message, content), content);
        return true;
    }
}
