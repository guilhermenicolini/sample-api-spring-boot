package com.github.guilhermenicolini.sampleapispringboot.security;

import com.github.guilhermenicolini.sampleapispringboot.BaseTest;
import com.github.guilhermenicolini.sampleapispringboot.domain.User;
import com.github.guilhermenicolini.sampleapispringboot.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class JwtTest extends BaseTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void generateToken() {
        User user = Objects.getUser();
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getName());
        assertNotNull(token);
        assertTrue(jwtUtil.validToken(token));
    }

    @Test
    public void generateTokenNoUserName() {
        User user = Objects.getUser();
        String token = jwtUtil.generateToken(user.getId(), null, user.getName());
        assertNotNull(token);
        assertFalse(jwtUtil.validToken(token));
    }

    @Test
    public void generateTokenNoExpiration() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJTYW1wbGUgQVBJIiwiYXVkIjoid3d3LmdpdGh1Yi5jb20vZ3VpbGhlcm1lbmljb2xpbmkiLCJzdWIiOiJqb2huLmRvZUBpbmJveC5teSIsImp0aSI6ImFjMWE4ZGI3LTNhMmQtNGJjOC1hMGEwLTM1ZWI1MzdkODk0NCIsIm5hbWUiOiJKb2huIERvZSJ9.Na4BwAHX_-4qE6K6ZOlWR-cwwDac7Mj3uLzVud802PikcQHY0o5rDFpQvbncWdfsK3cULuKLxky5TWwxSXQvXQ";
        assertNotNull(token);
        assertFalse(jwtUtil.validToken(token));
    }

    @Test
    public void generateTokenExpiredToken() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJTYW1wbGUgQVBJIiwiYXVkIjoid3d3LmdpdGh1Yi5jb20vZ3VpbGhlcm1lbmljb2xpbmkiLCJzdWIiOiJqb2huLmRvZUBpbmJveC5teSIsImp0aSI6ImFjMWE4ZGI3LTNhMmQtNGJjOC1hMGEwLTM1ZWI1MzdkODk0NCIsIm5hbWUiOiJKb2huIERvZSIsImV4cCI6MTU3MzkxNjQ2MX0.4zhnDJZ2X65X18zgPceM6YsoL7Otd3QB0KsEEgBI_gvnGmmppLkMO4OlK_529xt4NggCrSN5eoUggsQS2W-sXQ";
        assertNotNull(token);
        assertFalse(jwtUtil.validToken(token));
    }

    @Test
    public void getClaims() {
        User user = Objects.getUser();
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getName());
        assertNotNull(token);
        assertTrue(jwtUtil.validToken(token));
        assertEquals("John Doe", jwtUtil.getClaim("name", token));
        assertEquals(null, jwtUtil.getClaim("test", token));
    }

    @Test
    public void noClaims() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.e30.a8Dbdb7bRw78JZqukZx7tPV2_L9tcJpmHKOK0UuUq7Q";
        assertEquals(null, jwtUtil.getClaim("test", token));
    }

    @Test
    public void nullClaims() {
        assertEquals(null, jwtUtil.getClaim("id", null));
    }
}
