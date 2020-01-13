package com.github.guilhermenicolini.sampleapispringboot.business;

import com.github.guilhermenicolini.sampleapispringboot.domain.User;
import com.github.guilhermenicolini.sampleapispringboot.dto.LoginDto;
import com.github.guilhermenicolini.sampleapispringboot.dto.UserDto;
import com.github.guilhermenicolini.sampleapispringboot.exceptions.BusinessException;
import com.github.guilhermenicolini.sampleapispringboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthBusiness {

    @Autowired
    private UserService userService;

    public UserDto authenticate(LoginDto payload) {

        User user = userService.findByEmail(payload.getEmail());
        if (user == null ||
                !userService.checkPassword(payload.getPassword(), user.getPassword())) {
            throw new BusinessException("Invalid login or password");
        }

        return new UserDto(user);
    }


}
