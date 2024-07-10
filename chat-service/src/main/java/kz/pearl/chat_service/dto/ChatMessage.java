package kz.pearl.chat_service.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
    private String chatRoom;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

}
