package com.halonexus.yonabot.commands;

import com.google.common.eventbus.Subscribe;
import com.halonexus.yonabot.CommandRegistry;
import com.halonexus.yonabot.base.NoTargetActionCommand;
import com.halonexus.yonabot.base.TargetActionCommand;
import com.halonexus.yonabot.base.CommandModule;
import com.halonexus.yonabot.base.HelpInfo;

@CommandModule
@SuppressWarnings("UnstableApiUsage")
public class ActionCommands {
    @Subscribe
    public void registerActionCommands(CommandRegistry registry) {
        //kiss
        String[] actions = new String[]{"kissed", "smooched", "is making out with", "french kissed", "is tongue wrestling"};
        String[] finisher = new String[]{"", "uwu", "owo", "\u2267\u25e1\u2266", "(\u2312o\u2312)", "(\u25e0\u03c9\u25e0 )", "(`\uff65\u03c9\uff65\u00b4)"};
        String[] selfStart = new String[]
                {"Awww...you're so lonely...\nHere...",
                        "You can't kiss yourself dummy\nSoo...",
                        "_You want to be kissed by a cute girl huh_\n"};
        String[] selfFinish = new String[]{" _s-so embarrassing..._", "nya~", " _nya~_", " _nyah~_", "\u2267\u25e1\u2266", "uwu"};
        String[] botStart = new String[]
                {"Wawaaa!? You want to k-kiss me?!\n",
                        "Eeeh...? _Uuum y-you can kiss m-me if you really want to..._\n",
                        "K-kiss! _...please kiss me more_\n"};
        String[] botFinish = new String[]{" _s-so embarrassing..._", " _hueh_", "nya~", " _nya~_", "_kissu..._", " _nyah~_", ">~<", "( \u25e0\u203f\u25e0)", "(\u25e0\u03c9\u25e0)", " _runs away_"};

        registry.register("kiss", new TargetActionCommand("assets\\kiss\\", actions, finisher, selfStart, selfFinish, botStart, botFinish) {
            @Override
            public HelpInfo help() {
                return new HelpInfo("kiss another user", "y?kiss {user}")
                        .addArgument("user", "user to kiss");
            }
        });

        //lick
        actions = new String[]{"licked", "tasted"};
        selfStart = new String[]
                {"Awww...you're so lonely...\nHere...",
                        "You want to lick yourself..? I c-can lick you instead...\nI-its not like I want t-to or anything...",
                        "_I'll lick you instead~_\n"};
        selfFinish = new String[]{" _s-so embarrassing..._", " delicious", " _nya~_", " _tasty~_", " _nyah~_", "\u2267\u25e1\u2266", "uwu"};
        botStart = new String[]
                {"Hueh!? You're g-going to lick me?!\n",
                        "L-lick me? _please be gentle..._\n",
                        "Uwaa! T-thats so lewd!\n"};
        botFinish = new String[]{" _s-so embarrassing..._", " _hueh_", "nya~", " _nya~_", "_licku..._", " _nyah~_", ">~<", "( \u25e0\u203f\u25e0)", "(\u25e0\u03c9\u25e0)", " _runs away_"};
        registry.register("lick", new TargetActionCommand("assets\\lick\\", actions, finisher, selfStart, selfFinish, botStart, botFinish) {
            @Override
            public HelpInfo help() {
                return new HelpInfo("lick another user", "y?lick {user}")
                        .addArgument("user", "user to lick");
            }
        });

        //nom
        actions = new String[]{"nommed"};
        selfStart = new String[]
                {"Awww...you're so lonely...\nHere...",
                        "How...why would you nom yourself? Here's how its done\n",
                        "Dont nom alone! I-i'll nom you if you're lonely...\n"};
        selfFinish = new String[]{" yummy!", " delicious", " _nya~_", " _tasty~_", " _nyah~_", "\u2267\u25e1\u2266", "uwu"};
        botStart = new String[]
                {"N-nom me? _well if you want to..._\n",
                        "Eeeeh!? D-dont eat m-me..!\n",
                        "owo nom\n"};
        botFinish = new String[]{" _s-so embarrassing..._", " n-nom...", "nya~", " _hueh_", " _nya~_", " _nyah~_", ">~<", "( \u25e0\u203f\u25e0)", "(\u25e0\u03c9\u25e0)"};
        registry.register("nom", new TargetActionCommand("assets\\nom\\", actions, finisher, selfStart, selfFinish, botStart, botFinish) {
            @Override
            public HelpInfo help() {
                return new HelpInfo("nom another user", "y?nom {user}")
                        .addArgument("user", "user to nom");
            }
        });

        //pat
        actions = new String[]{"patted"};
        selfStart = new String[]
                {"Awww...you're so lonely...\nHere...",
                        "Patting yourself is just depressing...\nHere",
                        "I will pat you too!\n"};
        selfFinish = new String[]{" nya~!", " _nya~_", " _nyah~_", "owo", "\u2267\u25e1\u2266", "uwu"};
        botStart = new String[]
                {" Nya~ please pat me more~\n",
                        "pat?! owo _t-thanks..._\n",
                        "You'll pat me? eeh? _Im noot a chiild_\n>~<"};
        botFinish = new String[]{" nya!", " pat!", " _hueh_", "nya~", " _nya~_", " _nyah~_", ">~<", "( \u25e0\u203f\u25e0)", "(\u25e0\u03c9\u25e0)"};
        registry.register("pat", new TargetActionCommand("assets\\pat\\", actions, finisher, selfStart, selfFinish, botStart, botFinish) {
            @Override
            public HelpInfo help() {
                return new HelpInfo("pat another user", "y?pat {user}")
                        .addArgument("user", "user to pat");
            }
        });

        //suck
        actions = new String[]{"sucked"};
        selfStart = new String[]
                {"Awww...you're so lonely...\nHere...",
                        "You wanna...suck...yourself? uum... its embarrassing but...\n",
                        "L-lewd! _b-but I can do it for you if you want..._\n"};
        selfFinish = new String[]{" _s-so embarrassing..._", " delicious", "nya~", " _nya~_", " _tasty~_", " _nyah~_", "\u2267\u25e1\u2266", "uwu"};
        botStart = new String[]
                {"Hueh!? t-thats so lewd!\n>~<",
                        "S-suck me? owo\n",
                        "W-what are you gonna s-suck?! _nyoo..._\n"};
        botFinish = new String[]{" _s-so embarrassing..._", " _hueh_", " _nya~_", " _nyah~_", ">~<", "( \u25e0\u203f\u25e0)", "(\u25e0\u03c9\u25e0)", " _runs away_"};
        registry.register("suck", new TargetActionCommand("assets\\suck\\", actions, finisher, selfStart, selfFinish, botStart, botFinish) {
            @Override
            public HelpInfo help() {
                return new HelpInfo("suck another user", "y?suck {user}")
                        .addArgument("user", "user to suck owo");
            }
        });

        registry.register("dance", new NoTargetActionCommand("assets\\dance\\", "is dancing") {
            @Override
            public HelpInfo help() {
                return new HelpInfo("get dance gif", "y?dance");
            }
        });

        registry.register("wiggle", new NoTargetActionCommand("assets\\wiggle\\", "wiggles their butt uwu") {
            @Override
            public HelpInfo help() {
                return new HelpInfo("get a butt gif", "y?wiggle");
            }
        });
    }
}
