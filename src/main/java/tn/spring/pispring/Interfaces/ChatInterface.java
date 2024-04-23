package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Dtos.Message;
import tn.spring.pispring.Entities.ChatMessage;
import tn.spring.pispring.Entities.ChatRoom;

import java.util.List;

public interface ChatInterface {
    List<ChatRoom> getChatRoomsBySenderOrRecipient(Long idUser);

    ChatRoom getChatRoomById(Long id);

    List<ChatMessage> getChatMessagesByChatRoom(Long idChatRoom);

    ChatRoom addChatRoom(Long idSender, Long idRecipient);

    void deleteChatRoom(Long id);

    ChatMessage addMessage(Message msg);

    void deleteMessage(Long id);
}
