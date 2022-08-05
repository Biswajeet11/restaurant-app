package com.restaurantmanagement.service;

import com.restaurantmanagement.model.User;
import com.restaurantmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){

        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findUserById(userId);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }
}
