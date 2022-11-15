package com.tomik.akzhol.FirstSerurityApp.security;

import com.tomik.akzhol.FirstSerurityApp.services.PersonDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {
    private final PersonDetailService personDetailsService;
    @Autowired
    public AuthProviderImpl(PersonDetailService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }
    //Этот метод будет использоваться самим Security и сам Security будет передавать в метод
    //authenticate параметр(Обьект Authentication)
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails personDetails = personDetailsService.loadUserByUsername(username);
        String password = authentication.getCredentials().toString();
        if(!password.equals(personDetails.getPassword())){
            throw new BadCredentialsException("Incorrect password");
        }
        return new UsernamePasswordAuthenticationToken(personDetails,password, Collections.emptyList());
    }
    @Override
    //технический метод нужен чтобы понять для какого метода
    // Authentication работает AuthenticationProvider
    public boolean supports(Class<?> authentication) {
        return true;
    }
    //Если в приложении несколько AuthenticationProvider то будем
    // указывать для каких сценариев нужен какой
    // Authentication Provider
}
