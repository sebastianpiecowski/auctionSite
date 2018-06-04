package com.zai.projekt.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/index.html",
            "/style_index.css",
            "/style_index.sass",
            "/announcement.html",
            "/style_announcement.css",
            "/style_announcement.sass",
            "/assets/**",
            "/js/**",
            "/background.jpg",
            "/logo3.png",
            "/favicon.ico",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/announcement/all",
            "/user/sign_up",
            "/user/login",
            "/announcement/category_id*",
            "/category/all",
            "/announcement/user*"
    };
    
    @Autowired
    private MyUserDetailsService detailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/**/**").authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
    
}
