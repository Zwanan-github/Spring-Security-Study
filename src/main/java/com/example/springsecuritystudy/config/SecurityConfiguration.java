package com.example.springsecuritystudy.config;

import com.alibaba.fastjson.JSONObject;
import com.example.springsecuritystudy.common.Result;
import com.example.springsecuritystudy.service.AuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Autowired
    private AuthorizeService authorizeService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeRequests()
                // 开放首页
                .antMatchers("/index/**").permitAll()
                // 任何请求
                .anyRequest().authenticated()
                .and()
                // 登录url设置
                .formLogin().loginProcessingUrl("/api/auth/login")
                .successHandler(this::onAuthenticationSuccess)
                .failureHandler(this::onAuthenticationfailure)
                .and()
                // 登出
                .logout().logoutUrl("/api/auth/logout")
                .logoutSuccessHandler(this::onAuthenticationSuccess)
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(this::onAuthenticationfailure)
                .and()
                .userDetailsService(authorizeService)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private void onAuthenticationfailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        if (e instanceof BadCredentialsException) {
            httpServletResponse
                    .getWriter()
                    .write(JSONObject.toJSONString(Result.failure(403, "账号或密码错误")));
        } else {
            httpServletResponse
                    .getWriter()
                    .write(JSONObject.toJSONString(Result.failure(404, "404 not found")));
        }
    }

    private void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        if (httpServletRequest.getRequestURI().endsWith("/login")) {
            httpServletResponse
                    .getWriter()
                    .write(JSONObject.toJSONString(Result.success("登录成功")));
        } else if (httpServletRequest.getRequestURI().endsWith("logout")){
            httpServletResponse
                    .getWriter()
                    .write(JSONObject.toJSONString(Result.success("退出成功")));
        }
    }

}
