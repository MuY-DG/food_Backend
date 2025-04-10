package com.muybaby.food.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Long commentId;
    private Long userId;
    private Long dishId;
    private String content;
    private String createdAt;
}