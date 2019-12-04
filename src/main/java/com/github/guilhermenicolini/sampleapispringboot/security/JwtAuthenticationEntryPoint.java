package com.github.guilhermenicolini.sampleapispringboot.security;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // only protected end-points enter here

        // get authorization token
        String header = request.getHeader("Authorization");

        // if there is no token, return unauthorized missing bearer token
        if (header == null || !header.startsWith("Bearer ")) {
            setResponse(response, "Missing token for this request");
        } else { //return invalid token if is bearer
                setResponse(response, "Invalid token");
        }
    }

    private void setResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(new JSONObject()
                .put("errors", Collections.singleton(message))
                .toString());
    }

}
