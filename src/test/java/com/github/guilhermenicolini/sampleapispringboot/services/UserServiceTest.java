package com.github.guilhermenicolini.sampleapispringboot.services;

import com.github.guilhermenicolini.sampleapispringboot.BaseTest;
import com.github.guilhermenicolini.sampleapispringboot.domain.User;
import com.github.guilhermenicolini.sampleapispringboot.exceptions.UnauthorizedException;
import com.github.guilhermenicolini.sampleapispringboot.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest extends BaseTest {

    private static final String USER_PASS = "$2a$10$U/cz5qHaFbMzxJDTzjOC4u6NwO7wHKUD6uniMdzCK8ddnl8BpFS7O";
    private static final String USER_NAME = "John Doe";
    private static final String USER_EMAIL = "john.doe@inbox.my";

    @Autowired
    private UserService userService;

    @Test
    public void create() {
        User user = userService.create(Objects.getUser2());

        assertEquals("jane.doe@inbox.my", user.getEmail());
        assertEquals("Jane Doe", user.getName());
        assertEquals(USER_PASS, user.getPassword());
    }

    @Test
    @Transactional
    public void findByEmail() {
        User user = userService.findByEmail(USER_EMAIL);

        assertEquals("ac1a8db7-3a2d-4bc8-a0a0-35eb537d8944", user.getId());
        assertEquals(USER_EMAIL, user.getEmail());
        assertEquals(USER_NAME, user.getName());
        assertEquals(USER_PASS, user.getPassword());

        assertNotNull(user.getBeers());
        assertEquals(1, user.getBeers().size());
    }

    @Test
    public void checkPassword() {
        assertTrue(userService.checkPassword("123456", USER_PASS));
    }

    @Test
    public void fromSignUp() {
        User user = userService.fromSignUp(Objects.getCreateUserDto());

        assertEquals(USER_EMAIL, user.getEmail());
        assertEquals(USER_NAME, user.getName());
    }

    @Test
    public void authenticateFailed() {
        assertThrows(UnauthorizedException.class, UserService::authenticated);
    }
}
