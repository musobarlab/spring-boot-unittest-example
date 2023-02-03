package com.wuriyanto.example.application;

public class Meta {

    private int page;

    private int limit;

    private long totalPages;

    private long totalRecords;

    public Meta(int page, int limit, long totalPages, long totalRecords) {
        this.page = page;
        this.limit = limit;
        this.totalPages = totalPages;
        this.totalRecords = totalRecords;
    }

    public Meta(int page, int limit, long totalRecords) {
        this.page = page;
        this.limit = limit;
        this.totalPages = (long) Math.ceil((double) totalRecords / limit);
        this.totalRecords = totalRecords;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }
}
