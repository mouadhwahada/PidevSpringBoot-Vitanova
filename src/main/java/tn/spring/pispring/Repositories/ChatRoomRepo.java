package tn.spring.pispring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.ChatRoom;

import java.util.List;

@Repository
public interface ChatRoomRepo extends JpaRepository<ChatRoom,Long> {
    List<ChatRoom> findBySenderIdOrRecipientId(Long idUser, Long idUser1);
}
