package com.whiteopen.training.common;

public class PageData<T> {
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalDataInPage;
    private Long totalData;
    private Integer totalPages;
    private T listData;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getTotalDataInPage() {
        return totalDataInPage;
    }
    public void setTotalDataInPage(Integer totalDataInPage) {
        this.totalDataInPage = totalDataInPage;
    }
    public Long getTotalData() {
        return totalData;
    }
    public void setTotalData(Long totalData) {
        this.totalData = totalData;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
    public T getListData() {
        return listData;
    }
    public void setListData(T listData) {
        this.listData = listData;
    }
}
