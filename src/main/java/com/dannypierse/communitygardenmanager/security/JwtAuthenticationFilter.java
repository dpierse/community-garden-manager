package com.dannypierse.communitygardenmanager.security;

import com.dannypierse.communitygardenmanager.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response,
            jakarta.servlet.FilterChain filterChain
    ) throws java.io.IOException, jakarta.servlet.ServletException {

        String authHeader = request.getHeader("Authorization");

        System.out.println("AUTH HEADER: " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            try {
                String token = authHeader.substring(7);

                String email = jwtService.extractEmail(token);

                var user = userRepository.findByEmail(email);

                if (user.isEmpty()) {
                    filterChain.doFilter(request, response);
                    return;
                }

                System.out.println("DATABASE ROLE: " + user.get().getRole());

                var authorities = List.of(
                        new SimpleGrantedAuthority("ROLE_" + user.get().getRole())
                );



                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                authorities
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("USER: " + email);
                System.out.println("AUTHORITIES: " + authorities);

            } catch (Exception e) {
                System.out.println("Invalid JWT token");
            }
        }

        filterChain.doFilter(request, response);
    }
}