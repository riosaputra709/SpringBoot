package com.whiteopen.training.module.parameter.model;


import java.util.List;


public class ParameterGroupModel {
	private String createdBy;
	private String createdTime;
	private String updatedBy;
	private String updatedTime;
	private String paramGroupCode;
	private String paramGroupName;
	private String paramName;
	private List<ParameterChildModel> listParameter;
	//private String recordFlag;


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

	public List<ParameterChildModel> getListParameter() {
		return listParameter;
	}

	public void setListParameter(List<ParameterChildModel> listParameter) {
		this.listParameter = listParameter;
	}

	/*public String getRecordFlag() {
		return recordFlag;
	}

	public void setRecordFlag(String recordFlag) {
		this.recordFlag = recordFlag;
	}*/

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
}
