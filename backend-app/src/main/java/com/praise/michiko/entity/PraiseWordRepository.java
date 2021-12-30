package com.praise.michiko.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PraiseWordRepository extends JpaRepository<PraiseWord, Long> {
}
