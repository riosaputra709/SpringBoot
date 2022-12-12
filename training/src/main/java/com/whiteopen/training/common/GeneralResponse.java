package com.whiteopen.training.common;

public class GeneralResponse<T> {
    private String status;
    private String message;
    private  int countData;
    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCountData() {
        return countData;
    }

    public void setCountData(int countData) {
        this.countData = countData;
    }

    public GeneralResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public GeneralResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public GeneralResponse(String status, String message, int countData, T data) {
        this.status = status;
        this.message = message;
        this.countData = countData;
        this.data = data;
    }
}
