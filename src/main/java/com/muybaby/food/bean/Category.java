package com.muybaby.food.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.muybaby.food.dto.LocalDateTimeToSecondsSerializer;
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
//类别
public class Category {
        private Integer categoryId;
        private String name;
//        @JsonSerialize(using = LocalDateTimeToSecondsSerializer.class)
        private String createdAt;
//        @JsonSerialize(using = LocalDateTimeToSecondsSerializer.class)
        private String updatedAt;



}
