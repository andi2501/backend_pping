package de.academy.backend_pping.buddy_core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable() // CR: Muss disabled sein, sonst muss ich CSRF token des servers in meinem request mitsenden
                .headers().frameOptions().disable();
    }



//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/api/registration").permitAll() // neue Zeile: Zugriff aller auf Home und Register
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/api/login")
//                .permitAll()
//                .and()
//                .logout().logoutSuccessUrl("/").and() // neue Zeile: nach Logout Weiterleitung zu Home (statt Login)
//                .httpBasic();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}