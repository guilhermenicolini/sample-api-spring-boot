package com.github.guilhermenicolini.sampleapispringboot.business;

import com.github.guilhermenicolini.sampleapispringboot.BaseTest;
import com.github.guilhermenicolini.sampleapispringboot.exceptions.BusinessException;
import com.github.guilhermenicolini.sampleapispringboot.services.UserService;
import com.github.guilhermenicolini.sampleapispringboot.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserBusinessTest extends BaseTest {

    @MockBean
    private UserService userService;

    @Autowired
    private UserBusiness userBusiness;

    @Test
    public void signUp() {

        when(userService.findByEmail(any())).thenReturn(null);
        when(userService.fromSignUp(any())).thenReturn(Objects.getUser());
        when(userService.create(any())).thenReturn(Objects.getUser());

        String id = userBusiness.signUp(Objects.getCreateUserDto());

        verify(userService, times(1)).findByEmail(any());
        verify(userService, times(1)).fromSignUp(any());
        verify(userService, times(1)).create(any());
        assertEquals("ac1a8db7-3a2d-4bc8-a0a0-35eb537d8944", id);
    }

    @Test
    public void signUpRegistered() {

        when(userService.findByEmail(any())).thenReturn(Objects.getUser());
        when(userService.fromSignUp(any())).thenReturn(Objects.getUser());
        when(userService.create(any())).thenReturn(Objects.getUser());

        assertThrows(BusinessException.class, () -> userBusiness.signUp(Objects.getCreateUserDto()));

        verify(userService, times(1)).findByEmail(any());
        verify(userService, times(0)).fromSignUp(any());
        verify(userService, times(0)).create(any());
    }
}
