package com.haider.DocMindAi.repo;

import com.haider.DocMindAi.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepo extends JpaRepository<DocumentEntity,Long> {
}
