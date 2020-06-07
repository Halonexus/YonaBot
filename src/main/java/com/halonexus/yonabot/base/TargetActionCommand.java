package com.halonexus.yonabot.base;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Random;

public abstract class TargetActionCommand implements Command {
    private final String assetPath;
    private final String[] actionsPastTense;
    private final String[] selfActions;
    private final String[] botActions;
    private final Category category;
    private final PermissionLevel permissionLevel;

    public TargetActionCommand(String assetPath, String[] actionsPastTense) {
        this.assetPath = assetPath;
        permissionLevel = PermissionLevel.USER;
        category = Category.ACTION;
        this.actionsPastTense = actionsPastTense;
        this.selfActions = actionsPastTense;
        this.botActions = actionsPastTense;
    }

    public TargetActionCommand(String assetPath, String[] actionsPastTense, String[] selfActions, String[] botActions) {
        this.assetPath = assetPath;
        permissionLevel = PermissionLevel.USER;
        category = Category.ACTION;
        this.actionsPastTense = actionsPastTense;
        this.selfActions = selfActions;
        this.botActions = botActions;
    }

    @Override
    public void execute(Context context, String content) {
        File file = getRandomFile(assetPath);
        String targets = "";
        boolean selfTarget = false;
        boolean botTarget = false;
        for (Member u : context.getMentionedMembers()) {
            if(u.equals(context.getSelfMember())){
                botTarget = true;
            }
            if (u.equals(context.getMember())) {
                selfTarget = true;
                continue;
            }
            if (!targets.isBlank()) {
                targets = targets.concat(", ");
                botTarget = false;
            }
            targets = targets.concat(u.getEffectiveName());
        }
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setColor(new Color(255, 0, 255))
                .setImage("attachment://" + file.getName());
        if (targets.isBlank() && selfTarget) {
            embedBuilder
                    .setDescription("You're so lonely...\nHere...**"
                            + context.getSelfMember().getEffectiveName() + "** "
                            + getRandomString(selfActions) + " **"
                            + context.getMember().getEffectiveName() + "**");
            context.getChannel().sendFile(file).embed(embedBuilder.build()).queue();
            return;
        }
        if(botTarget){
            embedBuilder
                    .setDescription("**" + context.getMember().getEffectiveName()
                            + "** " + getRandomString(botActions) + " **" + targets + "**");
            context.getChannel().sendFile(file).embed(embedBuilder.build()).queue();
            return;
        }
        if (targets.isBlank()) {
            return;
        }
        embedBuilder
                .setDescription("**" + context.getMember().getEffectiveName()
                        + "** " + getRandomString(actionsPastTense) + " **" + targets + "**");
        context.getChannel().sendFile(file).embed(embedBuilder.build()).queue();
    }

    private static File getRandomFile(String path){
        File dir = new File(path);
        File[] files = dir.listFiles();
        Random rand = new Random();
        return files[rand.nextInt(files.length)];
    }

    private static String getRandomString(String[] strings){
        if(strings == null){
            return "";
        }
        Random rand = new Random();
        return strings[rand.nextInt(strings.length)];
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
