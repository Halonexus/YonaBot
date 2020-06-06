package com.halonexus.yonabot.base;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

public enum PermissionLevel {
    USER() {
        @Override
        public boolean check(Member member) {
            return true;
        }
    },
    MODERATOR() {
        @Override
        public boolean check(Member member) {
            return member.isOwner() || member.hasPermission(Permission.ADMINISTRATOR) ||
                    member.hasPermission(Permission.MANAGE_SERVER) ||
                    member.hasPermission(Permission.MANAGE_CHANNEL) && member.hasPermission(Permission.MANAGE_ROLES);
        }
    },
    ADMIN() {
        @Override
        public boolean check(Member member) {
            return member.isOwner() || member.hasPermission(Permission.ADMINISTRATOR);
        }
    };

    public abstract boolean check(Member member);
}