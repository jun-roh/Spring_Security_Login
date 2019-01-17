package com.jun.login.config.security;

import com.jun.login.config.users.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    DataSource dataSource;

    @Autowired
    UsersService usersService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        log.info("security config..............");

        http.csrf().ignoringAntMatchers("/api/**");

        http
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll();

//        // Pages do not require login
//        http
//                .authorizeRequests()
//                .antMatchers("/css/**", "/font/**", "/img/**", "/js/**", "/uploads/**", "/menu", "/html/**", "/did","/login", "/users/**", "/api/**")
//                .permitAll();
//
//        // ROLE_USER Only
//        http
//                .authorizeRequests()
//                .antMatchers("managerSecret", "/manager/**")
//                .hasAuthority("MANAGER")
//                .anyRequest().authenticated();
//
//        // ROLE_ADMIN Only
//        http
//                .authorizeRequests()
//                .antMatchers("/adminSecret", "/admin/**")
//                .hasAuthority("ADMIN")
//                .anyRequest().authenticated();
////
////         When the user has logged in as XX.
////         But access a page that requires role YY,
////         AccessDeniedException will be thrown.
//        http
//                .authorizeRequests()
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/403");
//
//        // Form Login config
//        http
//                .authorizeRequests()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .successHandler(successHandler())
//                .failureUrl("/login?error=true");
//
//        http
//                .authorizeRequests()
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/")
//                .permitAll();

        // Logout Config
//        http
//                .authorizeRequests()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new SecurityHandler();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        log.info("build Auth global........");
        auth.userDetailsService(usersService).passwordEncoder(passwordEncoder());

    }
}