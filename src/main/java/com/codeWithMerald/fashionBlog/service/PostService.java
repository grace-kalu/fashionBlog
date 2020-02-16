package com.codeWithMerald.fashionBlog.service;

import com.codeWithMerald.fashionBlog.model.Post;
import org.springframework.stereotype.Service;

@Service
public interface PostService {

    PostResponse addPost(PostRequest postRequest);

    Post getPost(Integer id);

    PagedResponse<Post> getAllPosts(int page, int size);

    Post getPostByTitle(String title);

    PagedResponse<Post> getPostsByComments(Integer id, int page, int size);

    Post updatePost(Integer id, PostRequest newPostRequest);

    ApiResponse deletePost(Integer id);

}
