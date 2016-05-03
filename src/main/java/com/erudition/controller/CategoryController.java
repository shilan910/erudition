package com.erudition.controller;

import com.erudition.bean.CategoryEntity;
import com.erudition.bean.FilesEntity;
import com.erudition.dao.CategoryDao;
import com.erudition.dao.ResourcesDao;
import com.erudition.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsj on 16-4-28.
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    @Qualifier("categoryDao")
    CategoryDao categoryDao;


    @ResponseBody
    @RequestMapping(value = "/firstcates" , method = RequestMethod.GET)
    public Model getFirstCategory(Model model){
        List<CategoryEntity> firstCategories = new ArrayList<CategoryEntity>();
        firstCategories = categoryDao.getFirstCategory();
        model.addAttribute("firstCates", firstCategories);
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/getSecondCategory/{id}" , method = RequestMethod.GET)
    public Model getSecondCategory(@PathVariable ("id") int firstId,Model model){
        List<CategoryEntity> secondCategories = categoryDao.getSecondCategoryByFirst(firstId);
        model.addAttribute("secondCates", secondCategories);
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/getThirdCategory/{fid}/{sid}" , method = RequestMethod.GET)
    public Model getThirdCategory(Model model,@PathVariable ("fid") int firstId,@PathVariable ("sid") int secondId){
        List<CategoryEntity> secondCategories = new ArrayList<CategoryEntity>();
        secondCategories = categoryDao.getThirdCategoryByFS(firstId, secondId);
        model.addAttribute("category3",secondCategories);
        return model;
    }
}
