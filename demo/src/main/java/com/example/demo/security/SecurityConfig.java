package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/5/15 11:30
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter = new MyUsernamePasswordAuthenticationFilter();
        myUsernamePasswordAuthenticationFilter.setPostOnly(true);
        myUsernamePasswordAuthenticationFilter.setAuthenticationManager(this.authenticationManager());
        myUsernamePasswordAuthenticationFilter.setUsernameParameter("name_key");
        myUsernamePasswordAuthenticationFilter.setPasswordParameter("pwd_key");
        myUsernamePasswordAuthenticationFilter.setVerificationCodeParameter("verification_code");
        myUsernamePasswordAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/checkLogin", "POST"));
       // myUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(simpleUrlAuthenticationFailureHandler());
       // myUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
      //  myUsernamePasswordAuthenticationFilter.setSessionAuthenticationStrategy(new MyConcurrentSessionControlAuthenticationStrategy(sessionRegistry));
        return myUsernamePasswordAuthenticationFilter;
    }


}
