package com.muybaby.food.service.impl;

import com.muybaby.food.bean.Dish;
import com.muybaby.food.bean.Result;
import com.muybaby.food.dto.UserDto;
import com.muybaby.food.mapper.CategoryMapper;
import com.muybaby.food.mapper.DishMapper;
import com.muybaby.food.mapper.UserMapper;
import com.muybaby.food.service.DishService;
import com.muybaby.food.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DishServiceImpl implements DishService {
    @Resource
    private DishMapper dishMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public Result addDish(Dish dish) {
        if (dishMapper.existsByTitle(dish.getTitle())) {
            return Result.error(HttpStatus.BAD_REQUEST, "菜品名称已存在");
        }
        if (dish.getCategoryId() == null) {
            return Result.error(HttpStatus.BAD_REQUEST, "菜品分类不能为空");
        }
        if (dish.getTitle() == null || dish.getTitle().isEmpty()) {
            return Result.error(HttpStatus.BAD_REQUEST, "菜品名称不能为空");
        }
        if (!categoryMapper.selectIdIfExists(dish.getCategoryId())) {
            return Result.error(HttpStatus.BAD_REQUEST, "菜品分类不存在");
        }
        if (!userMapper.existsById(Math.toIntExact(dish.getUserId()))){
            return Result.error(HttpStatus.BAD_REQUEST, "用户不存在");
        }

        int affectedRows = dishMapper.insertDish(dish);
        return affectedRows > 0 ?
                Result.success("添加成功") :
                Result.error(HttpStatus.BAD_REQUEST, "添加失败");
    }

    @Override
    public Result deleteDish(Long id) {
        int affectedRows = dishMapper.deleteById(id);
        return affectedRows > 0 ?
                Result.success("删除成功") :
                Result.error(HttpStatus.BAD_REQUEST, "菜品不存在");
    }

    @Override
    public Result updateDish(Dish dish) {
        if (dishMapper.existsByTitleAndNotId(dish)) {
            return Result.error(HttpStatus.BAD_REQUEST, "菜品名称已存在");
        }
        int affectedRows = dishMapper.updateDish(dish);
        return affectedRows > 0 ?
                Result.success("更新成功") :
                Result.error(HttpStatus.BAD_REQUEST, "菜品不存在");
    }

    @Override
    public Result<List<Dish>> getAllDishes() {
        List<Dish> dishes = dishMapper.selectAll();
        return Result.success(dishes);
    }

    @Override
    public Result<Dish> getDishById(Long dishId) {
        // 先更新点击量
        int rowsUpdated = dishMapper.incrementClicks(dishId);
        if (rowsUpdated == 0) {
            return Result.error(HttpStatus.NOT_FOUND, "菜品不存在");
        }
        // 再查询最新数据
        Dish dish = dishMapper.selectById(dishId);
        return Result.success(dish);
    }

    @Override
    public Page<Dish> getDishSByPage(Pageable pageable, String title, Integer categoryId) {

        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();
        List<Dish> users = dishMapper.selectByPage(offset, limit, categoryId, title);
        long total = dishMapper.count();
        return new PageImpl<>(users, pageable, total);
    }

    @Override
    public Result<List<Dish>> getHotDishes() {
        List<Dish> hotDishes = dishMapper.getHotDishes();
        if (hotDishes.isEmpty()) {
            return Result.error(HttpStatus.NOT_FOUND, "没有热门菜品");
        }
        return Result.success(hotDishes);
    }
}