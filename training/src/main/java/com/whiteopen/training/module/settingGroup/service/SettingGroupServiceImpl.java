package com.whiteopen.training.module.settingGroup.service;

import com.whiteopen.training.common.UploadResponse;
import com.whiteopen.training.controller.download.SettingGroupDownload;
import com.whiteopen.training.controller.upload.SettingGroupUpload;
import com.whiteopen.training.module.settingGroup.entity.SettingGroup;
import com.whiteopen.training.module.settingGroup.repository.SettingGroupRepositoryJPA;
import com.whiteopen.training.module.settingGroup.vo.SettingGroupAddVo;
import com.whiteopen.training.module.settingGroup.vo.SettingGroupDeleteVo;
import com.whiteopen.training.module.settingGroup.vo.SettingGroupSearchVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;


@Service
public class SettingGroupServiceImpl implements SettingGroupService {
    @Autowired
    private SettingGroupRepositoryJPA repositoryJPA;

    @Autowired
    SettingGroupUpload upload;


    @Override
    public List<SettingGroup> searchSettingGroup(SettingGroupSearchVo request) throws Exception{
        int pageNo = request.getPageNumber();
        int pageSize = (request.getPageSize() > 0 ? request.getPageSize() : 10);

        List<SettingGroup> settingGroupPage = repositoryJPA.searchSettingGroup(
                PageRequest.of(pageNo,pageSize), request.getGroupCd(), request.getGroupName());
        return settingGroupPage;
    }

    @Override
    public SettingGroup createSetingGroup(SettingGroupAddVo request) {
        SettingGroup settingGroup = new SettingGroup();
        //user.setUserId(null);
        settingGroup.setSettingGroupCode(request.getGroupCd());
        settingGroup.setSettingGroupName(request.getGroupName());
        settingGroup.setSettingGroupDesc(request.getGroupDesc());
        settingGroup.setRecordFlag("A");
        settingGroup.setCreatedBy("Admin");
        //user.setCreateOn(new Date());
        settingGroup.setCreatedTime(new Timestamp(new Date().getTime()));

        return repositoryJPA.save(settingGroup);
    }

    @Override
    public SettingGroup updateSetingGroup(String Id, SettingGroupAddVo request) throws Exception {

        SettingGroup settingGroupDb = repositoryJPA.findById(Id).orElse(null);

        if (settingGroupDb == null){
            throw new Exception("User Id = [" + Id + "] tidak ditemukan");
        }

        settingGroupDb.setSettingGroupName(request.getGroupName());
        settingGroupDb.setSettingGroupDesc(request.getGroupDesc());

        settingGroupDb.setUpdatedBy("Admin");
        settingGroupDb.setUpdatedTime(new Timestamp(new Date().getTime()));


        return repositoryJPA.save(settingGroupDb);

    }

    @Override
    public List<String> deleteSetingGroup(List<SettingGroupDeleteVo> listCode) throws Exception {

        List<String> listValueSettingGroupCode = new ArrayList<>();
        for(int i = 0; i < listCode.size(); i++)
        {
            listValueSettingGroupCode.add(listCode.get(i).getCode());
        }

        //validasi apakah group code yang dihapus ada di database?
        for(int i = 0; i < listCode.size(); i++)
        {
            String code = listValueSettingGroupCode.get(i);
            String result = repositoryJPA.findSettingGroupCode(code);

            if(result==null || !result.equals(code))
            {
                throw new Exception("ops!!, Failed delete, Id Code [" + code + "] is not exist");
            }
        }

        repositoryJPA.deleteByCode(listValueSettingGroupCode); //proses delete

        List<String> messageResult = new ArrayList<>();
        for(int i = 0; i < listCode.size(); i++)
        {
            messageResult.add("Delete with Setting Group Code [" + listCode.get(i).getCode() + "] Successfully");
        }
        return messageResult;
    }

    @Override
    public int searchCountSettingGroup(SettingGroupSearchVo request) throws Exception{
        int result = 0;
        return result = repositoryJPA.searchCount(request.getGroupCd(), request.getGroupName());
    }

    @Override
    public byte[] downloadTemplate(InputStream is) {
        Workbook wb = null;
        ByteArrayInputStream bais = null;
        ByteArrayOutputStream baos = null;
//		System.out.println("InputStream " + is);

        try {
            wb = new XSSFWorkbook(is);
        } catch (IOException e) {
            // logger.error("Error while trying to read template file", e);
            e.printStackTrace();
            return null;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Closing the output stream
        try {
            baos = new ByteArrayOutputStream();
            wb.write(baos);
        } catch (IOException e) {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e2) {
                    // do nothing
                    e2.printStackTrace();
                }
            }
            // logger.error("Error while trying to write to mem output stream", e);
            return null;
        }

        try {
            bais = new ByteArrayInputStream(baos.toByteArray());
        } catch (Exception e) {
            // logger.error("Error while trying to create stream content", e);
            e.printStackTrace();
        } finally {
            try {
                baos.close();
                bais.close();
            } catch (IOException e) {
                // do nothing
                e.printStackTrace();
            }
        }

        byte[] byteData = bais.readAllBytes();
        return byteData;
    }

    @Override
    public byte[] download(InputStream is, String groupCd, String groupName, String fileName) {
        Workbook wb = null;
        ByteArrayInputStream bais = null;
        ByteArrayOutputStream baos = null;
        SettingGroupDownload downloadExcel = new SettingGroupDownload();
        String csv = "";
//		System.out.println("InputStream " + is);

        try {
            if(fileName.equals("xls")){
                wb = new HSSFWorkbook(is);
            } else if(fileName.equals("xlsx")) {
                wb = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            // logger.error("Error while trying to read template file", e);
            e.printStackTrace();
            return null;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<SettingGroup> data= repositoryJPA.searchDataDownload(groupCd, groupName);
        if(fileName.equals("csv")) {
            csv = downloadExcel.settingGroupDownloadCSV(data);
        } else {
            downloadExcel.settingGroupDownload(wb, data);
        }
        // Closing the output stream
        try {
            baos = new ByteArrayOutputStream();
            if(!fileName.equals("csv")) {
                wb.write(baos);
            }
        } catch (IOException e) {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e2) {
                    // do nothing
                    e2.printStackTrace();
                }
            }
            // logger.error("Error while trying to write to mem output stream", e);
            return null;
        }

        try {
            if(fileName.equals("csv")) {
                byte[] bytes = csv.getBytes();
                bais = new ByteArrayInputStream(bytes);
            }else {
                bais = new ByteArrayInputStream(baos.toByteArray());
            }
        } catch (Exception e) {
            // logger.error("Error while trying to create stream content", e);
            e.printStackTrace();
        } finally {
            try {
                baos.close();
                bais.close();
            } catch (IOException e) {
                // do nothing
                e.printStackTrace();
            }
        }

        byte[] byteData = bais.readAllBytes();
        return byteData;
    }

    @Override
    public List<UploadResponse> uploadCity(MultipartFile file) throws IOException, ParseException {

        List<SettingGroup> settingGroupList = upload.uploadSettingGroup(file.getInputStream());

        List<SettingGroup> settingGroupSave = new ArrayList<>();

        List<UploadResponse> resultResp = new ArrayList<>(10000);
        int a = 1;

        for (int i = 0; i < settingGroupList.size(); i++) {
            UploadResponse response = new UploadResponse();
            if (StringUtils.isEmpty(settingGroupList.get(i).getSettingGroupCode())) {
                response.setMessage("Setting Group Code Is Empty in row " + a);
                response.setRow(a);
                a++;
            }
            else if (StringUtils.isEmpty(settingGroupList.get(i).getSettingGroupName())) {
                response.setMessage("Setting Group Name Is Empty in row " + a);
                response.setRow(a);
                a++;
                continue;
            } else {
                SettingGroup settingGroup = new SettingGroup();
                SettingGroup cekSetGroup = repositoryJPA.findById(settingGroupList.get(i).getSettingGroupCode()).orElse(null);
                if(cekSetGroup != null) {
                    // Update
                    settingGroup = cekSetGroup;
                    settingGroup.setSettingGroupName(settingGroupList.get(i).getSettingGroupName());
                    settingGroup.setSettingGroupDesc(settingGroupList.get(i).getSettingGroupDesc());
                    settingGroup.setUpdatedBy("Admin");
                    settingGroup.setUpdatedTime(new Date());
                    settingGroup.setRecordFlag("E");
                    response.setSettingGroupCd(settingGroupList.get(i).getSettingGroupCode());
                    response.setMessage("Data "+response.getSettingGroupCd()+" in row "+a+" is updated");
                    response.setRow(a);
                    a++;
                } else if (cekSetGroup == null) {
                    // Insert
                    settingGroup.setSettingGroupCode(settingGroupList.get(i).getSettingGroupCode());
                    settingGroup.setSettingGroupName(settingGroupList.get(i).getSettingGroupName());
                    settingGroup.setSettingGroupDesc(settingGroupList.get(i).getSettingGroupDesc());
                    settingGroup.setCreatedBy("Admin");
                    settingGroup.setCreatedTime(new Date());
                    settingGroup.setRecordFlag("A");
                    response.setSettingGroupCd(settingGroupList.get(i).getSettingGroupCode());
                    response.setMessage("Data "+response.getSettingGroupCd()+" in row "+a+" is added");
                    response.setRow(a);
                    a++;
                }
                settingGroupSave.add(settingGroup);
            }
            resultResp.add(response);
        }
        repositoryJPA.saveAll(settingGroupSave);
        return resultResp;
    }


}
