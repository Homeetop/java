package com.topsoft.bvs.service;

import com.topsoft.bvs.entity.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}