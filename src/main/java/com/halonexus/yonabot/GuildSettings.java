package com.halonexus.yonabot;

public class GuildSettings {
    public String GuildName;
    public String guildPrefix;
    public CommandSettings[] commandSettings;

    //should change types from String
    private static class CommandSettings{
        String commandName;
        String[] disabledChannels;
        String[] disabledRoles;
    }
}