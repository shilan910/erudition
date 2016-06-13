package com.erudition.controller;

import com.erudition.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/6/13.
 */
@Controller
@RequestMapping("/")
public class RecommendController {
    @Autowired
    @Qualifier("categoryDao")
    CategoryDao categoryDao;

    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public String index(HttpSession httpSession){
        httpSession.setAttribute("categories", categoryDao.getCategorys());

        System.out.println("RecommendController");

        String username = (String)httpSession.getAttribute("username");
        if(username==null) return "login";
        return "recommend";
    }
}
