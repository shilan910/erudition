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

        if(recommendDao.getRecentFiles(userid)!=null){
            List<FilesEntity> recentFiles = recommendDao.getRecentFiles(userid);
            httpSession.setAttribute("recentFiles",recentFiles);

            if(resourcesDao.getRelationFileByOne(recentFiles)!=null)
                httpSession.setAttribute("recommendFiles",resourcesDao.getRelationFileByOne(recentFiles));
        }
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

        UserEntity user = (UserEntity) httpSession.getAttribute("loginUser");

        List<FilesEntity> recentFiles = recommendDao.getRecentFiles(user.getId());

        return resourcesDao.getRelationFileByOne(recentFiles);

    }



}
