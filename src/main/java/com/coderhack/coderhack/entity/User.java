package com.coderhack.coderhack.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Document
public class User {

    @Id
    @NotEmpty
    private String userId;

    @NotEmpty
    private String userName;

    @Min(0)
    @Max(100)
    private Integer score;

    private List<Badge> Badges;

    public User(String userId,String userName){
        this.userId=userId;
        this.userName=userName;
        this.score=0;
        this.Badges=new ArrayList<>();
    }


}
