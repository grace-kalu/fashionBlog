package com.codeWithMerald.fashionBlog.service;

import com.codeWithMerald.fashionBlog.model.Post;
import com.codeWithMerald.fashionBlog.payload.request.PostRequest;
import com.codeWithMerald.fashionBlog.payload.response.ApiResponse;
import com.codeWithMerald.fashionBlog.payload.response.PagedResponse;
import com.codeWithMerald.fashionBlog.payload.response.PostResponse;
import org.springframework.stereotype.Service;

@Service
public interface PostService {

    PostResponse addPost(PostRequest postRequest);

    PostResponse getPost(Integer id);

    PagedResponse<Post> getAllPosts(int page, int size);

    Post getPostByTitle(String title);

   // PagedResponse<Post> getPostsByComments(Integer id, int page, int size);

    ApiResponse updatePost(Integer id, PostRequest newPostRequest);

    ApiResponse deletePost(Integer id);

    Post likeAPost(Integer id, PostRequest newPostRequest);

    Post disLikeAPost(Integer id, PostRequest newPostRequest);

    //PagedResponse<Post> getByTitle(String title, int page, int size);

}
