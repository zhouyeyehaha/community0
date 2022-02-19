package com.jc.community.config;

import com.jc.community.util.CommunityConstant;
import com.jc.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements CommunityConstant {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    // 放开静态资源
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 授权
        http.authorizeRequests()
                // 下面是登陆之后才能访问的路径
                .antMatchers(
                        "/user/setting",
                        "/user/upload",
                        "/user/setting",
                        "/discuss/add",
                        "/letter/**",
                        "/notice/list",
                        "/like",
                        "/follow",
                        "/unfollow"
                )
                // 上面路径的权限 这三个权限都可以看这些
                .hasAnyAuthority(
                        AUTHORITY_USER,
                        AUTHORITY_ADMIN,
                        AUTHORITY_MODERATOR
                )
                .antMatchers(
                        "/discuss/top",
                        "/discuss/wonderful"
                )
                .hasAnyAuthority(
                        AUTHORITY_MODERATOR
                )
                .antMatchers(
                        "/discuss/delete",
                        "/data/**",
                        "/actuator/**"
                )
                .hasAnyAuthority(
                        AUTHORITY_ADMIN
                )
                // 其他路径登不登陆都可以访问
                .anyRequest().permitAll()
                // 禁用csrf功能
                .and().csrf().disable();

        // 权限不够时的处理
        http.exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    // 没有登录
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                        String xRequestedWith = request.getHeader("x-requested-with");
                        // 如果相等就是异步请求
                        if ("XMLHttpRequest".equals(xRequestedWith)) {
                            response.setContentType("application/plain;charset=utf-8");
                            PrintWriter writer = response.getWriter();
                            //System.out.println("还没有登录");
                            logger.info("还没登录（异步请求）");
                            writer.write(CommunityUtil.getJSONString(403, "你还没有登录"));
                        }
                        // 非异步 直接重定向
                        else {
                            logger.info("还没登录");
                            response.sendRedirect(request.getContextPath() + "/login");
                        }
                    }
                })
                .accessDeniedHandler(new AccessDeniedHandler() {
                    // 没有权限
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
                        String xRequestedWith = request.getHeader("x-requested-with");
                        if ("XMLHttpRequest".equals(xRequestedWith)) {
                            response.setContentType("application/plain;charset=utf-8");
                            PrintWriter writer = response.getWriter();
                            logger.info("没有权限（异步请求）");
                            writer.write(CommunityUtil.getJSONString(403, "你没有访问此功能的权限"));
                        }
                        // 非异步 直接重定向
                        else {
                            logger.info("没有权限");
                            response.sendRedirect(request.getContextPath() + "/denied");
                        }
                    }
                });

        // Security底层默认会拦截/logout请求，进行退出处理
        // 覆盖他默认的逻辑，才执行自己的退出逻辑
        // 随便设置一个不是自己的路径就可以了
        http.logout().logoutUrl("/securitylogout");

    }


}
