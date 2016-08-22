package com.erudition.dao;

import com.erudition.bean.FilesEntity;
import com.erudition.bean.UserEntity;
import com.erudition.page.Page;
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

    public Page<FilesEntity> getRecentFiles(int userid , int pageNum , int pageNow){   //pageNum一页的大小

        String hql = "select distinct fileId from LogEntity as log where log.userId=?";
        org.hibernate.Query query = query(hql);
        query.setInteger(0,userid);
        List<Integer> fileIds = query.list();
        List<FilesEntity> recentFiles = new ArrayList<>();

        Page<FilesEntity> pageFile = new Page<>();

        pageFile.setPageNow(pageNow);

        int totolNum = fileIds.size()/pageNum + (fileIds.size()%pageNum==0?0:1);

        pageFile.setTotalPageCount(totolNum);

        int maxnIndex = fileIds.size() > pageNum*pageNow ? pageNow*pageNum : fileIds.size();

        for(int i=pageNum*(pageNow-1) ; i<maxnIndex ; i++){
            recentFiles.add(resourcesDao.getById(fileIds.get(i)));
        }

        pageFile.setList(recentFiles);

        System.out.println("pageNow:"+pageNow);
        System.out.println("pageNum:"+pageNum);
        System.out.println("totolNum:"+totolNum);

        if(pageNow==1){
            pageFile.setHasPre(false);
        }else{
            pageFile.setHasPre(true);
        }

        if(pageNow == totolNum){
            pageFile.setHasNext(false);
        }else{
            pageFile.setHasNext(true);
        }


//        if(recentFiles==null){
//            for(int i=1 ; i<=5 ; i++){
//                if(resourcesDao.getById(i)!=null)
//                    recentFiles.add(resourcesDao.getById(i));
//            }
//        }


        return pageFile;
    }



    public List<FilesEntity> getRecentFilesById(int userid){

        String hql = "select distinct fileId from LogEntity as log where log.userId=?";
        org.hibernate.Query query = query(hql);
        query.setInteger(0,userid);
        List<Integer> fileIds = query.list();
        List<FilesEntity> recentFiles = new ArrayList<>();

        for(int id : fileIds){
            recentFiles.add(resourcesDao.getById(id));
        }



        if(recentFiles==null){
            for(int i=1 ; i<=5 ; i++){
                if(resourcesDao.getById(i)!=null)
                    recentFiles.add(resourcesDao.getById(i));
            }
        }


        return recentFiles;
    }




}
