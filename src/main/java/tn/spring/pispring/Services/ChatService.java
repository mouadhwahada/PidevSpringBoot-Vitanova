package tn.spring.pispring.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Dtos.Message;
import tn.spring.pispring.Entities.ChatMessage;
import tn.spring.pispring.Entities.ChatRoom;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Interfaces.ChatInterface;
import tn.spring.pispring.Repositories.ChatMessageRepo;
import tn.spring.pispring.Repositories.ChatRoomRepo;
import tn.spring.pispring.Repositories.UserRepo;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatService implements ChatInterface {
    private ChatMessageRepo chatMessageRepo;
    private ChatRoomRepo chatRoomRepo;
    private UserRepo userRepo;

    @Override
    public List<ChatRoom> getChatRoomsBySenderOrRecipient(Long idUser) {
        return chatRoomRepo.findBySenderIdOrRecipientId(idUser, idUser);
    }
    @Override
    public ChatRoom getChatRoomById(Long id) {
        return chatRoomRepo.findById(id).orElseThrow(() -> new RuntimeException("Chat room not found."));
    }
    @Override
    public List<ChatMessage> getChatMessagesByChatRoom(Long idChatRoom) {
        ChatRoom chatRoom = getChatRoomById(idChatRoom);
        return chatMessageRepo.findByChatRoom(chatRoom);
    }
    @Override
    public ChatRoom addChatRoom(Long idSender, Long idRecipient) {
        User sender = userRepo.findById(idSender).orElseThrow(() -> new RuntimeException("Sender not found"));
        User recipient = userRepo.findById(idRecipient).orElseThrow(() -> new RuntimeException("Recipient not found"));

        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setSender(sender);
        chatRoom.setRecipient(recipient);

        return chatRoomRepo.save(chatRoom);

    }
    @Override
    public void deleteChatRoom(Long id) {
        chatRoomRepo.deleteById(id);
    }
    @Override
    public ChatMessage addMessage(Message msg) {
        ChatRoom chatRoom = getChatRoomById(msg.getIdChatRoom());
        User sender = userRepo.findById(msg.getIdSender()).orElseThrow(() -> new RuntimeException("Sender not found."));
        User recipient = userRepo.findById(msg.getIdRecipient()).orElseThrow(() -> new RuntimeException("Recipient not found."));

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(msg.getContent());
        chatMessage.setTimestamp(LocalDateTime.now());
        chatMessage.setSender(sender);
        chatMessage.setRecipient(recipient);
        chatMessage.setChatRoom(chatRoom);

       return chatMessageRepo.save(chatMessage);
    }
    @Override
    public void deleteMessage(Long id) {
        chatMessageRepo.deleteById(id);
    }

}
