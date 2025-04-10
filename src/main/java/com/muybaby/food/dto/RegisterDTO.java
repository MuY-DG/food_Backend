package com.muybaby.food.dto;


import com.muybaby.food.bean.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private User user;
    private String verificationCode;
    
    // getters/setters
}