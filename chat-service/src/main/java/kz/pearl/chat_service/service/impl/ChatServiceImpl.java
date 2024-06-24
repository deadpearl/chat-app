package kz.pearl.chat_service.service.impl;

import kz.pearl.chat_service.dto.ChatMessage;
import kz.pearl.chat_service.entity.MessageEntity;
import kz.pearl.chat_service.repository.MessageRepository;
import kz.pearl.chat_service.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private MessageRepository messageRepository;
    @Override
    public void saveMessage(ChatMessage chatMessage) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setSender(chatMessage.getSender());
        messageEntity.setContent(chatMessage.getContent());
        messageEntity.setChatRoom(chatMessage.getChatRoom());
        messageEntity.setSentTime(LocalDateTime.now());
        messageEntity.setType(chatMessage.getType().name());
        messageRepository.save(messageEntity);
    }
}
