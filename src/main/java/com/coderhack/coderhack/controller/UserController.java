package com.coderhack.coderhack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhack.coderhack.dto.AllUserResponse;
import com.coderhack.coderhack.dto.UpdatedScoreDto;
import com.coderhack.coderhack.entity.User;
import com.coderhack.coderhack.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/coderHack/apis/v1")
public class UserController {
    
    @Autowired
    UserService userService;

    // POST /users - Register a new user to the contest
    @PostMapping("/users")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        String createdUserId=userService.createUser(user);
        return new ResponseEntity<String>(String.format("user with userId: %s is created", createdUserId), HttpStatus.CREATED);
    }
    
    //GET /users - Retrieve a list of all registered users
    @GetMapping("/users")
    public ResponseEntity<AllUserResponse> getAllUser() {
        AllUserResponse allUserResponse=userService.getAllUser();
        return new ResponseEntity<AllUserResponse>(allUserResponse,HttpStatus.OK);
    }
    
    //GET /users/{userId} - Retrieve the details of a specific user
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user=userService.getUserById(userId);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
    
    //DELETE /users/{userId} - Deregister a specific user from the contes
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable String userId){
        userService.deleteUserById(userId);
        return new ResponseEntity<String>(String.format("user with userId: %s is deleted", userId), HttpStatus.OK);
    }

    // PUT /users/{userId} - Update the score of a specific user
    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUserScore(@PathVariable String userId,@Valid @RequestBody UpdatedScoreDto updateScore) {
        User user = userService.updateScore(userId, updateScore.getScore());
        return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
    }
}
