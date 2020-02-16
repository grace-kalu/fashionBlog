package com.codeWithMerald.fashionBlog.payload.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PagedResponse<T> {
    private List<T> body;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public PagedResponse(){

    }

    public PagedResponse(List<T> body, int page, int size, long totalElements, int totalPages, boolean last) {
        setBody(body);
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    public List<T> getBody() {
        return body == null ? null : new ArrayList<>(body);
    }

    public final void setBody(List<T> body) {
        if (body == null) {
            this.body = null;
        } else {
            this.body = Collections.unmodifiableList(body);
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
