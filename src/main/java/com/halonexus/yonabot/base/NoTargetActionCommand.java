package com.halonexus.yonabot.base;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Random;

public abstract class NoTargetActionCommand implements Command {
    private final String assetPath;
    private final String actionString;
    private final Category category;
    private final PermissionLevel permissionLevel;

    public NoTargetActionCommand(String assetPath, String actionString) {
        this.assetPath = assetPath;
        this.actionString = actionString;
        permissionLevel = PermissionLevel.USER;
        category = Category.ACTION;
    }

    @Override
    public void execute(Context context, String content) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        File dir = new File(assetPath);
        File[] files = dir.listFiles();
        Random rand = new Random();
        File file = files[rand.nextInt(files.length)];
        embedBuilder
                .setDescription("**" + context.getMember().getEffectiveName() + "** " + actionString)
                .setColor(new Color(255, 0, 255))
                .setImage("attachment://" + file.getName());
        context.getChannel().sendFile(file).embed(embedBuilder.build()).queue();
    }

    @Override
    public abstract HelpInfo help();

    @Override
    public Category category() {
        return category;
    }

    @Override
    public PermissionLevel permissionLevel() {
        return permissionLevel;
    }

    @Override
    public List<String> aliases() {
        return null;
    }
}
