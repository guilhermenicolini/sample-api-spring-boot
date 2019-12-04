package com.github.guilhermenicolini.sampleapispringboot.services;

import com.github.guilhermenicolini.sampleapispringboot.domain.User;
import com.github.guilhermenicolini.sampleapispringboot.dto.CreateUserDto;
import com.github.guilhermenicolini.sampleapispringboot.exceptions.UnauthorizedException;
import com.github.guilhermenicolini.sampleapispringboot.repositories.UserRepository;
import com.github.guilhermenicolini.sampleapispringboot.security.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    public User create(User user) {
        user.setId(null);
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    public boolean checkPassword(String entryPassword, String storedPassword) {
        return bcrypt.matches(entryPassword, storedPassword);
    }

    public User fromSignUp(CreateUserDto objDto) {
        User user = new User();
        user.setName(objDto.getName());
        user.setEmail(objDto.getEmail());
        user.setPassword(bcrypt.encode(objDto.getPassword()));
        return user;
    }

    public static UserContext authenticated() {
        try {
            return (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e) {
            throw new UnauthorizedException("Access denied", e);
        }
    }
}
