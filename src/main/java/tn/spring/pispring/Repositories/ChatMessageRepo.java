package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.ChatMessage;
import tn.spring.pispring.Entities.ChatRoom;

import java.util.List;

@Repository
public interface ChatMessageRepo extends JpaRepository<ChatMessage,Long> {
    List<ChatMessage> findByChatRoom(ChatRoom chatRoom);

}
