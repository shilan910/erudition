package com.erudition.dao;

import com.erudition.bean.CategoryEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tsj on 16-4-28.
 */
@Repository("categoryDao")
public class CategoryDao extends BaseDao {

    public CategoryEntity getById(int id){
        return get(CategoryEntity.class,id);
    }

    public List<CategoryEntity> getFirstCategory(){
        String hql = "from CategoryEntity as cate where cate.id =0";
        Query query = query(hql);
        return query.list();
    }

    public List<CategoryEntity> getSecondCategoryByFirst(int firstId){
        String hql = "from CategoryEntity as cate where cate.category1Id =? and category2Id=0";
        Query query = query(hql);
        query.setInteger(0,firstId);
        return query.list();
    }

    public List<CategoryEntity> getThirdCategoryByFS(int firstId,int secondId){
        String hql = "from CategoryEntity as cate where cate.category1Id =? and category2Id=? and category3Id=0";
        Query query = query(hql);
        query.setInteger(0,firstId);
        query.setInteger(1,secondId);
        return query.list();
    }
}
