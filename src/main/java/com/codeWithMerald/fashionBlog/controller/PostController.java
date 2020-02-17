package com.codeWithMerald.fashionBlog.controller;

import com.codeWithMerald.fashionBlog.model.Post;
import com.codeWithMerald.fashionBlog.payload.request.PostRequest;
import com.codeWithMerald.fashionBlog.payload.response.ApiResponse;
import com.codeWithMerald.fashionBlog.payload.response.PagedResponse;
import com.codeWithMerald.fashionBlog.payload.response.PostResponse;
import com.codeWithMerald.fashionBlog.service.PostService;
import com.codeWithMerald.fashionBlog.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponse> addPost(@Valid @RequestBody PostRequest postRequest) {
        PostResponse postResponse = postService.addPost(postRequest);

        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }

    //@GetMapping("/{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PostResponse> getPost(@PathVariable(name = "id") Integer id) {
        PostResponse post = postService.getPost(id);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Post>> getAllPosts(
            @RequestParam(value = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        PagedResponse<Post> response = postService.getAllPosts(page, size);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @GetMapping("/comment/{id}")
//    public ResponseEntity<PagedResponse<Post>> getPostsByComment(
//            @RequestParam(value = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
//            @RequestParam(value = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
//            @PathVariable(name = "id") Integer id) {
//        PagedResponse<Post> response = postService.getPostsByComments(id, page, size);
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @GetMapping()
    @RequestMapping(value = "/title/{title}", method = RequestMethod.GET)
    public ResponseEntity<Post> getPostByTitle(@PathVariable(name = "title") String title) {
        Post post = postService.getPostByTitle(title);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePost(@PathVariable(name = "id") Integer id,
                                           @Valid @RequestBody PostRequest newPostRequest) {
        ApiResponse post = postService.updatePost(id, newPostRequest);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable(name = "id") Integer id) {
        ApiResponse apiResponse = postService.deletePost(id);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//    @GetMapping("/search")
//    public ResponseEntity<PagedResponse<Post>> getAllByQuery(
//            @RequestParam(value = "title", required = false) String title,
//            @RequestParam(value = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
//            @RequestParam(value = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
//
//        PagedResponse<Post> response = postService.getByTitle(title, page, size);
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

}
