package com.ots.springbooks.repositories;

import com.ots.springbooks.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {}
