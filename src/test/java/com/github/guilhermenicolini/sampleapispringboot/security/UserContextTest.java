package com.github.guilhermenicolini.sampleapispringboot.security;

import com.github.guilhermenicolini.sampleapispringboot.BaseTest;
import com.github.guilhermenicolini.sampleapispringboot.Objects;
import com.github.guilhermenicolini.sampleapispringboot.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserContextTest extends BaseTest {

    @Test
    public void userContext() {

        User user = Objects.getUser();
        UserContext ctx = new UserContext(user.getId(), user.getEmail(), user.getName());

        assertEquals("ac1a8db7-3a2d-4bc8-a0a0-35eb537d8944", ctx.getId());
        assertEquals("john.doe@inbox.my", ctx.getUsername());
        assertNull(ctx.getPassword());
        assertEquals(0, ctx.getAuthorities().size());
        assertEquals("John Doe", ctx.getName());
        assertTrue(ctx.isAccountNonExpired());
        assertTrue(ctx.isAccountNonLocked());
        assertTrue(ctx.isCredentialsNonExpired());
        assertTrue(ctx.isEnabled());
    }

    @Test
    public void userContextDefault() {

        UserContext ctx = new UserContext();

        assertNull(ctx.getId());
        assertNull(ctx.getUsername());
        assertNull(ctx.getPassword());
        assertEquals(0, ctx.getAuthorities().size());
        assertNull(ctx.getName());
        assertTrue(ctx.isAccountNonExpired());
        assertTrue(ctx.isAccountNonLocked());
        assertTrue(ctx.isCredentialsNonExpired());
        assertTrue(ctx.isEnabled());
    }
}
