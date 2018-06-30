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
            "/index.html*",
            "/signup.html",
            "/style_index.css",
            "/style_index.sass",
            "/login.html",
            "/toConfirm.html",
            "/myAnnouncements.html",
            "/announcement.html",
            "/announcement.html?id=**",
            "/style.css",
            "/style.sass",
            "/assets/**",
            "/js/**",
            "/image/**",
            "/favicon.ico",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/announcement/all",
            "/user/sign_up",
            "/user/login",
            "/announcement/all",
            "/annoucement/id*",
            "/add.html",
            "/announcement/search*",
            "/announcement/category_id*/search*",
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
                .antMatchers("/**/**").hasAnyRole("ADMIN, USER")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
    
}
