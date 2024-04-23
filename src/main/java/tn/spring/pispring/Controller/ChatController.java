package tn.spring.pispring.Controller;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Dtos.Message;
import tn.spring.pispring.Interfaces.ChatInterface;
import tn.spring.pispring.Entities.ChatMessage;
import tn.spring.pispring.Entities.ChatRoom;

import java.util.List;

@RestController
@RequestMapping("/chat")
@CrossOrigin("*")
@AllArgsConstructor
public class ChatController {

    private ChatInterface chatService;

    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/getChatRooms/{userId}")
    public List<ChatRoom> getChatRoomsBySenderOrRecipient(@PathVariable Long userId) {
        return chatService.getChatRoomsBySenderOrRecipient(userId);
    }

    @GetMapping("/getChatRoomById/{id}")
    public ChatRoom getChatRoomById(@PathVariable Long id) {
        return chatService.getChatRoomById(id);
    }

    @GetMapping("/getChatMessagesByChatRoom/{chatRoomId}")
    public List<ChatMessage> getChatMessagesByChatRoom(@PathVariable Long chatRoomId) {
        return chatService.getChatMessagesByChatRoom(chatRoomId);
    }

    @PostMapping("/addChatRoom/{idSender}/{idRecipient}")
    public ChatRoom addChatRoom(@PathVariable Long idSender, @PathVariable Long idRecipient) {
        return chatService.addChatRoom(idSender, idRecipient);
    }

    @DeleteMapping("/deleteChatRoom/{id}")
    public void deleteChatRoom(@PathVariable Long id) {
        chatService.deleteChatRoom(id);
    }

    @DeleteMapping("/deleteMessage/{messageId}")
    public void deleteMessage(@PathVariable Long messageId) {
        chatService.deleteMessage(messageId);
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(@Payload Message msg) {

        ChatMessage chatMessage = chatService.addMessage(msg);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipient().getUserName(),
                "/queue/messages",
                chatMessage
        );

        return chatMessage;
    }

}
