package com.emerzonic.SpringApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;

    @Autowired
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username as principal,password as credentials, true from user where username=?")
                .authoritiesByUsernameQuery("select user_username as principal,role_name as role from user_roles where user_username=?")
                .passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");
    }

    protected void configure(HttpSecurity http ) throws Exception{
        http.authorizeRequests()
                .antMatchers("/post/new","/post/add","/post/edit/**","/post/update/**","/post/delete/**","/comment/**","/reply/**","/like/**")
                    .authenticated()
                .and()
                .formLogin()
                    .loginPage("/user/login")
                    .loginProcessingUrl("/user/login")
                    .permitAll().defaultSuccessUrl("/post/list")
                .and()
                    .logout().logoutSuccessUrl("/")
                    .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }
}
