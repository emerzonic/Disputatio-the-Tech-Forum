package com.emerzonic.SpringApp;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser(users.username("john").password("pass").roles("user"));

    }

    protected void configure(HttpSecurity http ) throws Exception{
        http.authorizeRequests()
                .antMatchers("/post/new","/post/add","/post/edit/**","/post/update/**","/post/delete/**")
                    .authenticated()
                .and()
                .formLogin()
                    .loginPage("/user/login")
                    .loginProcessingUrl("/user/login")
                    .permitAll()
                .and()
                    .logout().logoutSuccessUrl("/")
                    .permitAll();

    }
}
