package kz.pearl.chat_service.entity;

import kz.pearl.chat_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collection;

public class UserAuthentication implements Authentication {

    private static final long serialVersionUID = -7170337143687707450L;

    private final User user;
    private String token;
    private final UserService usersService;
    private boolean authenticated = true;

    @Autowired
    public UserAuthentication(final User user, final UserService usersService, String token){
        this.user = user;
        this.usersService = usersService;
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return this.user.getPassword();
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user.getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

    public kz.pearl.chat_service.entity.User getUser() {

        return usersService.findUserByUsername(user.getUsername());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
