package com.gameszaum.essentials;

import com.gameszaum.core.other.classes.ListenerLoader;
import com.gameszaum.core.spigot.Services;
import com.gameszaum.core.spigot.plugin.GamesPlugin;
import com.gameszaum.essentials.commands.PlayerCommands;
import com.gameszaum.essentials.commands.StaffCommands;
import com.gameszaum.essentials.files.FileLoader;
import com.gameszaum.essentials.listeners.player.PlayerListeners;
import com.gameszaum.essentials.messages.service.MessageService;
import com.gameszaum.essentials.messages.service.impl.MessageServiceImpl;

public final class Essentials extends GamesPlugin {

    private static Essentials instance;
    private FileLoader fileLoader;

    @Override
    public void load() {
        instance = this;

        /* Services */

        Services.create(this);
        Services.add(MessageService.class, new MessageServiceImpl());
    }

    @Override
    public void enable() {
        fileLoader = new FileLoader(this);
        fileLoader.loadSubFiles();

        Services.get(MessageService.class).load(fileLoader.getMessagesConfig());

        new ListenerLoader(this).load("com.gameszaum.essentials.listeners");

        PlayerCommands.setup();
        StaffCommands.setup();

        System.out.println("[games-essentials] Plugin enabled.");
    }

    @Override
    public void disable() {
        System.out.println("[games-essentials] Plugin disabled.");
    }

    public boolean isUsingMySQL() {
        return getConfig().getBoolean("use-mysql");
    }

    public boolean isDebugging() {
        return getConfig().getBoolean("debug");
    }

    public static Essentials getInstance() {
        return instance;
    }
}
