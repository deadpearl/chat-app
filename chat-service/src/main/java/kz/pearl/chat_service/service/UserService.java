package kz.pearl.chat_service.service;

import kz.pearl.chat_service.dto.LoginDTO;
import kz.pearl.chat_service.dto.TokenDTO;
import kz.pearl.chat_service.dto.UserDTO;
import kz.pearl.chat_service.entity.User;

import java.util.List;

public interface UserService {
    void registerUser(UserDTO userDTO);
    TokenDTO authenticateUser(LoginDTO loginDTO);
    List<UserDTO> getActiveUsers();
    User findUserByUsername(String username);

}
