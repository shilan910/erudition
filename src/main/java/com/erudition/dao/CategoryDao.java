package com.erudition.dao;

import com.erudition.bean.CategoryEntity;
import com.erudition.entity.Category;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsj on 16-4-28.
 */
@Repository("categoryDao")
public class CategoryDao extends BaseDao {

    public CategoryEntity getById(int id){
        return get(CategoryEntity.class,id);
    }



    public List<Category> getCategorys(){
        List<CategoryEntity> firstCates = this.getFirstCategory();
        List<Category> categories = new ArrayList<>();

        for(CategoryEntity firstCate : firstCates){//一级目录

            List<CategoryEntity> secondCates = this.getSecondCategoryByFirst(firstCate.getId());
            List<Category> first_children = new ArrayList<>();

            for(CategoryEntity secondCate : secondCates){//二级目录
                List<CategoryEntity> thirdCates = this.getThirdCategoryByFS(firstCate.getId() , secondCate.getId());
                List<Category> second_children = new ArrayList<>();

                for(CategoryEntity thirdCate : thirdCates){
                    second_children.add(new Category(thirdCate.getId() , thirdCate.getCategoryName() , null));
                }
                first_children.add(new Category(secondCate.getId() , secondCate.getCategoryName() , second_children));
            }

            categories.add(new Category(firstCate.getId() , firstCate.getCategoryName() , first_children));
        }

        return categories;
    }

    public List<CategoryEntity> getFirstCategory(){
        String hql = "from CategoryEntity as cate where cate.category1Id =0";
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

    public List<CategoryEntity> getThirdCategoryByFS(int secondId){
        String hql = "from CategoryEntity as cate where category2Id=? and category3Id=0";
        Query query = query(hql);
        query.setInteger(0,secondId);
        return query.list();
    }


    /**
     * 添加目录
     * @param parentCateId 父节点的id
     * @param newCateName 新创建目录的名称
     */
    public void addCategory(int parentCateId , String newCateName){

        CategoryEntity newCategory = new CategoryEntity();

        if(parentCateId == 0){ //新创建的目录为一级目录
            newCategory.setCategory1Id(0);
            newCategory.setCategoryName(newCateName);
        }else {

            CategoryEntity parentCate = getById(parentCateId);
            if(parentCate.getCategory1Id() == 0){ //新创建的目录为二级目录
                newCategory.setCategory1Id(parentCateId);
                newCategory.setCategory2Id(0);
                newCategory.setCategoryName(newCateName);
            }else{
                newCategory.setCategory1Id(parentCate.getCategory1Id());
                newCategory.setCategory2Id(parentCateId);
                newCategory.setCategory3Id(0);
                newCategory.setCategoryName(newCateName);
            }
        }
        save(newCategory);
    }

    public void updateCateName(String newname,int cateid){
        String hql = "update CategoryEntity as cate set cate.categoryName='"+newname+"' where id='"+cateid+"'";
        Query query = query(hql);
        query.executeUpdate();
    }


}
