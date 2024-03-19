package com.spring.mmm.common.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.mmm.common.config.RedisDao;
import com.spring.mmm.common.exception.SecurityExceptionDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        System.out.println("액세스 토큰: "+token);

        if (token != null) {
            String email = jwtProvider.getUserInfoFromToken(token);
            System.out.println("email : "+email);
            if(!jwtProvider.validateToken(token)){
                String reToken = redisDao.getRefreshToken(email);
                System.out.println("reToken : "+reToken);
                jwtProvider.reissueAtk(email, reToken);
            }
            setAuthentication(email);
        }
        filterChain.doFilter(request,response);
    }

    private void setAuthentication(String email) {
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
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}