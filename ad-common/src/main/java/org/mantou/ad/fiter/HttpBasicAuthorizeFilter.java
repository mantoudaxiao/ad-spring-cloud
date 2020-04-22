package org.mantou.ad.fiter;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.mantou.ad.common.RespWrapper;
import org.mantou.ad.utils.JWTUtils;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpBasicAuthorizeFilter implements Filter {

    JWTUtils jwt = JWTUtils.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token)) {
            PrintWriter writer = response.getWriter();
            RespWrapper respWrapper = RespWrapper.error("非法请求【缺少Authorization信息】");
            writer.write(new ObjectMapper().writeValueAsString(respWrapper));
            writer.flush();
            writer.close();
            return;
        }
        JWTUtils.JWTResult jwtResult = jwt.checkToken(token);
        if (!jwtResult.isRes()) {
            PrintWriter writer = response.getWriter();
            RespWrapper respWrapper = RespWrapper.error(jwtResult.getMsg());
            writer.write(new ObjectMapper().writeValueAsString(respWrapper));
            writer.flush();
            writer.close();
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
