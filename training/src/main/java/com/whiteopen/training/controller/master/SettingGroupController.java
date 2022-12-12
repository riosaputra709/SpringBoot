package com.whiteopen.training.controller.master;

import com.whiteopen.training.common.DownloadResponse;
import com.whiteopen.training.common.GeneralResponse;
import com.whiteopen.training.common.UploadResponse;
import com.whiteopen.training.module.settingGroup.entity.SettingGroup;
import com.whiteopen.training.module.settingGroup.service.SettingGroupService;
import com.whiteopen.training.module.settingGroup.vo.SettingGroupAddVo;
import com.whiteopen.training.module.settingGroup.vo.SettingGroupDeleteVo;
import com.whiteopen.training.module.settingGroup.vo.SettingGroupListDeleteVo;
import com.whiteopen.training.module.settingGroup.vo.SettingGroupSearchVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("setting-group")
public class SettingGroupController {
    @Autowired
    private SettingGroupService settingGroupService;

    private static final String statusSuccess = "success";
    private static final String statusError = "error";
    private static final String messageSuccess = "Processed Successfully";

    private static int countData = 0;

    @GetMapping("hello-world")
    @ResponseBody
    public String methodHelloWorld(){
        return "Hello World!!!!!!!!!!!";
    }

    @PostMapping("searchSettingGroup")
    @ResponseBody
    public GeneralResponse<List<SettingGroup>> searchSettingGroup(@RequestBody SettingGroupSearchVo request){
        try {
            List<SettingGroup> data = settingGroupService.searchSettingGroup(request);
            countData = settingGroupService.searchCountSettingGroup(request);

            return new GeneralResponse<>(statusSuccess, messageSuccess, countData, data);

        }catch (Exception e){
            return new GeneralResponse<>(statusError,e.getMessage());
        }
    }

    @PostMapping("insertSettingGroup")
    @ResponseBody
    public GeneralResponse<SettingGroup> addSettingGroup(@RequestBody SettingGroupAddVo request) {
        try{

            if (StringUtils.isEmpty(request.getGroupCd())){
                throw new Exception ("Setting Group Code should not be empty");
            }

            if (StringUtils.isEmpty(request.getGroupName())){
                throw new Exception ("Setting Group Name should not be empty");
            }
            SettingGroup data = settingGroupService.createSetingGroup(request);

            return new GeneralResponse<>(statusSuccess, messageSuccess, 1, data);

        } catch (Exception e) {
            return new GeneralResponse<>(statusError,e.getMessage());
        }
    }

    @PostMapping("updateSettingGroup")
    @ResponseBody
    public GeneralResponse<SettingGroup> updateSettingGroup(@RequestBody SettingGroupAddVo request) {
        try{

            if (StringUtils.isEmpty(request.getGroupCd())){
                throw new Exception ("Setting Group Code should not be empty");
            }

            if (StringUtils.isEmpty(request.getGroupName())){
                throw new Exception ("Setting Group Name should not be empty");
            }

            SettingGroup data = settingGroupService.updateSetingGroup(request.getGroupCd(), request);

            return new GeneralResponse<>(statusSuccess, messageSuccess, 1, data);

        } catch (Exception e) {
            return new GeneralResponse<>(statusError,e.getMessage());
        }
    }

    @PostMapping("deleteSettingGroup")
    @ResponseBody
    public GeneralResponse<List<String>> deleteSettingGroup(@RequestBody SettingGroupListDeleteVo<List<SettingGroupDeleteVo>> request) {
        try{

            List<String> data = settingGroupService.deleteSetingGroup(request.getListCode());

            return new GeneralResponse<>(statusSuccess, messageSuccess, data.size(), data);

        } catch (Exception e) {
            return new GeneralResponse<>(statusError,e.getMessage());
        }
    }

    @PostMapping("downloadTemplateSettingGroup")
    @ResponseBody
    public GeneralResponse<DownloadResponse> downloadTemplateSettingGroup() {
        DownloadResponse resp = new DownloadResponse();

        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("template/TemplateSettingGroup.xlsx");
            byte[] fileContext = null;

            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String strDate = dateFormat.format(date);
            String namaFile = "Template Setting Group.xlsx";
            fileContext = settingGroupService.downloadTemplate(is);

            resp.setBase64Data(Base64Utils.encodeToString(fileContext));
            resp.setFileName(namaFile);

        } catch (Exception e) {
            return new GeneralResponse<>(statusError,e.getMessage());
        }

        return new GeneralResponse<>(statusSuccess, messageSuccess, countData, resp);
    }

    @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
    @PostMapping("downloadSettingGroup")
    public GeneralResponse<DownloadResponse> downloadSettingGroup(@RequestHeader(required = false) String groupCd, @RequestHeader(required = false) String groupName, @RequestHeader(required = false) String extention) {
        DownloadResponse resp = new DownloadResponse();

        try {
            if(!extention.equals("xlsx") && !extention.equals("xls") && !extention.equals("csv")) {
                throw new Exception("Extention is unuseable");
            }
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("download/DownloadSettingGroup."+extention);
            byte[] fileContext = null;

            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String strDate = dateFormat.format(date);
            String namaFile = "SettingGroup" + "_" + strDate +"."+ extention;

            fileContext = settingGroupService.download(is, groupCd, groupName, extention);

            resp.setBase64Data(Base64Utils.encodeToString(fileContext));
            resp.setFileName(namaFile);

        } catch (Exception e) {
            return new GeneralResponse<>(statusError,e.getMessage());
        }

        return new GeneralResponse<>(statusSuccess, messageSuccess, countData, resp);
    }

    @PostMapping("uploadSettingGroup")
    @ResponseBody
    public GeneralResponse<List<UploadResponse>> uploadCity(@RequestParam(value = "file") MultipartFile dataFile) {
        try {
            DownloadResponse resp = new DownloadResponse();

            if(dataFile.isEmpty()){
                throw new Exception("File is mandatory");
            }

            List<UploadResponse> data = settingGroupService.uploadCity(dataFile);
            return new GeneralResponse<>(statusSuccess, messageSuccess, countData, data);

        }catch (Exception e){
            return new GeneralResponse<>(statusError,e.getMessage());
        }
    }


}


