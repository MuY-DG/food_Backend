package com.muybaby.food.mapper;

import com.muybaby.food.bean.User;

import com.muybaby.food.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author MuYbaby
 * @create 2025/3/7
 */
@Mapper
public interface UserMapper {
    User selectUser(Integer user_id);
    User selectUserByName(String username);
    int insertUser(User user);
    User selectUserByEmail(String email);
    List<UserDto> selectByPage(int offset, int limit);
    long count();
//    int deleteById(Integer userId);
//    int updateUserById(User user);
    boolean existsById(Integer userId);
}
