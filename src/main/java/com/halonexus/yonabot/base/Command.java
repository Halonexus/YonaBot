package com.halonexus.yonabot.base;

import java.util.List;

public interface Command {

    void execute(Context context, String content);

    HelpInfo help();

    Category category();

    PermissionLevel permissionLevel();

    List<String> aliases();
}
