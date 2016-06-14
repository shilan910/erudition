package com.erudition.dao;

import com.erudition.bean.FilesEntity;
import com.erudition.bean.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sl on 16-6-14.
 */
@Repository("recommendDao")
public class RecommendDao extends BaseDao{

    @Autowired
    @Qualifier("resourcesDao")
    ResourcesDao resourcesDao;

    public List<FilesEntity> getRecentFiles(int userid){

        String hql = "select fileId from LogEntity as log where userId=? distinct";
        org.hibernate.Query query = query(hql);
        query.setInteger(0,userid);
        List<Integer> fileIds = query.list();
        List<FilesEntity> recentFiles = new ArrayList<>();
        for(int fid : fileIds){
            recentFiles.add(resourcesDao.getById(fid));
        }

        return recentFiles;
    }






}
