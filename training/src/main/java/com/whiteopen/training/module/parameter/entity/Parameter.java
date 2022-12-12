package com.whiteopen.training.module.parameter.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "APP_PARAM")
public class Parameter {
	
	@Id
	@Column(name = "PARAM_CODE")
	private String paramCode;
	
	@Column(name = "PARAM_NAME")
	private String paramName;
	
	@Column(name = "PARAM_DESC")
	private String paramDesc;
	
	@Column(name = "LINE_NO")
	private int lineNo;

	@Column(name = "record_flag")
	@NotNull
	private String recordFlag;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonBackReference
	@JoinColumn(name = "PARAM_GROUP_CODE")
	private ParameterGroup parameterGroup;

	//base entity
	@Column(name = "created_by")
	@NotNull
	private String createdBy;

	@Column(name = "created_time")
	@NotNull
	private Date createdTime;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "updated_time")
	private Date updatedTime;

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamDesc() {
		return paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	public int getLineNo() {
		return lineNo;
	}

	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}

	public String getRecordFlag() {
		return recordFlag;
	}

	public void setRecordFlag(String recordFlag) {
		this.recordFlag = recordFlag;
	}

	public ParameterGroup getParameterGroup() {
		return parameterGroup;
	}

	public void setParameterGroup(ParameterGroup parameterGroup) {
		this.parameterGroup = parameterGroup;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
}
