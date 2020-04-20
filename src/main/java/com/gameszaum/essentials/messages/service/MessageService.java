package com.gameszaum.essentials.messages.service;

import com.gameszaum.core.spigot.api.configuration.ConfigAPI;
import com.gameszaum.essentials.messages.Message;

public interface MessageService {

    void create(Message message);

    String getMessage(String id);

    void load(ConfigAPI configAPI);

}
