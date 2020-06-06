package com.halonexus.yonabot.base;

import com.halonexus.yonabot.YonaBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Random;

public abstract class TargetActionCommand implements Command {
    private final String assetPath;
    private final String actionPastTense;
    private final Category category;
    private final PermissionLevel permissionLevel;

    public TargetActionCommand(String assetPath, String actionPastTense) {
        this.assetPath = assetPath;
        this.actionPastTense = actionPastTense;
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
        String targets = "";
        boolean selfTarget = false;
        for (Member u : context.getMentionedMembers()) {
            if (u.equals(context.getMember())) {
                selfTarget = true;
                continue;
            }
            if (!targets.isBlank()) {
                targets = targets.concat(", ");
            }
            targets = targets.concat(u.getEffectiveName());
        }
        if (targets.isBlank() && selfTarget) {
            embedBuilder
                    .setDescription("You're so lonely...\nHere...**" + context.getSelfMember().getEffectiveName() + "** " + actionPastTense + " **" + context.getMember().getEffectiveName() + "**")
                    .setColor(new Color(255, 0, 255))
                    .setImage("attachment://" + file.getName());
            context.getChannel().sendFile(file).embed(embedBuilder.build()).queue();
            return;
        }
        if (targets.isBlank()) {
            return;
        }
        embedBuilder
                .setDescription("**" + context.getMember().getEffectiveName() + "** " + actionPastTense + " **" + targets + "**")
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
