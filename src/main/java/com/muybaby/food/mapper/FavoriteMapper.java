package com.muybaby.food.mapper;

import com.muybaby.food.bean.Favorite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavoriteMapper {

    int insertFavorite(Favorite favorite);

    int deleteFavorite(Long favoriteId);

    Favorite selectFavoriteById(Long favoriteId);

    List<Favorite> selectFavoritesByUserId(Long userId);

    Favorite selectFavoriteByUserIdAndDishId(@Param("userId") Long userId, @Param("dishId") Long dishId);
}