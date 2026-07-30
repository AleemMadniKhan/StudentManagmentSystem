package com.StudentManagmentSystem.SMS.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.StudentManagmentSystem.SMS.model.User;
import com.StudentManagmentSystem.SMS.repository.UserRepo;
import com.StudentManagmentSystem.SMS.security.JwtService;
import com.StudentManagmentSystem.SMS.security.UserPrincipal;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

    private final JwtService jwtService;
    private final UserRepo repo;
    public JwtFilter(JwtService jwtService, UserRepo repo){
        this.jwtService = jwtService;
        this.repo = repo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String authHead = request.getHeader("authorization");
                String token = null;
                Integer userId = null;

                if(authHead != null && authHead.startsWith("Bearer ")){
                    token = authHead.substring(7);
                    try {
                        userId = jwtService.extractUserId(token); 
                    } catch (ExpiredJwtException e) {
                        logger.warn("JWT token has expired: " + e.getMessage());
                    } catch (Exception e) {
                        logger.error("Error parsing JWT token: " + e.getMessage());
                    }
                }

                if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                   Optional<User> user =  repo.findById(userId);
                   UserPrincipal userPrincipal = new UserPrincipal(user.get());
                   if (jwtService.validateToken(token, userPrincipal)) {
                    UsernamePasswordAuthenticationToken authToken = 
                    new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                   }

                }
            filterChain.doFilter(request, response);
    }
    
}
