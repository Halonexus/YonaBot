package com.halonexus.yonabot;

import com.halonexus.yonabot.base.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    Map<String, Command> commands = new HashMap<>();

    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }
}
