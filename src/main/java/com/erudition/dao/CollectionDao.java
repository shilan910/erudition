package com.erudition.dao;

import com.erudition.bean.CollectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import java.util.List;

/**
 * Created by tsj on 16-5-27.
 */
@Repository("collectionDao")
public class CollectionDao extends BaseDao{


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

    public List<CollectionEntity> getByUid(int userid){
        String hql = "from CollectionEntity as collection where userId=?";
        org.hibernate.Query query = query(hql);
        query.setInteger(0,userid);
        return query.list();
    }

}
