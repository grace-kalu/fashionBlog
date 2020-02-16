package com.codeWithMerald.fashionBlog.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentRequest {
    @NotBlank
    @Size(min = 10, message = "Comment content must be a minimum of 10 characters")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
