package com.muybaby.food.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
//类别
public class CategoryBasic {
        private Integer categoryId;
        private String name;
}
