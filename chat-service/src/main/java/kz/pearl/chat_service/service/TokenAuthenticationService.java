package kz.pearl.chat_service.service;

import javax.servlet.http.HttpServletRequest;
import org.hibernate.service.spi.ServiceException;
import org.springframework.security.core.Authentication;


public interface TokenAuthenticationService {
    Authentication authenticate(HttpServletRequest request) throws Exception;
}
