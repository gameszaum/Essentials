package com.gameszaum.essentials.files;

import com.gameszaum.core.spigot.api.configuration.ConfigAPI;
import com.gameszaum.core.spigot.plugin.GamesPlugin;
import lombok.Getter;
import org.apache.commons.io.FileUtils;

import java.io.File;

@Getter
public class FileLoader {

    private GamesPlugin plugin;
    private ConfigAPI messagesConfig, boxesConfig, permsConfig, groupsConfig;

    public FileLoader(GamesPlugin plugin) {
        this.plugin = plugin;
    }

    public void loadSubFiles() {
        messagesConfig = new ConfigAPI("messages", plugin);
        boxesConfig = new ConfigAPI("boxes", plugin);
        permsConfig = new ConfigAPI("permissions", plugin);
        groupsConfig = new ConfigAPI("groups", plugin);

        try {
            if (messagesConfig.getConfigurationSection("messages") == null) {
                FileUtils.copyInputStreamToFile(plugin.getResource("sub-files/messages.yml"), new File("messages.yml"));
            }
            if (messagesConfig.getConfigurationSection("boxes") == null) {
                FileUtils.copyInputStreamToFile(plugin.getResource("sub-files/boxes.yml"), new File("boxes.yml"));
            }
            if (messagesConfig.getConfigurationSection("permissions") == null) {
                FileUtils.copyInputStreamToFile(plugin.getResource("sub-files/permissions.yml"), new File("permissions.yml"));
            }
            if (messagesConfig.getConfigurationSection("groups") == null) {
                FileUtils.copyInputStreamToFile(plugin.getResource("sub-files/groups.yml"), new File("groups.yml"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
