package com.muybaby.food.service.impl;

import com.muybaby.food.bean.Comment;
import com.muybaby.food.mapper.CommentMapper;
import com.muybaby.food.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentMapper commentMapper;

    @Override
    @Transactional
    public int insertComment(Comment comment) {
        try {
            return commentMapper.insertComment(comment);
        } catch (Exception e) {
            logger.error("Error inserting comment: {}", comment, e);
            throw new RuntimeException("Failed to insert comment", e);
        }
    }

    @Override
    @Transactional
    public int deleteComment(Long commentId) {
        try {
            return commentMapper.deleteComment(commentId);
        } catch (Exception e) {
            logger.error("Error deleting comment with id: {}", commentId, e);
            throw new RuntimeException("Failed to delete comment", e);
        }
    }

    @Override
    public Comment getCommentById(Long commentId) {
        try {
            return commentMapper.selectCommentById(commentId);
        } catch (Exception e) {
            logger.error("Error retrieving comment with id: {}", commentId, e);
            throw new RuntimeException("Failed to retrieve comment", e);
        }
    }

    @Override
    public List<Comment> getCommentsByDishId(Long dishId) {
        try {
            return commentMapper.selectCommentsByDishId(dishId);
        } catch (Exception e) {
            logger.error("Error retrieving comments for dish id: {}", dishId, e);
            throw new RuntimeException("Failed to retrieve comments", e);
        }
    }
}