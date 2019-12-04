package com.github.guilhermenicolini.sampleapispringboot.business;

import com.github.guilhermenicolini.sampleapispringboot.domain.User;
import com.github.guilhermenicolini.sampleapispringboot.dto.CreateUserDto;
import com.github.guilhermenicolini.sampleapispringboot.exceptions.BusinessException;
import com.github.guilhermenicolini.sampleapispringboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserBusiness {

    @Autowired
    private UserService userService;

    @Transactional
    public String signUp(CreateUserDto payload) {

        User tmp = userService.findByEmail(payload.getEmail());
        if (tmp != null) {
            throw new BusinessException("Email already registered");
        }

        User user = userService.fromSignUp(payload);
        userService.create(user);

        return user.getId();
    }
}
