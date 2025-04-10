package com.muybaby.food.controller;

import com.muybaby.food.bean.Category;
import com.muybaby.food.bean.Result;
import com.muybaby.food.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author MuYbaby
 * @create 2025/3/12
 */
@RestController
@RequestMapping("/api/admin/category")

public class CategoryController {
    @Resource
    private CategoryService categoryService;

    // 添加分类
    @PostMapping
    public Result addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    // 删除分类
    @DeleteMapping("/{categoryId}")
    public Result deleteCategory(@PathVariable(value = "categoryId") Integer categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

    // 修改分类
    @PutMapping
    public Result updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

    // 查询所有分类
    @GetMapping
    public Result<List<Category>> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // 根据ID查询分类
    @GetMapping("/{id}")
    public Result<Category> getCategoryById(@PathVariable("id") Integer id) {
        return categoryService.getCategoryById(id);
    }
}
