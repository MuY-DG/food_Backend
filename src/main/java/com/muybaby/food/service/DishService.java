package com.muybaby.food.service;

import com.muybaby.food.bean.Dish;
import com.muybaby.food.bean.Result;
import com.muybaby.food.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DishService {
    Result addDish(Dish dish);
    Result deleteDish(Long id);
    Result updateDish(Dish dish);
    Result<List<Dish>> getAllDishes();
    Result<Dish> getDishById(Long dishId);
    Page<Dish> getDishSByPage(Pageable pageable,String title,Integer categoryId);
    Result<List<Dish>> getHotDishes();
}