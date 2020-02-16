package com.codeWithMerald.fashionBlog.repository;

import com.codeWithMerald.fashionBlog.model.Comment;
import com.codeWithMerald.fashionBlog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface
PostRepository extends JpaRepository<Post, Integer> {
    Post findPostsByTitle(String title);
    Page<Post> findPostsByComments(List<Comment> comment, Pageable pageable);
}

