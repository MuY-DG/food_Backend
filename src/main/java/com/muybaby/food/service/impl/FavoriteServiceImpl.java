package com.muybaby.food.service.impl;

import com.muybaby.food.bean.Favorite;
import com.muybaby.food.mapper.FavoriteMapper;
import com.muybaby.food.service.FavoriteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private static final Logger logger = LoggerFactory.getLogger(FavoriteServiceImpl.class);

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    public int insertFavorite(Favorite favorite) {
        try {
            return favoriteMapper.insertFavorite(favorite);
        } catch (Exception e) {
            logger.error("Error inserting favorite: {}", favorite, e);
            throw new RuntimeException("Failed to insert favorite", e);
        }
    }

    @Override
    public int deleteFavorite(Long userId, Long dishId) {
        try {
            return favoriteMapper.deleteFavorite(userId, dishId);
        } catch (Exception e) {
            logger.error("Error deleting favorite for user {} and dish {}: {}", userId, dishId, e.getMessage());
            throw new RuntimeException("Failed to delete favorite", e);
       }
    }

    @Override
    public Favorite getFavoriteById(Long favoriteId) {
        try {
            return favoriteMapper.selectFavoriteById(favoriteId);
        } catch (Exception e) {
            logger.error("Error retrieving favorite with id: {}", favoriteId, e);
            throw new RuntimeException("Failed to retrieve favorite", e);
        }
    }

    @Override
    public List<Favorite> getFavoritesByUserId(Long userId) {
        try {
            return favoriteMapper.selectFavoritesByUserId(userId);
        } catch (Exception e) {
            logger.error("Error retrieving favorites for user id: {}", userId, e);
            throw new RuntimeException("Failed to retrieve favorites", e);
        }
    }

    @Override
    public boolean isFavorite(Long userId, Long dishId) {
        try {
            return favoriteMapper.selectFavoriteByUserIdAndDishId(userId, dishId) != null;
        } catch (Exception e) {
            logger.error("Error checking if dish {} is favorited by user {}: {}", dishId, userId, e.getMessage());
            throw new RuntimeException("Failed to check if dish is favorited", e);
        }
    }
}