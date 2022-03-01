package com.example.springpetproject.user.impl;

import com.example.springpetproject.user.UserEntity;
import com.example.springpetproject.user.UserRepository;
import com.example.springpetproject.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }
}
