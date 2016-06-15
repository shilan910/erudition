package com.erudition.controller;

import com.erudition.bean.FilesEntity;
import com.erudition.bean.UserEntity;
import com.erudition.dao.CategoryDao;
import com.erudition.dao.RecommendDao;
import com.erudition.dao.ResourcesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sl on 16-4-27.
 */

@Controller
@RequestMapping("/")
public class IndexController {


    @Autowired
    @Qualifier("categoryDao")
    CategoryDao categoryDao;


    @Autowired
    @Qualifier("resourcesDao")
    ResourcesDao resourcesDao;

    @Autowired
    @Qualifier("recommendDao")
    RecommendDao recommendDao;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpSession httpSession){
        httpSession.setAttribute("categories", categoryDao.getCategorys());

        System.out.println("IndexController");


        String username = (String)httpSession.getAttribute("username");
        if(username==null) return "login";

        UserEntity user = (UserEntity) httpSession.getAttribute("loginUser");
        int userid = (int) httpSession.getAttribute("userid");

            List<FilesEntity> recentFiles = recommendDao.getRecentFiles(userid);
            httpSession.setAttribute("recentFiles",recentFiles);

            List<FilesEntity> recommendFiles = new ArrayList<>();
            recommendFiles = resourcesDao.getRelationFileByOne(recentFiles);
            if(recommendFiles.size()==0){
                recommendFiles.add(resourcesDao.getById(7));
                recommendFiles.add(resourcesDao.getById(8));

                recommendFiles.add(resourcesDao.getById(9));

                recommendFiles.add(resourcesDao.getById(13));
                recommendFiles.add(resourcesDao.getById(14));
            }else{
                for (FilesEntity file : recommendFiles){
                    System.out.println("1234567890: "+file.getId());
                }
            }
            httpSession.setAttribute("recommendFiles",recommendFiles);
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/recent",method = RequestMethod.GET)
    public List<FilesEntity> recent(HttpSession httpSession){

        UserEntity user = (UserEntity) httpSession.getAttribute("loginUser");

        List<FilesEntity> recentFiles = recommendDao.getRecentFiles(user.getId());
       return recentFiles;

    }

    @ResponseBody
    @RequestMapping(value = "/recommend",method = RequestMethod.GET)
    public List<FilesEntity> recommend(HttpSession httpSession){
        System.out.println("开始获取推荐数据");
        UserEntity user = (UserEntity) httpSession.getAttribute("loginUser");

        List<FilesEntity> recentFiles = recommendDao.getRecentFiles(user.getId());

        List<FilesEntity> files = resourcesDao.getRelationFileByOne(recentFiles);
        for (FilesEntity file : files){
            System.out.println("recommendFileId: "+file.getId());
        }

        return files;

    }



}
