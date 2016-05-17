package com.erudition.controller;

import com.erudition.bean.CategoryEntity;
import com.erudition.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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


    //@ResponseBody
    @RequestMapping(value = "/firstcates" , method = RequestMethod.GET)
    public String getFirstCategory(Model model){
        List<CategoryEntity> firstCategories;
        firstCategories = categoryDao.getFirstCategory();
        model.addAttribute("firstCates", firstCategories);
        return "index";
    }

//    @ResponseBody
    @RequestMapping(value = "/getSecondCategory/{id}" , method = RequestMethod.GET)
    public String  getSecondCategory(@PathVariable ("id") int firstId,Model model){
        List<CategoryEntity> secondCategories = categoryDao.getSecondCategoryByFirst(firstId);
        model.addAttribute("secondCates", secondCategories);
        System.out.println("Controller : secondCates");
        return "redirect:/index";
    }

//    @ResponseBody
    @RequestMapping(value = "/getThirdCategory/{fid}/{sid}" , method = RequestMethod.GET)
    public String getThirdCategory(Model model,@PathVariable ("fid") int firstId,@PathVariable ("sid") int secondId){
        List<CategoryEntity> secondCategories;
        secondCategories = categoryDao.getThirdCategoryByFS(firstId, secondId);
        model.addAttribute("thirdCates",secondCategories);
        System.out.println("Controller : thirdCates");
        return "redirect:/index";
    }

    @ResponseBody
    @RequestMapping(value = "/getChildrenCategory/{cid}" , method = RequestMethod.GET)
    public List<CategoryEntity> getChildrenCategory(Model model,@PathVariable ("cid") int cid){

        CategoryEntity category = categoryDao.getById(cid);

        if (category.getCategory1Id()==0){
            return categoryDao.getSecondCategoryByFirst(cid);
        }else{
            return categoryDao.getThirdCategoryByFS(cid);
        }
    }


}
