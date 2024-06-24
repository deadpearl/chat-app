package kz.pearl.chat_service.dto;



import kz.pearl.chat_service.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Builder
public class TokenDTO implements Serializable {

    private static final long serialVersionUID = 6710061358371752955L;

    private String token;

    private User user;

    public TokenDTO() {
    }

}
