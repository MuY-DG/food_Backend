package com.muybaby.food.service.impl;


import com.muybaby.food.dto.UserDto;
import com.muybaby.food.mapper.UserMapper;
import com.muybaby.food.bean.User;
import com.muybaby.food.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MuYbaby
 * @create 2025/3/7
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User getUser(Integer user_id) {
        return userMapper.selectUser(user_id);
    }

    @Override
    public User getUserByName(String username) {
        return  userMapper.selectUserByName(username);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }

    @Override
    public Page<UserDto> getUsersByPage(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();
        List<UserDto> users = userMapper.selectByPage(offset, limit);
        long total = userMapper.count();
        return new PageImpl<>(users, pageable, total);
    }


}
