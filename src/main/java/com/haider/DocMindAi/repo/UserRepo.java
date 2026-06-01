package com.haider.DocMindAi.repo;

import com.haider.DocMindAi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity,Long> {
}
