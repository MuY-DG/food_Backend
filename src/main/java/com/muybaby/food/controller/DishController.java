package com.muybaby.food.controller;

import com.muybaby.food.bean.Dish;
import com.muybaby.food.bean.Result;
import com.muybaby.food.dto.UserDto;
import com.muybaby.food.service.DishService;
import com.muybaby.food.service.MediaService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/dish")
public class DishController {
    @Resource
    private DishService dishService;
    @Resource
    private MediaService mediaService;

    @PostMapping
    public Result addDish(@RequestBody Dish dish) {

        return dishService.addDish(dish);
    }

    @DeleteMapping("/{dishId}")
    public Result deleteDish(@PathVariable Long dishId) {
        mediaService.deleteMedia(dishId);
        return dishService.deleteDish(dishId);
    }

    @PutMapping
    public Result updateDish(@RequestBody Dish dish) {
        return dishService.updateDish(dish);
    }

    @GetMapping
    public Result<List<Dish>> getAllDishes() {
        return dishService.getAllDishes();
    }
    @GetMapping("/hot")
    public Result<List<Dish>> getHotDishes() {
        return dishService.getHotDishes();
    }

    @GetMapping("/{dishId}")
    public Result<Dish> getDishById(@PathVariable Long dishId) {
        return dishService.getDishById(dishId);
    }

    @GetMapping("/search")
    public Result<Page<Dish>> getDishesByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer categoryId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Dish> userPage = dishService.getDishSByPage(pageable, title, categoryId);
        return Result.success(userPage);
    }

    @Autowired
    private CommentService commentService;

    @PostMapping("/{dishId}/comments")
    public Result addComment(@PathVariable Long dishId, @RequestParam String content) {
        Long userId = ThreadLocalUtil.getCurrentUser().getUserId();
        commentService.insertComment(new Comment(null, userId, dishId, content, LocalDateTime.now().toString()));
        return Result.success("评论已添加");
    }

    @DeleteMapping("/comments/{commentId}")
    public Result removeComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return Result.success("评论已删除");
    }

    @GetMapping("/{dishId}/comments")
    public Result<List<Comment>> getComments(@PathVariable Long dishId) {
        List<Comment> comments = commentService.getCommentsByDishId(dishId);
        return Result.success(comments);
    }
}

