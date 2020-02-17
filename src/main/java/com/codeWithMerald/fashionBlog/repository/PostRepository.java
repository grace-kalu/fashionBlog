package com.codeWithMerald.fashionBlog.repository;

import com.codeWithMerald.fashionBlog.model.Comment;
import com.codeWithMerald.fashionBlog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostsByTitle(String title);

//    @Query("select post from Post post where " +
//            "(?1 is null or upper(Post.title) like concat('%', upper(?1), '%')) ")
//    Page<Post> getByQuery(String title, final Pageable pageable);
   // Page<Post> findByComment(List<Comment> comment, Pageable pageable);
}

