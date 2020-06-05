package com.halonexus.yonabot.base;

import com.halonexus.yonabot.Config;
import com.halonexus.yonabot.DataManager;
import com.halonexus.yonabot.YonaBot;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class Context {
    @Getter
    private final YonaBot bot = YonaBot.getInstance();
    @Getter
    private final Config config = DataManager.getConfig();
    @Getter
    private final GuildMessageReceivedEvent event;
    @Getter
    private final String content;

    public Context(GuildMessageReceivedEvent event, String content){
        this.event = event;
        this.content = content;
    }

    public JDA getJDA() {
        return getEvent().getJDA();
    }

    public List<User> getMentionedUsers() {
        return getEvent().getMessage().getMentionedUsers();
    }

    public List<Member> getMentionedMembers() {
        return getEvent().getMessage().getMentionedMembers();
    }

    public Member getMember() {
        return event.getMember();
    }

    public User getUser() {
        return event.getAuthor();
    }

    public User getAuthor() {
        return getUser();
    }

    public Guild getGuild() {
        return event.getGuild();
    }

    public Message getMessage() {
        return event.getMessage();
    }

    public SelfUser getSelfUser() {
        return event.getJDA().getSelfUser();
    }

    public Member getSelfMember() {
        return getGuild().getSelfMember();
    }

    public TextChannel getChannel() {
        return event.getChannel();
    }

    public void send(Message message) {
        getChannel().sendMessage(message).queue();
    }

    public void send(String message) {
        getChannel().sendMessage(message).queue();
    }

    public void sendFormat(String message, Object... format) {
        getChannel().sendMessageFormat(message, format).queue();
    }

    public void send(MessageEmbed embed) {
        getChannel().sendMessage(embed).queue();
    }

    public void sendStripped(String message) {
        new MessageBuilder().setContent(message)
                .stripMentions(event.getGuild(), Message.MentionType.HERE, Message.MentionType.EVERYONE, Message.MentionType.USER)
                .sendTo(getChannel())
                .queue();
    }
}
