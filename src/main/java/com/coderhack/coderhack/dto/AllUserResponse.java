package com.coderhack.coderhack.dto;

import java.util.List;

import com.coderhack.coderhack.entity.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerialize
public class AllUserResponse {
    
    private List<User> users;
    private int totalUsers;

}
