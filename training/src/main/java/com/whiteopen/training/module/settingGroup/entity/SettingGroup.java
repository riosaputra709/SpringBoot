package com.whiteopen.training.module.settingGroup.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "app_setting_group")
public class SettingGroup {
    @Id
    @Column(name = "setting_group_code")
    @NotNull
    private String settingGroupCode;

    @Column(name = "setting_group_name")
    @NotNull
    private String settingGroupName;

    @Column(name = "setting_group_desc")
    private String settingGroupDesc;

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

    public String getSettingGroupCode() {
        return settingGroupCode;
    }

    public void setSettingGroupCode(String settingGroupCode) {
        this.settingGroupCode = settingGroupCode;
    }

    public String getSettingGroupName() {
        return settingGroupName;
    }

    public void setSettingGroupName(String settingGroupName) {
        this.settingGroupName = settingGroupName;
    }

    public String getSettingGroupDesc() {
        return settingGroupDesc;
    }

    public void setSettingGroupDesc(String settingGroupDesc) {
        this.settingGroupDesc = settingGroupDesc;
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