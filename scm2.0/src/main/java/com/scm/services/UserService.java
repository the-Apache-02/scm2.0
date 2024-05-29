package com.scm.services;

import java.util.List;
import java.util.Optional;

import com.scm.entities.User;

public interface UserService {

    public User createUser(User user);

    public Optional<User>getUserById(String userId);

    public Optional<User> updateUser(User user);

    public String deleteUser(String userId);

    public boolean isUserExist(String userId);

    public boolean isUserExistByEmail(String email);

    public List<User> getAllUser();
}
