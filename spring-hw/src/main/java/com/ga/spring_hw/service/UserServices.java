package com.ga.spring_hw.service;

import com.ga.spring_hw.model.User;
import com.ga.spring_hw.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User register(User user) {

        if (userRepository.existsByEmailAddress(user.getEmailAddress())) {
            throw new RuntimeException("Email already exists");
        }

        return userRepository.save(user);
    }

    public User findUserByEmailAddress(String email) {
        return userRepository.findUserByEmailAddress(email);
    }


}
