package com.muybaby.food.service.impl;


import com.muybaby.food.bean.Result;
import com.muybaby.food.mapper.MediaMapper;
import com.muybaby.food.service.MediaService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class MediaServiceImpl implements MediaService {
    @Resource
    private MediaMapper MediaMapper;


    @Override
    public Result deleteMedia(Long dishId) {
        int i = MediaMapper.deleteMediaBydishId(dishId);
        return i > 0 ?
                Result.success("删除成功") :
                Result.error(HttpStatus.BAD_REQUEST, "删除失败");
    }
}