package com.haider.DocMindAi.service;

import com.haider.DocMindAi.dtos.request.UserRequest;
import com.haider.DocMindAi.dtos.response.UserResponse;
import com.haider.DocMindAi.entity.UserEntity;
import com.haider.DocMindAi.mapper.UserMapper;
import com.haider.DocMindAi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMapper userMapper;

    public UserResponse addUser(UserRequest request) {
        UserEntity userEntity = userMapper.toUserEntity(request);
        userEntity.setActive(true);
        UserEntity saved = userRepo.save(userEntity);
        return userMapper.toUserResponse(saved);
    }

    public UserResponse getById(Long id) {
        UserEntity userEntity = userRepo.findById(id).get();
        return userMapper.toUserResponse(userEntity);
    }
}
