package com.yfc.gc.app.config;

import com.yfc.gc.core.base.RestResult;
import com.yfc.gc.core.login.filter.JwtAuthenticationTokenFilter;
import com.yfc.gc.core.util.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * 安全模块配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * @Author WangYX
     * @Description //TODO 装载BCrypt密码编码器
     * @Date 2018/12/24 下午10:18
     **/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            // 使用JWT，不需要csrf
            .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(getAuthenticationEntryPoint()).and()

            // 基于token，不需要session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

            .authorizeRequests()

            // 允许对于网站静态资源的无授权访问
            .antMatchers("/api/wx/user/auth/*",
                    "/api/wx/shop/commodity/notifyOrdinary",
                    "/api/wx/shop/package/notifyPackage",
                    "/api/wx/shop/group/notifyGroup",
                    "/api/wx/shop/campaign/notifyCampaign")
            .permitAll()
            // 除上面外的所有请求全部需要鉴权认证
            .anyRequest().authenticated();

        // 添加JWT filter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 禁用缓存
        http.headers().cacheControl();

    }

    /**
     * @Author WangYX
     * @Description //TODO 无权访问处理方法
     * @Date 2018/12/24 下午10:23
     **/
    @Bean
    public AuthenticationEntryPoint getAuthenticationEntryPoint(){
        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setHeader("Content-Species", "application/json;charset=utf-8");
            httpServletResponse.setStatus(HttpStatus.OK.value());
            httpServletResponse.getWriter().write(JSONUtil.toJson(RestResult.error("99999", "登录状态失效")));
            httpServletResponse.getWriter().flush();
        };
    }


}