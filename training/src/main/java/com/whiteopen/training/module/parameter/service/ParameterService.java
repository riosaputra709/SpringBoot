package com.whiteopen.training.module.parameter.service;

import com.whiteopen.training.common.GeneralResponse;
import com.whiteopen.training.module.parameter.entity.ParameterGroup;
import com.whiteopen.training.module.parameter.model.ParameterGroupModel;
import com.whiteopen.training.module.parameter.vo.ParameterAddVo;
import com.whiteopen.training.module.parameter.vo.ParameterVo;

import java.util.List;

public interface ParameterService {
    List<String> getParamCode();

    List<ParameterGroupModel> searchingParameter(ParameterVo request);

    GeneralResponse<ParameterGroupModel> submitParameter(ParameterAddVo request);
}
