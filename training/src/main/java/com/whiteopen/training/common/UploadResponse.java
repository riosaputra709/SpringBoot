package com.whiteopen.training.common;

public class UploadResponse {
    private String message;
    private int row;
    private String settingGroupCd;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getSettingGroupCd() {
        return settingGroupCd;
    }

    public void setSettingGroupCd(String settingGroupCd) {
        this.settingGroupCd = settingGroupCd;
    }
}
