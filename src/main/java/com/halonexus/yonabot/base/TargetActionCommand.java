package com.halonexus.yonabot.base;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

public abstract class TargetActionCommand implements Command {
    private final String assetPath;
    private final String[] actionsPastTense;
    private final String[] selfStart;
    private final String[] selfFinish;
    private final String[] botStart;
    private final String[] botFinish;
    private final String[] finisher;
    private final Category category;
    private final PermissionLevel permissionLevel;
    private static final String[] embarrassEmotes = new String[]{"<:svLove:709701962517577778>", "<:tmpblush:668054723932192789>", "<:Shoeby_Blush2:702099192482365460>", "<:meguYes:700388489174188102>", "<:blush_what:691462538440867857>", "<:m4Blush:668036075499028490>"};

    public TargetActionCommand(String assetPath, String[] actionsPastTense) {
        this.assetPath = assetPath;
        permissionLevel = PermissionLevel.USER;
        category = Category.ACTION;
        this.actionsPastTense = actionsPastTense;
        selfFinish = null;
        botFinish = null;
        selfStart = null;
        botStart = null;
        finisher = null;
    }

    public TargetActionCommand(String assetPath,
                               String[] actionsPastTense, String[] finisher,
                               String[] selfStart, String[] selfFinish,
                               String[] botStart, String[] botFinish) {
        this.assetPath = assetPath;
        permissionLevel = PermissionLevel.USER;
        category = Category.ACTION;
        this.actionsPastTense = actionsPastTense;
        this.selfFinish = selfFinish;
        this.botFinish = botFinish;
        this.botStart = botStart;
        this.selfStart = selfStart;
        this.finisher = finisher;
    }

    @Override
    public void execute(Context context, String content) {
        File file = getRandomFile(assetPath);
        String targets = "";
        boolean selfTarget = false;
        boolean botTarget = false;
        for (Member u : context.getMentionedMembers()) {
            if (u.equals(context.getSelfMember())) {
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
        //self branch
        if (targets.isBlank() && selfTarget) {
            embedBuilder
                    .setDescription(encodeInUTF8(getRandomString(selfStart)
                            + " **" + context.getSelfMember().getEffectiveName() + "** "
                            + getRandomString(actionsPastTense)
                            + " **" + context.getMember().getEffectiveName() + "** "
                            + getRandomString(selfFinish)));
            context.getChannel().sendFile(file).embed(embedBuilder.build()).queue();
            if (passedThreshold(0.5)) {
                context.send(getRandomString(embarrassEmotes));
            }
            return;
        }
        //bot branch
        if (botTarget) {
            embedBuilder
                    .setDescription(encodeInUTF8(getRandomString(botStart)
                            + " **" + context.getMember().getEffectiveName() + "** "
                            + getRandomString(actionsPastTense)
                            + " **" + targets + "** "
                            + getRandomString(botFinish)));
            context.getChannel().sendFile(file).embed(embedBuilder.build()).queue();
            if (passedThreshold(0.4)) {
                context.send(getRandomString(embarrassEmotes));
            }
            return;
        }
        //no target
        if (targets.isBlank()) {
            return;
        }
        //normal
        embedBuilder
                .setDescription(encodeInUTF8(" **" + context.getMember().getEffectiveName() + "** "
                        + getRandomString(actionsPastTense)
                        + " **" + targets + "** "
                        + getRandomString(finisher)));
        context.getChannel().sendFile(file).embed(embedBuilder.build()).queue();
    }

    private static File getRandomFile(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        Random rand = new Random();
        return files[rand.nextInt(files.length)];
    }

    private static String encodeInUTF8(String string) {
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private static String getRandomString(String[] strings) {
        if (strings == null) {
            return "";
        }
        Random rand = new Random();
        return strings[rand.nextInt(strings.length)];
    }

    private static boolean passedThreshold(double threshold) {
        Random rand = new Random();
        return rand.nextDouble() > threshold;
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
