package com.haider.DocMindAi.mapper;

import com.haider.DocMindAi.dtos.request.UserRequest;
import com.haider.DocMindAi.dtos.response.UserResponse;
import com.haider.DocMindAi.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toUserEntity(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRequest.getUsername());
        userEntity.setPassword(userRequest.getPassword());
        userEntity.setActive(true);
        return userEntity;
    }
    public UserResponse toUserResponse(UserEntity userEntity) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userEntity.getId());
        userResponse.setUsername(userEntity.getUsername());
        return userResponse;
    }
}
