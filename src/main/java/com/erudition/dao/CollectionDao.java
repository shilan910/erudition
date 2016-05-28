package com.erudition.dao;

import com.erudition.bean.CollectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.management.Query;

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

}
