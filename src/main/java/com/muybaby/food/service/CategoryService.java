package com.muybaby.food.service;

import com.muybaby.food.bean.Category;
import com.muybaby.food.bean.Result;
import java.util.List;


public interface CategoryService {
    Result addCategory(Category category);
    Result deleteCategory(Integer id);
    Result updateCategory(Category category);
    Result<List<Category>> getAllCategories();
    Result<Category> getCategoryById(Integer id);
}
