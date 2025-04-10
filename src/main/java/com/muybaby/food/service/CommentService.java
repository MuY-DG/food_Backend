package com.muybaby.food.service;

import com.muybaby.food.bean.Comment;
import java.util.List;

public interface CommentService {

    int insertComment(Comment comment);

    int deleteComment(Long commentId);

    Comment getCommentById(Long commentId);

    List<Comment> getCommentsByDishId(Long dishId);
}