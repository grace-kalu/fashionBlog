package com.codeWithMerald.fashionBlog.controller;

import com.codeWithMerald.fashionBlog.model.Comment;
import com.codeWithMerald.fashionBlog.payload.request.CommentRequest;
import com.codeWithMerald.fashionBlog.payload.response.ApiResponse;
import com.codeWithMerald.fashionBlog.payload.response.PagedResponse;
import com.codeWithMerald.fashionBlog.service.CommentService;
import com.codeWithMerald.fashionBlog.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

//    @Autowired
//    public CommentController(CommentService commentService) {
//        this.commentService = commentService;
//    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@Valid @RequestBody CommentRequest commentRequest,
                                              @PathVariable(name = "postId") Integer postId) {
        Comment newComment = commentService.addComment(commentRequest, postId);

        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable(name = "postId") Integer postId,
                                              @PathVariable(name = "id") Integer id) {
        Comment comment = commentService.getComment(postId, id);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Comment>> getAllComments(@PathVariable(name = "postId") Integer postId,
                                                                 @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                                                 @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {

        PagedResponse<Comment> allComments = commentService.getAllComments(postId, page, size);

        return new ResponseEntity<>(allComments, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable(name = "postId") Integer postId,
                                                 @PathVariable(name = "id") Integer id, @Valid @RequestBody CommentRequest commentRequest) {

        Comment updatedComment = commentService.updateComment(postId, id, commentRequest);

        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable(name = "postId") Integer postId,
                                                     @PathVariable(name = "id") Integer id) {

        ApiResponse response = commentService.deleteComment(postId, id);

        HttpStatus status = response.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(response, status);
    }

}
