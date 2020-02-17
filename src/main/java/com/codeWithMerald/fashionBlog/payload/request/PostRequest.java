package com.codeWithMerald.fashionBlog.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostRequest {
    @NotBlank
    @Size(min = 10)
    private String title;

    @NotBlank
    @Size(min = 50)
    private String content;

    private List<String> comments;

    private Integer likes;
    private Integer unlikes;

    public PostRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String body) {
        this.content = body;
    }

    public List<String> getComments() {

        return comments == null ? Collections.emptyList() : new ArrayList<>(comments);
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getUnlikes() {
        return unlikes;
    }

    public void setUnlikes(Integer unlikes) {
        this.unlikes = unlikes;
    }

    public void setComments(List<String> comments) {

        if (comments == null) {
            this.comments = null;
        } else {
            this.comments = Collections.unmodifiableList(comments);
        }
    }

}
