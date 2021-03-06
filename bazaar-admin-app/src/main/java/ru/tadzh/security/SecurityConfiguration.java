package ru.tadzh.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    public void authConfig(AuthenticationManagerBuilder auth,
                           PasswordEncoder passwordEncoder,
                           UserAuthService userAuthService) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("mem_user")
                .password(passwordEncoder.encode("password"))
                .roles("ADMIN")
                .and()
                .withUser("mem_guest")
                .password(passwordEncoder.encode("password"))
                .roles("GUEST");

        auth.userDetailsService(userAuthService);
    }

//    @Configuration
//    @Order (1)
//    public static class AiWebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .antMatcher("/api/**")
//                    .authorizeRequests()
//                    .anyRequest().authenticated()
//                    .and()
//                    .httpBasic()
//                    .authenticationEntryPoint((req, resp, exception) -> {
//                        resp.setContentType("aplication/json");
//                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                        resp.setCharacterEncoding("UTF-8");
//                        resp.getWriter().println("{ \"error\" : \"" + exception.getMessage() + "\" }");
//                    })
//                     .and()
//                     .csrf().disable()
//                     .sessionManagement()
//                     .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        }
//    }

    @Configuration
//    @Order (2)
    public static class UiWebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/**/*.css", "/**/*.js").permitAll()
                    .antMatchers("/product/**", "/user/register", "/user/new").permitAll()
                    .antMatchers("/user/**").hasAnyRole("ADMIN")
                    .antMatchers("/access_denied").authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/product")
                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/access_denied");
        }
    }
}