package com.coderhack.coderhack.service;

import com.coderhack.coderhack.dto.AllUserResponse;
import com.coderhack.coderhack.entity.User;

public interface UserService {
    AllUserResponse getAllUser();
    User getUserById(String id);
    String createUser(User user);
    User updateScore(String userId,Integer score);
    String deleteUserById(String id);
}
