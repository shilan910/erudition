package com.erudition.dao;

import com.erudition.bean.CollectionEntity;
import com.erudition.bean.FilesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsj on 16-5-27.
 */
@Repository("collectionDao")
public class CollectionDao extends BaseDao{


    @Autowired
    @Qualifier("resourcesDao")
    private ResourcesDao resourcesDao;

    public void createARecord(int fileid,int userid){
        CollectionEntity collectionEntity = new CollectionEntity();
        collectionEntity.setFileId(fileid);
        collectionEntity.setUserId(userid);
        save(collectionEntity);
    }

    public List<CollectionEntity> getByFidUid(int fileid,int userid){
        String hql = "from CollectionEntity as collection where fileId=? and userId=?";
        org.hibernate.Query query = query(hql);
        query.setInteger(0,fileid);
        query.setInteger(1,userid);
        return query.list();
    }

    public List<FilesEntity> getByUid(int userid){
        String hql = "select fileId from CollectionEntity as collection where userId=?";
        org.hibernate.Query query = query(hql);
        query.setInteger(0,userid);
        List<Integer> fileIds =  query.list();
        List<FilesEntity> collectionFiles = new ArrayList<>();
        for(Integer fileId : fileIds){
            collectionFiles.add(resourcesDao.getById(fileId));
        }
        return collectionFiles;
    }

}
