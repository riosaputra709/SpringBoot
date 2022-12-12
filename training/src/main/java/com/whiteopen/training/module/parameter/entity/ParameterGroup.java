package com.whiteopen.training.module.parameter.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "app_param_group")
public class ParameterGroup {

    @Id
    @Column(name = "param_group_code")
    @NotNull
    private String paramGroupCode;

    @Column(name = "param_group_name")
    @NotNull
    private String paramGroupName;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parameterGroup", orphanRemoval = false )
    @JsonManagedReference
    private List<Parameter> param;

    @Column(name = "record_flag")
    @NotNull
    private String recordFlag;


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

    public List<Parameter> getParam() {
        return param;
    }

    public void setParam(List<Parameter> param) {
        this.param = param;
    }

    public String getRecordFlag() {
        return recordFlag;
    }

    public void setRecordFlag(String recordFlag) {
        this.recordFlag = recordFlag;
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
