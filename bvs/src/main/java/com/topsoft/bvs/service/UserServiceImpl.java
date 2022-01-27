package com.topsoft.bvs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.topsoft.bvs.entity.User;
import com.topsoft.bvs.respository.UserRepository;



@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
  
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }



	
}