package com.whiteopen.training.module.settingGroup.repository;


import com.whiteopen.training.module.settingGroup.entity.SettingGroup;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SettingGroupRepositoryJPA extends PagingAndSortingRepository<SettingGroup,String> {

    @Query(value = "SELECT a FROM SettingGroup a WHERE 1 = 1 " +
            " AND (:settingGroupCode = '' OR setting_group_code LIKE '%'||:settingGroupCode||'%')" +
            " AND (:settingGroupName = '' OR setting_group_name LIKE '%'||:settingGroupName||'%')",
            countQuery = "SELECT count(a) FROM SettingGroup a WHERE 1 = 1 " +
                    " AND (:settingGroupCode = '' OR setting_group_code LIKE '%'||:settingGroupCode||'%')" +
                    " AND (:settingGroupName = '' OR setting_group_name LIKE '%'||:settingGroupName||'%')")
    List<SettingGroup> searchSettingGroup(Pageable pageable, @Param("settingGroupCode") String settingGroupCode, @Param("settingGroupName") String settingGroupName);


    @Transactional
    @Modifying
    @Query(value = "delete from SettingGroup p where setting_group_code in(:settingGroupCode)")
    void deleteByCode(List<String> settingGroupCode);

    @Query("SELECT settingGroupCode FROM SettingGroup a WHERE settingGroupCode = :settingGroupCode")
    String findSettingGroupCode(@Param("settingGroupCode") String settingGroupCode);

    @Query("SELECT count(*) FROM SettingGroup a WHERE 1 = 1 " +
            " AND (:settingGroupCode = '' OR setting_group_code LIKE '%'||:settingGroupCode||'%')" +
            " AND (:settingGroupName = '' OR setting_group_name LIKE '%'||:settingGroupName||'%')")
    int searchCount(@Param("settingGroupCode") String settingGroupCode, @Param("settingGroupName") String settingGroupName);

    @Query(value = "SELECT a FROM SettingGroup a WHERE 1 = 1 " +
            " AND (:settingGroupCode = '' OR setting_group_code LIKE '%'||:settingGroupCode||'%')" +
            " AND (:settingGroupName = '' OR setting_group_name LIKE '%'||:settingGroupName||'%')",
            countQuery = "SELECT count(a) FROM SettingGroup a WHERE 1 = 1 " +
                    " AND (:settingGroupCode = '' OR setting_group_code LIKE '%'||:settingGroupCode||'%')" +
                    " AND (:settingGroupName = '' OR setting_group_name LIKE '%'||:settingGroupName||'%')")
    List<SettingGroup> searchDataDownload(@Param("settingGroupCode") String settingGroupCode, @Param("settingGroupName") String settingGroupName);

}

