package com.codeWithMerald.fashionBlog.payload.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostResponse {
    private String title;
    private String content;
    private List<String> comments;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getComments() {

        return comments == null ? null : new ArrayList<>(comments);
    }
    public void setTags(List<String> comments) {

        if (comments == null) {
            this.comments = null;
        } else {
            this.comments = Collections.unmodifiableList(comments);
        }
    }
}
