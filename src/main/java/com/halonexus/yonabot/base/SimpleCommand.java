package com.halonexus.yonabot.base;

import java.util.ArrayList;
import java.util.List;

public abstract class SimpleCommand implements Command {
    private final Category category;
    private final PermissionLevel permissionLevel;
    private final List<String> aliases = new ArrayList<>();

    public SimpleCommand(Category category) {
        this.category = category;
        this.permissionLevel = PermissionLevel.USER;
    }

    public SimpleCommand(Category category, PermissionLevel permissionLevel) {
        this.category = category;
        this.permissionLevel = permissionLevel;
    }

    @Override
    public abstract void execute(Context context, String content);

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
        return aliases;
    }

    public void addAlias(String alias) {
        aliases.add(alias);
    }
}
