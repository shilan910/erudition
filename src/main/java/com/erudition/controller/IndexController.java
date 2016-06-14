package com.erudition.controller;

import com.erudition.bean.CategoryEntity;
import com.erudition.bean.FilesEntity;
import com.erudition.bean.UserEntity;
import com.erudition.dao.CategoryDao;
import com.erudition.dao.RecommendDao;
import org.apache.commons.io.monitor.FileEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    @Qualifier("recommendDao")
    RecommendDao recommendDao;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpSession httpSession){
        httpSession.setAttribute("categories", categoryDao.getCategorys());

        System.out.println("IndexController");

        String username = (String)httpSession.getAttribute("username");
        if(username==null) return "login";
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/recent",method = RequestMethod.GET)
    public List<FilesEntity> recent(HttpSession httpSession){

        UserEntity user = (UserEntity) httpSession.getAttribute("loginUser");

        List<FilesEntity> recentFiles = recommendDao.getRecentFiles(user.getId());
       return recentFiles;

    }



}
