package com.muybaby.food.mapper;

import com.muybaby.food.bean.Dish;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MuYbaby
 * @create 2025/3/13
 */
public interface DishMapper {
    int insertDish(Dish dish);
    int deleteById(Long dishId);
    int updateDish(Dish dish);
    List<Dish> selectAll();
    Dish selectById(Long dishId);
    boolean existsByTitle(String title);
    boolean existsByTitleAndNotId(Dish dish);
    List<Dish> selectByPage(int offset, int limit, @Param("categoryId") Integer categoryId, @Param("title") String title);
    long count();
    List<Dish> getHotDishes();
    int incrementClicks(Long dishId); // 新增方法
}