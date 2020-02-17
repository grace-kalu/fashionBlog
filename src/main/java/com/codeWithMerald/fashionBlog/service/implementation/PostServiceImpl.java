package com.codeWithMerald.fashionBlog.service.implementation;

import com.codeWithMerald.fashionBlog.exception.ResourceNotFoundException;
import com.codeWithMerald.fashionBlog.model.Comment;
import com.codeWithMerald.fashionBlog.model.Post;
import com.codeWithMerald.fashionBlog.payload.request.PostRequest;
import com.codeWithMerald.fashionBlog.payload.response.ApiResponse;
import com.codeWithMerald.fashionBlog.payload.response.PagedResponse;
import com.codeWithMerald.fashionBlog.payload.response.PostResponse;
import com.codeWithMerald.fashionBlog.repository.CommentRepository;
import com.codeWithMerald.fashionBlog.repository.PostRepository;
import com.codeWithMerald.fashionBlog.service.PostService;
import com.codeWithMerald.fashionBlog.utils.AppUtils;
import com.codeWithMerald.fashionBlog.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.codeWithMerald.fashionBlog.utils.AppConstants.*;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public PostResponse addPost(PostRequest postRequest) {
        List<Comment> comments = new ArrayList<Comment>(postRequest.getComments().size());

        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());

        Post newPost = postRepository.save(post);

        PostResponse postResponse = new PostResponse();
        postResponse.setTitle(newPost.getTitle());
        postResponse.setContent(newPost.getContent());

        return postResponse;
    }


    @Override
    public PostResponse getPost(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(POST, ID, id));
        PostResponse postResponse = new PostResponse();
        postResponse.setTitle(post.getTitle());
        postResponse.setContent(post.getContent());
        return postResponse;
    }


    @Override
    public PagedResponse<Post> getAllPosts(int page, int size) {
        ServiceUtils.validatePageNumberAndSize(page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, CREATED_AT);

        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> postList = posts.getNumberOfElements() == 0 ? Collections.emptyList() : posts.getContent();

        return new PagedResponse<>(postList, posts.getNumber(), posts.getSize(),
                posts.getTotalElements(), posts.getTotalPages(), posts.isLast());
    }


    @Override
    public Post getPostByTitle(String title) {
        return postRepository.findPostsByTitle(title);
    }

//    @Override
//    public PagedResponse<Post> getPostsByComments(Integer id, int page, int size) {
//        AppUtils.validatePageNumberAndSize(page, size);
//
//        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(COMMENT, ID, id));
//
//        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, CREATED_AT);
//
//        Page<Post> posts = postRepository.findByComment(Collections.singletonList(comment), pageable);
//
//        List<Post> postList = posts.getNumberOfElements() == 0 ? Collections.emptyList() : posts.getContent();
//
//        return new PagedResponse<>(postList, posts.getNumber(), posts.getSize(), posts.getTotalElements(),
//                posts.getTotalPages(), posts.isLast());
//    }


    @Override
    public ApiResponse updatePost(Integer id, PostRequest newPostRequest) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(POST, ID, id));
        post.setTitle(newPostRequest.getTitle());
        post.setContent(newPostRequest.getContent());
        postRepository.save(post);

        return new ApiResponse(Boolean.TRUE, "You Successfully Updated post");

    }

    @Override
    public ApiResponse deletePost(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(POST, ID, id));
        postRepository.deleteById(id);
        return new ApiResponse(Boolean.TRUE, "You successfully deleted post");
    }

    @Override
    public Post likeAPost(Integer id, PostRequest newPostRequest) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(POST, ID, id));
        post.setLikes(post.getLikes() + 1);

        return post;
    }

    @Override
    public Post disLikeAPost(Integer id, PostRequest newPostRequest) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(POST, ID, id));
        post.setUnlikes(post.getUnlikes() + 1);

        return post;
    }

//    @Override
//    public PagedResponse<Post> getByTitle(String title, int page, int size) {
//        ServiceUtils.validatePageNumberAndSize(page, size);
//
//        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, CREATED_AT);
//
//        Page<Post> posts = postRepository.getByQuery(title, pageable);
//
//        List<Post> postList = posts.getNumberOfElements() == 0 ? Collections.emptyList() : posts.getContent();
//
//        return new PagedResponse<>(postList, posts.getNumber(), posts.getSize(),
//                posts.getTotalElements(), posts.getTotalPages(), posts.isLast());
//    }


}

