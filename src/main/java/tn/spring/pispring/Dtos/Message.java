package tn.spring.pispring.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Long idChatRoom;
    private Long idSender;
    private Long idRecipient;
    private String content;
}
