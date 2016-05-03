package com.erudition.dao;

import com.erudition.bean.CategoryEntity;
import com.erudition.bean.FilesEntity;
import com.erudition.page.Page;
import com.erudition.page.PageHandler;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tsj on 16-5-3.
 */
@Repository("resourcesDao")
public class ResourcesDao extends BaseDao{

    @Autowired
    private PageHandler pageHandler;

    public FilesEntity getById(int id){
        return get(FilesEntity.class,id);
    }

    public Page<FilesEntity> getResourcesByPage(int pageNum,int pageSize,String thirdId){
        String hql = "from FilesEntity as files where files.categoryId=3 and files.type =?";
        Query query = query(hql);
        query.setString(0,thirdId);
        return pageHandler.getPage(pageNum, pageSize,
                FilesEntity.class, query);
    }
}
