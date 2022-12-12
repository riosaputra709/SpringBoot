package com.whiteopen.training.module.parameter.repository;

import com.whiteopen.training.module.parameter.entity.ParameterGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParameterRepositoryJPA extends PagingAndSortingRepository<ParameterGroup,String> {

    @Query(value = "SELECT PARAM_GROUP_CODE FROM APP_PARAM_GROUP"
            , nativeQuery = true)
    List<String> getParamCode();

    @Query(value = "SELECT DISTINCT apg.PARAM_GROUP_CODE, apg.PARAM_GROUP_NAME, apg.CREATED_BY, apg.CREATED_TIME, apg.UPDATED_BY, apg.UPDATED_TIME "
            +		"FROM APP_PARAM_GROUP apg LEFT JOIN APP_PARAM ap ON apg.PARAM_GROUP_CODE = ap.PARAM_GROUP_CODE "
            + 	   " WHERE apg.RECORD_FLAG != 'D' "
            +      "AND (:paramGroupCd = '' or UPPER(apg.PARAM_GROUP_CODE) like '%' || upper(:paramGroupCd) || '%') "
            +      "AND  (:paramGroupName = '' or UPPER(apg.PARAM_GROUP_NAME) like '%' || upper(:paramGroupName) || '%') "
            +      "AND  (:param = '' or UPPER(ap.PARAM_NAME) like '%' || upper(:param) || '%' OR ap.PARAM_NAME IS null) ",
            countQuery = " SELECT count(DISTINCT apg.PARAM_GROUP_CODE) "
                    + 		"FROM APP_PARAM_GROUP apg LEFT JOIN APP_PARAM ap ON apg.PARAM_GROUP_CODE = ap.PARAM_GROUP_CODE "
                    + 	   "WHERE "
                    +      "(:paramGroupCd = '' or UPPER(apg.PARAM_GROUP_CODE) like '%' || upper(:paramGroupCd) || '%') "
                    +      "AND  (:paramGroupName = '' or UPPER(apg.PARAM_GROUP_NAME) like '%' || upper(:paramGroupName) || '%') "
                    +      "AND  (:param = '' or UPPER(ap.PARAM_NAME) like '%' || upper(:param) || '%' OR ap.PARAM_NAME IS null) "
            ,nativeQuery = true)
    List<Object[]> searchData(@Param("paramGroupCd") String paramGroupCd, @Param("paramGroupName") String paramGroupName, @Param("param") String param);

    @Query(value = "SELECT ap.PARAM_GROUP_CODE, ap.PARAM_NAME, ap.PARAM_CODE, ap.PARAM_DESC, ap.LINE_NO "
            +		"FROM APP_PARAM_GROUP apg JOIN APP_PARAM ap ON apg.PARAM_GROUP_CODE = ap.PARAM_GROUP_CODE "
            + 	   "WHERE ap.RECORD_FLAG != 'D' "
            +      "AND (:paramGroupCd = '' or UPPER(apg.PARAM_GROUP_CODE) like '%' || upper(:paramGroupCd) || '%') "
            ,nativeQuery = true)
    List<Object[]> getParamName(@Param("paramGroupCd") String paramGroupCd);
}

