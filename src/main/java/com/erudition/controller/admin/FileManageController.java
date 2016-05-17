package com.erudition.controller.admin;

import com.erudition.bean.CategoryEntity;
import com.erudition.dao.CategoryDao;
import com.erudition.dao.ResourcesDao;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


/**
 * Created by Administrator on 2016/5/13.
 */
@Controller
@RequestMapping("/admin/filecollect")
public class FileManageController {

    @Autowired
    @Qualifier("categoryDao")
    CategoryDao categoryDao;

    @Autowired
    @Qualifier("resourcesDao")
    ResourcesDao resourcesDao;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String file(Model model){


        model.addAttribute("adminCates",categoryDao.getFirstCategory());
        return "admin/file-collect";    //此处填jsp页面
    }


    @RequestMapping(value = "/category/{cateId}" , method = RequestMethod.GET)
    public String getCategory(@PathVariable("cateId") int cateId , Model model,HttpSession httpSession){

        CategoryEntity category = categoryDao.getById(cateId);

        if(category.getCategory1Id() == 0){ //一级目录
            model.addAttribute("adminCates",categoryDao.getSecondCategoryByFirst(cateId));
            model.addAttribute("cateLayer","1");
            httpSession.setAttribute("cate1",cateId);
        }else if(category.getCategory2Id() == 0){ //二级目录
            model.addAttribute("adminCates",categoryDao.getThirdCategoryByFS(cateId));
            model.addAttribute("cateLayer","2");
            httpSession.setAttribute("cate2",cateId);
        }else { //三级目录
            model.addAttribute("cateLayer","3");
            model.addAttribute("adminCates",resourcesDao.getResourcesByPage(1,7,cateId));
            httpSession.setAttribute("cate3",cateId);
        }

        return "admin/file-collect";
    }

    @RequestMapping(value = "/addcategory/{parentCateId}" , method = RequestMethod.POST)
    public String addCategory(@PathVariable("parentCateId") int parentCateId ,
                              String newCateName){

        categoryDao.addCategory(parentCateId,newCateName);

        return "redirect:/admin/filecollect/category/"+parentCateId;
    }

    @RequestMapping(value = "/changename" , method = RequestMethod.POST)
    public String changeName(String newname,int cateid,int parentcateid){
        categoryDao.updateCateName(newname, cateid);
        return "redirect:/admin/filecollect/category/"+parentcateid;
    }

    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
    public String DeletName(int cateid){
//        categoryDao.updateCateName(newname, cateid);
        CategoryEntity cate = new CategoryEntity();
        cate.setId(cateid);
        categoryDao.delete(cate);
        System.out.println(cateid);
        return "redirect:/admin/filecollect/category/"+cateid;
    }
}