package kz.pearl.chat_service.service;

import kz.pearl.chat_service.dto.ChatMessage;

public interface ChatService {
    void saveMessage(ChatMessage chatMessage);
}
