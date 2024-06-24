package kz.pearl.chat_service.service;


import com.sun.jdi.InternalException;
import kz.pearl.chat_service.dto.TokenDTO;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    TokenDTO getToken(String username, String password);
    String deactivateToken(HttpServletRequest request) throws InternalException;
}
