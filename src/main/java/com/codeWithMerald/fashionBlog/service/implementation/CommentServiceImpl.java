package com.codeWithMerald.fashionBlog.service.implementation;

import com.codeWithMerald.fashionBlog.model.Comment;
import com.codeWithMerald.fashionBlog.model.Post;
import com.codeWithMerald.fashionBlog.payload.request.CommentRequest;
import com.codeWithMerald.fashionBlog.payload.response.ApiResponse;
import com.codeWithMerald.fashionBlog.payload.response.PagedResponse;
import com.codeWithMerald.fashionBlog.repository.CommentRepository;
import com.codeWithMerald.fashionBlog.repository.PostRepository;
import com.codeWithMerald.fashionBlog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private static final String THIS_COMMENT = " this comment";

    private static final String YOU_DON_T_HAVE_PERMISSION_TO = "You don't have permission to ";

    private static final String ID_STR = "id";

    private static final String COMMENT_STR = "Comment";

    private static final String POST_STR = "Post";

    private static final String COMMENT_DOES_NOT_BELONG_TO_POST = "Comment does not belong to post";

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    @Override
    public Comment addComment(CommentRequest commentRequest, Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(POST_STR, ID_STR, postId));

        Comment comment = new Comment(commentRequest.getContent());
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    @Override
    public Comment getComment(Integer postId, Integer id) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(POST_STR, ID_STR, postId));
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(COMMENT_STR, ID_STR, id));
        if (comment.getPost().getId().equals(post.getId())) {
            return comment;
        }

        throw new BlogapiException(HttpStatus.BAD_REQUEST, COMMENT_DOES_NOT_BELONG_TO_POST);
    }

    @Override
    public PagedResponse<Comment> getAllComments(Integer postId, int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");

        Page<Comment> comments = commentRepository.findByPostId(postId, pageable);

        return new PagedResponse<Comment>(comments.getContent(), comments.getNumber(), comments.getSize(),
                comments.getTotalElements(), comments.getTotalPages(), comments.isLast());
    }


    @Override
    public Comment updateComment(Integer postId, Integer id, CommentRequest commentRequest) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(POST_STR, ID_STR, postId));
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(COMMENT_STR, ID_STR, id));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogapiException(HttpStatus.BAD_REQUEST, COMMENT_DOES_NOT_BELONG_TO_POST);
        }


        comment.setContent(commentRequest.getContent());
        return commentRepository.save(comment);

    }

    @Override
    public ApiResponse deleteComment(Integer postId, Integer id) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(POST_STR, ID_STR, postId));
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(COMMENT_STR, ID_STR, id));

        if (!comment.getPost().getId().equals(post.getId())) {
            return new ApiResponse(Boolean.FALSE, COMMENT_DOES_NOT_BELONG_TO_POST);
        }

        commentRepository.deleteById(comment.getId());
        return new ApiResponse(Boolean.TRUE, "You successfully deleted comment");

    }
}

