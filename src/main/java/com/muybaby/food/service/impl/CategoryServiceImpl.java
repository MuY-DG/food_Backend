package com.muybaby.food.service.impl;

import com.muybaby.food.bean.Category;
import com.muybaby.food.bean.Result;
import com.muybaby.food.mapper.CategoryMapper;
import com.muybaby.food.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// Import cache annotations if caching is implemented
// import org.springframework.cache.annotation.CacheEvict;
// import org.springframework.cache.annotation.Cacheable;

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
    @Transactional // Default propagation is REQUIRED
    // @CacheEvict(value = "categories", allEntries = true) // Example cache eviction
    public Result addCategory(Category category) {
        // Consider adding more validation here (e.g., name length)
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            return Result.error(HttpStatus.BAD_REQUEST, "分类名称不能为空");
        }
        
        // Use optimized existence check
        if (categoryMapper.existsByName(category.getName())) {
            return Result.error(HttpStatus.CONFLICT, "分类名称已存在"); // Changed to CONFLICT
        }

        int affectedRows = categoryMapper.insertCategory(category);
        return affectedRows > 0
                ? Result.success("添加成功", category) // Return the created category with ID
                : Result.error(HttpStatus.INTERNAL_SERVER_ERROR, "添加失败，请稍后重试"); // Changed to INTERNAL_SERVER_ERROR
    }

    @Override
    @Transactional
    // @CacheEvict(value = "categories", allEntries = true) // Example cache eviction
    public Result deleteCategory(Integer categoryId) {
        // Optional: Check if category exists before deleting if needed for specific logic
        // Category existingCategory = categoryMapper.selectById(categoryId);
        // if (existingCategory == null) {
        //     return Result.error(HttpStatus.NOT_FOUND, "分类不存在");
        // }
        
        // Optional: Check if category is associated with any dishes before deleting
        // boolean associated = dishMapper.existsByCategoryId(categoryId); // Requires adding this method to DishMapper
        // if (associated) {
        //     return Result.error(HttpStatus.BAD_REQUEST, "无法删除，该分类下有关联的菜品");
        // }
        
        int affectedRows = categoryMapper.deleteById(categoryId);
        return affectedRows > 0
                ? Result.success("删除成功")
                : Result.error(HttpStatus.NOT_FOUND, "分类不存在或删除失败"); // Corrected status to NOT_FOUND
    }

    @Override
    @Transactional
    // @CacheEvict(value = "categories", allEntries = true) // Example cache eviction
    public Result updateCategory(Category category) {
        // Validate required fields
        if (category.getCategoryId() == null) {
             return Result.error(HttpStatus.BAD_REQUEST, "分类ID不能为空");
        }
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            return Result.error(HttpStatus.BAD_REQUEST, "分类名称不能为空");
        }

        // Use optimized existence check (excluding self)
        if (categoryMapper.existsByNameAndNotId(category)) {
            return Result.error(HttpStatus.CONFLICT, "分类名称已存在"); // Changed to CONFLICT
        }

        int affectedRows = categoryMapper.updateCategory(category);
        return affectedRows > 0
                ? Result.success("更新成功", category) // Return the updated category
                : Result.error(HttpStatus.NOT_FOUND, "分类不存在或更新失败"); // Corrected status to NOT_FOUND
    }

    @Override
    @Transactional(readOnly = true) // Mark read-only methods
    // @Cacheable("categories") // Example caching
    public Result<List<Category>> getAllCategories() {
        List<Category> categories = categoryMapper.selectAll();
        return Result.success(categories);
    }

    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "categories", key = "#id") // Example caching by ID
    public Result<Category> getCategoryById(Integer id) {
        Category category = categoryMapper.selectById(id);
        return category != null
                ? Result.success(category)
                : Result.error(HttpStatus.NOT_FOUND, "分类不存在");
    }
}