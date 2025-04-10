package com.muybaby.food.mapper;

import com.muybaby.food.bean.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {

    int insertComment(Comment comment);

    int deleteComment(Long commentId);

    Comment selectCommentById(Long commentId);

    List<Comment> selectCommentsByDishId(Long dishId);
}