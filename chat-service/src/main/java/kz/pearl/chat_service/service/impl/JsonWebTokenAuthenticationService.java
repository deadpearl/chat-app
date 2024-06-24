package kz.pearl.chat_service.service.impl;

import io.jsonwebtoken.*;
import kz.pearl.chat_service.config.SecurityConstants;
import kz.pearl.chat_service.entity.Session;
import kz.pearl.chat_service.entity.UserAuthentication;
import kz.pearl.chat_service.repository.SessionRepository;
import kz.pearl.chat_service.service.TokenAuthenticationService;
import kz.pearl.chat_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static kz.pearl.chat_service.config.SecurityConstants.TOKEN_EXPIRE_TIME;


@Slf4j
@Service
@RequiredArgsConstructor
public class JsonWebTokenAuthenticationService implements TokenAuthenticationService {

    private final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    @Value("${security.token.secret.key}")
    private String secretKey;

    private final UserDetailsService userDetailsService;
    private final UserService usersService;
    private final SessionRepository sessionRepository;

    @Override
    @Transactional
    public Authentication authenticate(final HttpServletRequest request) throws Exception {

        final String token = request.getHeader(SecurityConstants.AUTH_HEADER_NAME);
        final Jws<Claims> tokenData = parseToken(token);

        if (tokenData != null) {
            User user = getUserFromToken(tokenData);
            if (user != null) {
                if (!isTokenExpired(token)) {
                    return new UserAuthentication(user, this.usersService, token);
                } else {
                    throw new ServiceException("Token was expired");
                }
            }
        }
        return null;
    }

    private Jws<Claims> parseToken(final String token) {
        if (token != null) {
            try {
                return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
                return null;
            }
        }
        return null;
    }

    private boolean isTokenExpired(String token) {

        System.out.println("token: " + token);
        Session session = sessionRepository.findFirstByTokenOrderByTokenCreateDateDesc(token);

        if (session != null) {
            if (session.getTokenExpireDate() == null || session.getTokenExpireDate().isEmpty()) {
                return false;
            }
            Date tokenExpireDate = new Date(session.getTokenExpireDate());
            Calendar now = Calendar.getInstance();
            Calendar tokenExpirationDateCalendar = Calendar.getInstance();
            tokenExpirationDateCalendar.setTime(tokenExpireDate);
            boolean isTokenExpired = tokenExpirationDateCalendar.before(now);
            System.out.println("tokenExp " + isTokenExpired);
            if (!isTokenExpired) {
                Calendar newExpireDate = Calendar.getInstance();
                long dateTimeInMillis = newExpireDate.getTimeInMillis();
                newExpireDate.add(Calendar.MINUTE, TOKEN_EXPIRE_TIME);
                Date date = new Date(dateTimeInMillis + (TOKEN_EXPIRE_TIME * 60 * 1000));
                String exDate = DATE_FORMAT.format(date);
                session.setTokenExpireDate(exDate);
                sessionRepository.save(session);
            }
            return isTokenExpired;
        } else {
            throw new ServiceException("Session does not exist");
        }
    }

    private User getUserFromToken(final Jws<Claims> tokenData) throws Exception {
        try {
            return (User) userDetailsService.loadUserByUsername(tokenData.getBody().get("username").toString());
        } catch (Exception e) {
            throw new Exception("User" + tokenData.getBody().get("username").toString() + " not found");
        }
    }

}
