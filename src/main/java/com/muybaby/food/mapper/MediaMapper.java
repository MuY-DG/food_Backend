package com.muybaby.food.mapper;


import com.muybaby.food.bean.Media;
import com.muybaby.food.bean.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author MuYbaby
 * @create 2025/3/12
 */


@Mapper
public interface MediaMapper {
    int deleteMediaBydishId(Long dishId);
}
