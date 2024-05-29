package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.helper.ResourceNotFoundException;
import com.scm.repo.UserRepo;
import com.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public User createUser(User user) {
        // TODO Auto-generated method stub
        String generatedUserId=UUID.randomUUID().toString();
        user.setUserId(generatedUserId);
        return userRepo.save(user);
    }

    @Override
    public String deleteUser(String userId) {
        // TODO Auto-generated method stub
        userRepo.deleteById(userId);

        return "User deleted Successfully";
    }

    @Override
    public List<User> getAllUser() {
        // TODO Auto-generated method stub

        return userRepo.findAll();
    }

    @Override
    public Optional<User> getUserById(String userId) {
        // TODO Auto-generated method stub
        return userRepo.findById(userId);
        
    }

    @Override
    public boolean isUserExist(String userId) {
        // TODO Auto-generated method stub
        User user=userRepo.findById(userId).orElse(null);
        return user==null?false:true;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        // TODO Auto-generated method stub
        User user=userRepo.findByEmail(email).orElse(null);
        return user==null?false:true;
    }

    @Override
    public Optional<User> updateUser(User user) {
        // TODO Auto-generated method stub
       User prevUser=userRepo.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException("User with this given userId is not found"));
       prevUser.setName(user.getName());
       prevUser.setEmail(user.getEmail());
       prevUser.setPassword(user.getPassword()); 
       prevUser.setPhonenumber(user.getPhonenumber());
       prevUser.setProfilepic(user.getProfilepic());
       prevUser.setEnabled(user.isEnabled());
       prevUser.setAbout(user.getAbout());
       prevUser.setEmailVerified(user.isEmailVerified());
       prevUser.setPhoneVerified(user.isPhoneVerified());
       User user2=userRepo.save(prevUser);
       return Optional.ofNullable(user2);
    }

}
