package com.halonexus.yonabot;

import com.google.common.eventbus.EventBus;
import com.halonexus.yonabot.base.CommandModule;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.lang.annotation.Annotation;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("UnstableApiUsage")
public class YonaBot {
    private static final String COMMAND_PACKAGE = "com.halonexus.yonabot.commands";
    @Getter
    private static YonaBot instance;
    private final Config config;
    @Getter
    private final JDA jda;

    private YonaBot() {
        config = DataManager.readConfig("config.json");
        instance = this;
        registerCommands();
        jda = startJDAInstance();
        jda.addEventListener(new CommandListener(new CommandProcessor()));
    }

    private JDA startJDAInstance() {
        JDA jda = null;
        try {
            jda = JDABuilder.create(config.token, GatewayIntent.GUILD_MESSAGES,
                    GatewayIntent.GUILD_MESSAGE_REACTIONS)
                    .setAutoReconnect(true)
                    .setBulkDeleteSplittingEnabled(false)
                    .disableCache(EnumSet.of(CacheFlag.ACTIVITY, CacheFlag.EMOTE))
                    .setActivity(Activity.watching("you sleep"))
                    .build()
                    .awaitReady();
        } catch (LoginException e) {
            System.exit(2);
        } catch (InterruptedException e) {
            System.exit(3);
        }
        return jda;
    }

    public static void main(String[] args) {
        try {
            new YonaBot();
        } catch (Exception e) {

        }
    }

    private void registerCommands() {
        Set<Class<?>> commands = lookForAnnotatedOn(CommandModule.class);
        EventBus eventBus = new EventBus();
        for (Class<?> aClass : commands) {
            try {
                eventBus.register(aClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                //error = "Couldnt find public constructor for command class: " + aClass.getName();
            }
        }
        eventBus.post(CommandProcessor.COMMAND_REGISTRY);
    }

    private Set<Class<?>> lookForAnnotatedOn(Class<? extends Annotation> annotation) {
        return new ClassGraph()
                .whitelistPackages(COMMAND_PACKAGE)
                .enableAnnotationInfo()
                .scan()
                .getAllClasses().stream().filter(classInfo -> classInfo.hasAnnotation(annotation.getName())).map(ClassInfo::loadClass)
                .collect(Collectors.toSet());
    }
}
