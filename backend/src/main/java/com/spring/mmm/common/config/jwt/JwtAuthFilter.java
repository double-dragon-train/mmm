package com.spring.mmm.common.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.mmm.common.config.RedisDao;
import com.spring.mmm.common.exception.SecurityExceptionDto;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final RedisDao redisDao;
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        String token = jwtProvider.resolveToken(request);
        if (token != null) {

            if(!jwtProvider.validateToken(token)){
                response.sendError(401, "만료되었습니다.");
                jwtExceptionHandler(response,"401", HttpStatus.BAD_REQUEST.value() );
                return;
            }
            // 검증 후 인증 객체 생성하여 securityContextHolder에서 관리
            Claims userInfo = jwtProvider.getUserInfoFromToken(token);
            setAuthentication(userInfo.get("email", String.class));
        }
        filterChain.doFilter(request,response);
    }

    private void setAuthentication(String email ) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = jwtProvider.createUserAuthentication(email);
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

    }



    public void jwtExceptionHandler(HttpServletResponse response, String msg, int statusCode) {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        try {
            String json = new ObjectMapper().writeValueAsString(new SecurityExceptionDto(statusCode, msg));
            //, ObjectMapper를 사용하여 SecurityExceptionDto 객체를 JSON 문자열로 변환
            response.getWriter().write(json); //JSON 문자열을 응답으로 작성
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}