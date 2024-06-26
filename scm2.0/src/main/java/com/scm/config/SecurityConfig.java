package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.scm.services.impl.SecurityCustomUserDetailService;


@Configuration
public class SecurityConfig {

    // @Bean
    // public UserDetailsService userDetailsService(){
    //     UserDetails user1=User.withDefaultPasswordEncoder()
    //     .username("admin")
    //     .password("admin123")
    //     .roles("ROLE","ADMIN")
    //     .build();
    //     UserDetails user2=User.withDefaultPasswordEncoder()
    //     .username("root")
    //     .password("root123")
    //     .roles("ADMIN")
    //     .build();
    //     var inmemoryUserDetailsManager=new InMemoryUserDetailsManager(user1,user2);
    //     return inmemoryUserDetailsManager;
    // }
    
    @Autowired
    private SecurityCustomUserDetailService securityCustomUserDetailService;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(securityCustomUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
