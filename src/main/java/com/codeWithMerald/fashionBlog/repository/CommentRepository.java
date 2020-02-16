package com.codeWithMerald.fashionBlog.repository;

import com.codeWithMerald.fashionBlog.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Page<Comment> findByPostId(Integer postId, Pageable pageable);
}
