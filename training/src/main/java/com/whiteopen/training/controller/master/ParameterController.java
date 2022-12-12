package com.whiteopen.training.controller.master;

import com.whiteopen.training.common.GeneralResponse;
import com.whiteopen.training.module.parameter.entity.ParameterGroup;
import com.whiteopen.training.module.parameter.model.ParameterGroupModel;
import com.whiteopen.training.module.parameter.service.ParameterService;
import com.whiteopen.training.module.parameter.vo.ParameterAddVo;
import com.whiteopen.training.module.parameter.vo.ParameterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("parameter")
public class ParameterController {

    @Autowired
    private ParameterService parameterService;

    private static final String statusSuccess = "success";
    private static final String statusError = "error";
    private static final String messageSuccess = "Processed Successfully";

    private static int countData = 0;

    @PostMapping("getParamCode")
    @ResponseBody
    public GeneralResponse<List<String>> getParamCode(){
        try {
            List<String> data = parameterService.getParamCode();

            return new GeneralResponse<>(statusSuccess, messageSuccess, countData, data);

        }catch (Exception e){
            return new GeneralResponse<>(statusError,e.getMessage());
        }
    }

    @PostMapping("searchParameter")
    @ResponseBody
    public GeneralResponse<List<ParameterGroupModel>> searchParameter(@RequestBody ParameterVo request){
        try {
            List<ParameterGroupModel> data = parameterService.searchingParameter(request);

            return new GeneralResponse<>(statusSuccess, messageSuccess, countData, data);

        }catch (Exception e){
            return new GeneralResponse<>(statusError,e.getMessage());
        }
    }

    @PostMapping("submitParameter")
    @ResponseBody
    public GeneralResponse<GeneralResponse<ParameterGroupModel>> submitParameter(@RequestBody ParameterAddVo request){
        try {

            GeneralResponse<ParameterGroupModel> data = parameterService.submitParameter(request);
            return new GeneralResponse<>(statusSuccess, messageSuccess, countData, data);

        }catch (Exception e){
            return new GeneralResponse<>(statusError,e.getMessage());
        }
    }

}
