package com.erudition.dao;

import com.erudition.bean.CategoryEntity;
import com.erudition.bean.FilesEntity;
import com.erudition.bean.UserEntity;
import com.erudition.page.Page;
import com.erudition.page.PageHandler;
import com.erudition.util.MultipartFileUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

/**
 * Created by tsj on 16-5-3.
 */
@Repository("resourcesDao")
public class ResourcesDao extends BaseDao{


    @Autowired
    @Qualifier("categoryDao")
    CategoryDao categoryDao;


    @Autowired
    private PageHandler pageHandler;

    public FilesEntity getById(int id){
        return get(FilesEntity.class,id);
    }

    public Page<FilesEntity> getResourcesByPage(int pageNum,int pageSize,int thirdId){
        String hql = "from FilesEntity as files where files.categoryId=?";
        Query query = query(hql);
        query.setInteger(0,thirdId);
        return pageHandler.getPage(pageNum, pageSize,
                FilesEntity.class, query);
    }

    public Page<FilesEntity> getResourcesByKeyword(int pageNum,int pageSize,String key){
        String hql = "from FilesEntity as files where files.keywords like ?";
        Query query = query(hql);
        query.setString(0, "%" + key + "%");
        return pageHandler.getPage(pageNum, pageSize,
                FilesEntity.class, query);
    }


    public void saveFiles(String cate1 , String cate2 , String cate3 , MultipartFile file , UserEntity user){
        FilesEntity fileEntity = new FilesEntity();

        fileEntity.setTitle(file.getOriginalFilename());
        System.out.println("123345667"+file.getOriginalFilename());
        fileEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        fileEntity.setSize(Double.valueOf(file.getSize()));
        fileEntity.setKeywords(cate1+cate2+cate3);
        fileEntity.setType(getFileTypeByFileName(file.getName()));
        fileEntity.setCreater(user.getUserName());

        CategoryEntity category = categoryDao.getById(Integer.valueOf(cate3));

        fileEntity.setCategoryId(category.getId());
        fileEntity.setCategoryName(category.getCategoryName());
        System.out.println("uploadDao in ......");

        //TODO:暂定视频
        String url = MultipartFileUtils.saveFile(file, "/usr/local/erudition/video");
        fileEntity.setUrl(url);
        save(fileEntity);
        System.out.println("Dao out ......");


    }


    private String getFileTypeByFileName(String name){

        int index = name.lastIndexOf('.');

        return name.substring(index+1);

    }

}
