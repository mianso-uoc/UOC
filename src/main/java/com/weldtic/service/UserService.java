package com.weldtic.service;

import java.util.List;

import com.weldtic.model.User;

public interface UserService {

    public void saveUser(User user);
    public List<Object> isUserPresent(User user);
}
