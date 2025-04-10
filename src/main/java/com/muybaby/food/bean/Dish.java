package com.muybaby.food.bean;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.muybaby.food.dto.CategoryBasic;
import com.muybaby.food.dto.UserBasic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author MuYbaby
 * @create 2025/3/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//菜品
public class Dish {
    private Long dishId; // 菜品ID
    private String title; // 菜品标题
    private Integer categoryId; // 分类ID
    private Long userId; // 用户ID
//    @JsonRawValue
    private String markdownContent; // 菜品内容（Markdown格式）
    private String coverImage; // 封面图片
    // @JsonSerialize(using = LocalDateTimeToSecondsSerializer.class)
    private String createdAt; // 创建时间（秒级时间戳）
    // @JsonSerialize(using = LocalDateTimeToSecondsSerializer.class)
    private String updatedAt; // 更新时间（秒级时间戳）
    private Long clicks;
    //关联对象
    private CategoryBasic category;
    private UserBasic user;
}