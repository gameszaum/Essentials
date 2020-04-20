package com.gameszaum.essentials.messages.service.impl;

import com.gameszaum.core.spigot.api.configuration.ConfigAPI;
import com.gameszaum.essentials.messages.Message;
import com.gameszaum.essentials.messages.service.MessageService;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class MessageServiceImpl implements MessageService {

    private Set<Message> messages;
    private ConfigAPI messagesConfig;

    public MessageServiceImpl() {
        messages = new HashSet<>();
    }

    @Override
    public void load(ConfigAPI configAPI) {
        ConfigurationSection section = configAPI.getConfigurationSection("messages");

        if (section == null) return;

        section.getKeys(false).forEach(s -> create(new Message(s, section.getString(s))));
    }

    @Override
    public void create(Message message) {
        messages.add(message);
    }

    @Override
    public String getMessage(String id) {
        return search(id).findFirst().get().getText().replaceAll("&", "§");
    }

    private Stream<Message> search(String s) {
        return messages.stream().filter(message -> message.getId().equals(s));
    }
}
