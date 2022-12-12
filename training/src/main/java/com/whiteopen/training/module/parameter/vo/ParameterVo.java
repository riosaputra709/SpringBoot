package com.whiteopen.training.module.parameter.vo;

public class ParameterVo {
    private String param;
    private String paramGroupCode;
    private String paramGroupName;
    private int pageNumber;
    private int pageSize;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getParamGroupCode() {
        return paramGroupCode;
    }

    public void setParamGroupCode(String paramGroupCode) {
        this.paramGroupCode = paramGroupCode;
    }

    public String getParamGroupName() {
        return paramGroupName;
    }

    public void setParamGroupName(String paramGroupName) {
        this.paramGroupName = paramGroupName;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
