package com.whiteopen.training.module.parameter.service;

import com.whiteopen.training.common.GeneralResponse;
import com.whiteopen.training.module.parameter.entity.ParameterGroup;
import com.whiteopen.training.module.parameter.model.ParameterChildModel;
import com.whiteopen.training.module.parameter.model.ParameterGroupModel;
import com.whiteopen.training.module.parameter.repository.ParameterRepositoryJPA;
import com.whiteopen.training.module.parameter.vo.ParameterAddVo;
import com.whiteopen.training.module.parameter.vo.ParameterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ParameterServiceImpl implements ParameterService{

    @Autowired
    private ParameterRepositoryJPA repositoryJPA;

    @Override
    public List<String> getParamCode() {
        List<String> listParamCode = repositoryJPA.getParamCode();
        return listParamCode;
    }

    @Override
    public List<ParameterGroupModel> searchingParameter(ParameterVo request) {
        List<Object[]> listParamGroup = repositoryJPA.searchData(request.getParamGroupCode(), request.getParamGroupName(), request.getParam());
        List<ParameterGroupModel> parameterGroupModelList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

        for(int i = 0; i < listParamGroup.size(); i++) {
            Object[] objHeader = (Object[]) listParamGroup.get(i);
            List<Object[]> dataParam = repositoryJPA.getParamName(objHeader[0]!= null ? ((String) objHeader[0]) : null);

            ParameterGroupModel groupModel = new ParameterGroupModel();
            List<ParameterChildModel> childModelList = new ArrayList<>();
            String value = "";
            for(int j=0; j< dataParam.size(); j++) {
                Object[] objPara = (Object[]) dataParam.get(j);
                ParameterChildModel childModel = new ParameterChildModel();

                value += objPara[1]!= null ? ((String) objPara[1]) : null;
                if(j+1 != dataParam.size()) {
                    value += ", ";
                }

                childModel.setParamCode(objPara[2]!= null ? ((String) objPara[2]) : null);
                childModel.setParamName(objPara[1]!= null ? ((String) objPara[1]) : null);
                childModel.setParamDesc(objPara[3]!= null ? ((String) objPara[3]) : null);

                childModelList.add(childModel);
            }

            SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            Date create = null, update = null;
            try {
                if(objHeader[3] != null) {
                    create = sdfInput.parse(""+objHeader[3]);

                }
                if(objHeader[5] != null) {
                    update = sdfInput.parse(""+objHeader[5]);
                }
            } catch (Exception e) {

            }

            groupModel.setParamGroupCode(objHeader[0]!= null ? ((String) objHeader[0]) : null);
            groupModel.setParamGroupName(objHeader[1] != null ? ((String) objHeader[1]) : null);
            groupModel.setParamName(value);
            groupModel.setCreatedBy(objHeader[2] != null ? ((String) objHeader[2]) : null);
            groupModel.setUpdatedBy(objHeader[4] != null ? ((String) objHeader[4]) : null);
            groupModel.setCreatedTime(create != null ? (sdf.format(create)) : "");
            groupModel.setUpdatedTime(update != null ? (sdf.format(update)) : "");
            groupModel.setListParameter(childModelList);
            parameterGroupModelList.add(groupModel);
        }
        return parameterGroupModelList;
    }

    @Override
    public GeneralResponse<ParameterGroupModel> submitParameter(ParameterAddVo request) {
        ParameterGroup parameterGroupEntity =new ParameterGroup();
        /*parameterGroupEntity.setParamGroupCode(request);*/
        return null;

    }
}
