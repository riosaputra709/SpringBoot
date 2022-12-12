package com.whiteopen.training.module.settingGroup.service;


import com.whiteopen.training.common.UploadResponse;
import com.whiteopen.training.module.settingGroup.entity.SettingGroup;
import com.whiteopen.training.module.settingGroup.vo.SettingGroupAddVo;
import com.whiteopen.training.module.settingGroup.vo.SettingGroupDeleteVo;
import com.whiteopen.training.module.settingGroup.vo.SettingGroupSearchVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

public interface SettingGroupService {
    List<SettingGroup> searchSettingGroup(SettingGroupSearchVo request) throws Exception;

    SettingGroup createSetingGroup(SettingGroupAddVo request);

    SettingGroup updateSetingGroup(String Id, SettingGroupAddVo request) throws Exception;

    List<String> deleteSetingGroup(List<SettingGroupDeleteVo> listCode) throws Exception;

    int searchCountSettingGroup(SettingGroupSearchVo request) throws Exception;

    byte[] downloadTemplate(InputStream is);

    byte[] download(InputStream is, String groupCd, String groupName, String extention);

    List<UploadResponse> uploadCity(MultipartFile dataFile) throws IOException, ParseException;
}
