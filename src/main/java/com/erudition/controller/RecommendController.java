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

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2016/6/13.
 */
@Controller
@RequestMapping("/")
public class RecommendController {
    @Autowired
    @Qualifier("categoryDao")
    CategoryDao categoryDao;

    @Autowired
    @Qualifier("resourcesDao")
    ResourcesDao resourcesDao;

    @Autowired
    @Qualifier("recommendDao")
    RecommendDao recommendDao;

    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public String index(HttpSession httpSession){
        httpSession.setAttribute("categories", categoryDao.getCategorys());

        System.out.println("RecommendController");

        UserEntity user = (UserEntity) httpSession.getAttribute("loginUser");

        List<FilesEntity> recentFiles = recommendDao.getRecentFiles(user.getId());
        httpSession.setAttribute("recentFiles",recentFiles);

        httpSession.setAttribute("recommendFiles",resourcesDao.getRelationFileByOne(recentFiles));

        String username = (String)httpSession.getAttribute("username");
        if(username==null) return "login";
        return "recommend";
    }
}
