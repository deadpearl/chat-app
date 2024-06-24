package kz.pearl.chat_service.service.impl;

import kz.pearl.chat_service.entity.User;
import kz.pearl.chat_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Primary
@Service
@RequiredArgsConstructor
public class BasicUserDetailsService implements UserDetailsService {

    private final UserService usersService;

    @Override
    public UserDetails loadUserByUsername(String idnOrMobilePhoneOrUsername) throws UsernameNotFoundException {

        User user = usersService.findUserByUsername(idnOrMobilePhoneOrUsername);

        if (user != null) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

            if(user.getEmail() != null && user.getPassword() != null){
                return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
            }else{
                throw new ServiceException("User with idn or password is empty");
            }

        } else {
            throw new UsernameNotFoundException("User with idnOrMobilePhoneOrUsername: " + idnOrMobilePhoneOrUsername + " not found");
        }

    }
}
