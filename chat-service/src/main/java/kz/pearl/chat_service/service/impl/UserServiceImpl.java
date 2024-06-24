package kz.pearl.chat_service.service.impl;

import com.sun.jdi.InternalException;
import kz.pearl.chat_service.dto.LoginDTO;
import kz.pearl.chat_service.dto.TokenDTO;
import kz.pearl.chat_service.dto.UserDTO;
import kz.pearl.chat_service.entity.User;
import kz.pearl.chat_service.repository.UserRepository;
import kz.pearl.chat_service.service.TokenService;
import kz.pearl.chat_service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Override
    public void registerUser(UserDTO userDTO) {
        User user = User.builder()
                .id(null)
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .registerDate(LocalDateTime.now()).build();
        userRepository.save(user);
    }
    @Override
    public TokenDTO authenticateUser(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow();
        if (!(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()))) {
            throw new InternalException("Неправильный пароль");
        }
        return tokenService.getToken(loginDTO.getEmail(), loginDTO.getPassword());
    }
    @Override
    public List<UserDTO> getActiveUsers() {
        // Fetch active users
        return null;
    }
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow();
    }
}
