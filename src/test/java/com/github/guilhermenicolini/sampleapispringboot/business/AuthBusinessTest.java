package com.github.guilhermenicolini.sampleapispringboot.business;

import com.github.guilhermenicolini.sampleapispringboot.BaseTest;
import com.github.guilhermenicolini.sampleapispringboot.Objects;
import com.github.guilhermenicolini.sampleapispringboot.dto.UserDto;
import com.github.guilhermenicolini.sampleapispringboot.exceptions.UnauthorizedException;
import com.github.guilhermenicolini.sampleapispringboot.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthBusinessTest extends BaseTest {

    @MockBean
    private UserService userService;

    @Autowired
    private AuthBusiness authBusiness;

    @Test
    public void authenticate() {

        when(userService.findByEmail(any())).thenReturn(Objects.getUser());
        when(userService.checkPassword(any(), any())).thenReturn(true);

        UserDto user = authBusiness.authenticate(Objects.getLoginDto());

        verify(userService, times(1)).findByEmail(any());
        assertNotNull(user);

        assertEquals("ac1a8db7-3a2d-4bc8-a0a0-35eb537d8944", user.getId());
        assertEquals("john.doe@inbox.my", user.getEmail());
        assertEquals("John Doe", user.getName());
    }

    @Test
    public void authenticateNotFound() {

        when(userService.findByEmail(any())).thenReturn(null);
        when(userService.checkPassword(any(), any())).thenReturn(true);

        assertThrows(UnauthorizedException.class, () -> authBusiness.authenticate(Objects.getLoginDto()));

        verify(userService, times(1)).findByEmail(any());
    }

    @Test
    public void authenticateWrong() {

        when(userService.findByEmail(any())).thenReturn(Objects.getUser());
        when(userService.checkPassword(any(), any())).thenReturn(false);

        assertThrows(UnauthorizedException.class, () -> authBusiness.authenticate(Objects.getLoginDto()));

        verify(userService, times(1)).findByEmail(any());
    }
}
