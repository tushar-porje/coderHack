package com.coderhack.coderhack.service.serviceimpl;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.coderhack.coderhack.dto.AllUserResponse;
import com.coderhack.coderhack.entity.Badge;
import com.coderhack.coderhack.entity.User;
import com.coderhack.coderhack.exception.ResourceAlreadyExistException;
import com.coderhack.coderhack.exception.ResourceNotFoundException;
import com.coderhack.coderhack.repository.UserRepository;
import com.coderhack.coderhack.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public AllUserResponse getAllUser() {
        List<User> users = userRepository.findAll(Sort.by(Direction.ASC,"score"));
        return new AllUserResponse(users,users.size());        
    }

    @Override
    public User getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","Id",id));
        return user;
    }

    @Override
    public String createUser(User user) {
        if(!userRepository.existsById(user.getUserId())){
            User createdUser= userRepository.save(user);
            return createdUser.getUserId(); 
        }else{
            throw new ResourceAlreadyExistException("user", "Id", user.getUserId());
        }
    }

    @Override
    public User updateScore(String userId, Integer score) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        user.setScore(score);
        updateBadge(user,score);
        return user;
    }

    private void updateBadge(User user,Integer score){
        List<Badge> badges= new ArrayList<>();
        if(score>=1&&score<=30){
            badges.add(Badge.CODE_NINJA);
        }else if(score>=31&&score<=60){
            badges.add(Badge.CODE_CHAMP);
            badges.add(Badge.CODE_NINJA);
        }else if(score>=61&&score<=100){
            badges.add(Badge.CODE_CHAMP);
            badges.add(Badge.CODE_NINJA);
            badges.add(Badge.CODE_MASTER);
        }
        user.setBadges(badges);
        userRepository.save(user);
    }

    @Override
    public String deleteUserById(String id) {
        userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","Id",id));
        userRepository.deleteById(id);
        return id;
    }
    
}
