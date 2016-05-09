package com.erudition.controller;

import com.erudition.bean.CategoryEntity;
import com.erudition.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpSession httpSession) {
        List<CategoryEntity> categoryEntities = new ArrayList<CategoryEntity>();
        categoryEntities = categoryDao.getFirstCategory();
        httpSession.setAttribute("firstCates",categoryEntities);

        for(CategoryEntity cate : categoryEntities){
            System.out.println(cate.getCategoryName()+"   "+cate.getId());
        }

        System.out.println("IndexController");
        return "index";
    }
}
