package com.whiteopen.training.module.parameter.vo;

import java.util.List;

public class ParameterAddVo {
    private String paramGroupCode;
    private String paramGroupName;
    private String paramName;
    private List<ParameterChildAddVo> listParameter;

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

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public List<ParameterChildAddVo> getListParameter() {
        return listParameter;
    }

    public void setListParameter(List<ParameterChildAddVo> listParameter) {
        this.listParameter = listParameter;
    }
}
