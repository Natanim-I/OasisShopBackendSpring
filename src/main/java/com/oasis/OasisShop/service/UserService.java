package com.oasis.OasisShop.service;

import com.oasis.OasisShop.model.User;
import com.oasis.OasisShop.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User registerUser(User user) {
        return userRepo.save(user);
    }
}
