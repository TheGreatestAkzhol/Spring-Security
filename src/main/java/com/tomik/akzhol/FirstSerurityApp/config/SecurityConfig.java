package com.tomik.akzhol.FirstSerurityApp.config;

import com.tomik.akzhol.FirstSerurityApp.services.PersonDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//все что касается springSecurity настраивается в этом классе
@EnableWebSecurity
//дает понять что это конфигурационный класс для Spring Security
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final PersonDetailService personDetailService;
    @Autowired
    public SecurityConfig(PersonDetailService personDetailService) {
        this.personDetailService = personDetailService;
    }
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        //конфигурируем сам Spring Security
        //конфигурируем авторизацию(давать доступ пользователю на определенные страницы)
        http.csrf().disable().//отключаем защиту от межсайтовой подделки запросов (об этом будет отдельный урок)
                authorizeRequests()
                        .antMatchers("/auth/login","/auth/registration","/error").permitAll()
                        .anyRequest().authenticated()
                        .and().
                formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/",true)
                .failureUrl("/auth/login?error")
                .and();
    }
    //Настраиваем аутентификацию
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailService);
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    //    private final AuthProviderImpl authProvider;
//    @Autowired
//    public SecurityConfig(AuthProviderImpl authProvider) {
//        this.authProvider = authProvider;
//    }
//
//    @Override
//    //настраивается аутентификация
//    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(authProvider);
//    }
}
