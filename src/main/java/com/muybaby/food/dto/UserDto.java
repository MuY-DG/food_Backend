package com.muybaby.food.dto;

import lombok.Builder;
import lombok.Data;


/**
 * @author MuYbaby
 * @create 2025/3/6
 */

@Data
@Builder
public class UserDto {
    private Integer userId;
    private String username;
    private String email;
    private String phone;
    private String role;
//    @JsonSerialize(using = LocalDateTimeToSecondsSerializer.class)
    private String createdAt;  // 直接存储秒级时间戳
//    @JsonSerialize(using = LocalDateTimeToSecondsSerializer.class)
    private String updatedAt;

    // 添加默认构造函数
    public UserDto() {
    }

    public UserDto(Integer userId, String username,  String email, String phone, String role, String createdAt, String updatedAt) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}