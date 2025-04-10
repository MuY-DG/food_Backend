package com.muybaby.food.service;

import com.muybaby.food.bean.Favorite;
import java.util.List;

public interface FavoriteService {

    void insertFavorite(Favorite favorite);

    void deleteFavorite(Long favoriteId);

    Favorite getFavoriteById(Long favoriteId);

    List<Favorite> getFavoritesByUserId(Long userId);

    boolean isFavorite(Long userId, Long dishId);
}