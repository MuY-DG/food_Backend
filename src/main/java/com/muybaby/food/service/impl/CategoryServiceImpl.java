package com.muybaby.food.service.impl;

import com.muybaby.food.bean.Category;
import com.muybaby.food.bean.Result;
import com.muybaby.food.mapper.CategoryMapper;
import com.muybaby.food.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MuYbaby
 * @create 2025/3/12
 */

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Result addCategory(Category category) {
        // 检查名称是否重复
        if (categoryMapper.existsByName(category.getName())) {
            return Result.error(HttpStatus.BAD_REQUEST, "分类名称已存在");
        }

        int affectedRows = categoryMapper.insertCategory(category);
        return affectedRows > 0
                ? Result.success("添加成功")
                : Result.error(HttpStatus.BAD_REQUEST, "添加失败");
    }

    @Override
    public Result deleteCategory(Integer categoryId) {
        int affectedRows = categoryMapper.deleteById(categoryId);
        return affectedRows > 0
                ? Result.success("删除成功")
                : Result.error(HttpStatus.BAD_REQUEST, "分类不存在");
    }

    @Override
    public Result updateCategory(Category category) {
        // 检查名称是否重复（排除自己）
        if (categoryMapper.existsByNameAndNotId(category)) {
            return Result.error(HttpStatus.BAD_REQUEST, "分类名称已存在");
        }

        int affectedRows = categoryMapper.updateCategory(category);
        return affectedRows > 0
                ? Result.success("更新成功")
                : Result.error(HttpStatus.BAD_REQUEST, "分类不存在");
    }

    @Override
    public Result<List<Category>> getAllCategories() {
        List<Category> categories = categoryMapper.selectAll();
        return Result.success(categories);
    }

    @Override
    public Result<Category> getCategoryById(Integer id) {
        Category category = categoryMapper.selectById(id);
        return category != null
                ? Result.success(category)
                : Result.error(HttpStatus.NOT_FOUND, "分类不存在");
    }
}