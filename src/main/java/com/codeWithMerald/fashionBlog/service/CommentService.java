package com.codeWithMerald.fashionBlog.service;

import com.codeWithMerald.fashionBlog.model.Comment;
import com.codeWithMerald.fashionBlog.payload.request.CommentRequest;
import com.codeWithMerald.fashionBlog.payload.response.ApiResponse;
import com.codeWithMerald.fashionBlog.payload.response.PagedResponse;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {

    Comment addComment(CommentRequest commentRequest, Integer postId);

    Comment getComment(Integer postId, Integer id);

    Comment updateComment(Integer postId, Integer id, CommentRequest commentRequest);

    PagedResponse<Comment> getAllComments(Integer postId, int page, int size);

    ApiResponse deleteComment(Integer postId, Integer id);

}




