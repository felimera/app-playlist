package com.project.appplaylist.filter;

import com.project.appplaylist.service.jwt.UserJwtServiceImpl;
import com.project.appplaylist.util.Constants;
import com.project.appplaylist.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    UserJwtServiceImpl userJwtServiceImp;
    JwtUtil jwtUtil;

    @Autowired
    public JwtRequestFilter(UserJwtServiceImpl userJwtServiceImp, JwtUtil jwtUtil) {
        this.userJwtServiceImp = userJwtServiceImp;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(Constants.AUTHORIZATION);
        String token = null;
        String useraname = null;

        if (Objects.nonNull(authHeader) && authHeader.startsWith(Constants.BEARER)) {
            token = authHeader.substring(7);
            useraname = jwtUtil.extractUsername(token);
        }

        if (Objects.nonNull(useraname) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            UserDetails userDetails = userJwtServiceImp.loadUserByUsername(useraname);
            if (jwtUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
