
package com.hostel.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Set;

public class AuthInterceptor implements HandlerInterceptor {
    private static final Set<String> WHITELIST = Set.of(
        "/", "/login", "/signup", "/doLogin", "/doSignup", "/h2-console"
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        if (WHITELIST.stream().anyMatch(path::startsWith)) return true;
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) return true;
        response.sendRedirect("/login");
        return false;
    }
}
