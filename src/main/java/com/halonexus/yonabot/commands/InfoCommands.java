package com.halonexus.yonabot.commands;

import com.google.common.eventbus.Subscribe;
import com.halonexus.yonabot.CommandRegistry;
import com.halonexus.yonabot.base.*;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import static com.halonexus.yonabot.Utility.capitalise;

@CommandModule
@SuppressWarnings("UnstableApiUsage")
public class InfoCommands {
    @Subscribe
    public void help(CommandRegistry registry) {
        registry.register("help", new SimpleCommand(Category.INFO) {
            @Override
            public void execute(Context context, String content) {
                EmbedBuilder builder = new EmbedBuilder()
                        .setColor(new Color(255, 0, 255));
                if (content.length() != 0) {
                    Command command = registry.getCommands().get(content.toLowerCase());
                    if (command == null) {
                        builder.setDescription("No such command");
                        context.send(builder.build());
                        return;
                    }
                    HelpInfo helpInfo = command.help();
                    builder.setAuthor(capitalise(content), null)
                            .setDescription(helpInfo.getDescription())
                            .addField("Example:", helpInfo.getUsage(), false);
                    Map<String, String> arguments = helpInfo.getArguments();
                    if (!arguments.isEmpty()) {
                        for (String s : arguments.keySet()) {
                            builder.addField(capitalise(s), arguments.get(s), false);
                        }
                    }
                    context.send(builder.build());
                    return;
                }
                String[] cmdsByCategory = new String[Category.values().length];
                for (int i = 0; i < Category.values().length; i++) {
                    cmdsByCategory[i] = "";
                }
                Map<String, Command> commands = registry.getCommands();
                for (String cmd : commands.keySet()) {
                    int index = commands.get(cmd).category().ordinal();
                    cmdsByCategory[index] = cmdsByCategory[index] + "\t" + cmd;
                }
                for (Category c : Category.values()) {
                    builder.addField(c.getName(), cmdsByCategory[c.getPriority()], false);
                }
                builder.setDescription("YonaBot commands");
                context.send(builder.build());
            }

            @Override
            public HelpInfo help() {
                return new HelpInfo("bruh momento", "bruh sound effect #2")
                        .addArgument("command", "shows the commands help info");
            }
        });
    }
}
