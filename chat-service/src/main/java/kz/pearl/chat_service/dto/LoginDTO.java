package kz.pearl.chat_service.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = -4159366809929151486L;

    private String email;
    private String password;

}
