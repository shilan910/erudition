package com.erudition.controller.admin;

import com.erudition.bean.CategoryEntity;
import com.erudition.bean.FilesEntity;
import com.erudition.bean.UserEntity;
import com.erudition.dao.CategoryDao;
import com.erudition.dao.ResourcesDao;
import com.erudition.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;


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

    @Autowired
    @Qualifier("userDao")
    UserDao userDao;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String file(Model model , HttpSession session){


        model.addAttribute("adminCates",categoryDao.getFirstCategory());
        model.addAttribute("cateLayer",0);
        session.setAttribute("adminSidebarActive",0);
        return "admin/file-collect";    //此处填jsp页面
    }


    @RequestMapping(value = "/category/{cateId}" , method = RequestMethod.GET)
    public String getCategory(@PathVariable("cateId") int cateId , Model model,HttpSession httpSession){

        CategoryEntity category = categoryDao.getById(cateId);

        if(category.getCategory1Id() == 0){ //一级目录
            model.addAttribute("adminCates",categoryDao.getSecondCategoryByFirst(cateId));
            httpSession.setAttribute("cate1name",category.getCategoryName());
            model.addAttribute("cateLayer","1");
            httpSession.setAttribute("cate1",cateId);
        }else if(category.getCategory2Id() == 0){ //二级目录
            model.addAttribute("adminCates",categoryDao.getThirdCategoryByFS(cateId));
            httpSession.setAttribute("cate2name",category.getCategoryName());
            model.addAttribute("cateLayer","2");
            httpSession.setAttribute("cate2",cateId);
        }else { //三级目录
            model.addAttribute("cateLayer","3");
            model.addAttribute("adminCates",resourcesDao.getResourcesByPage(1,7,cateId));
            httpSession.setAttribute("cate3name",category.getCategoryName());
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
        if(parentcateid==0)return "redirect:/admin/filecollect";
        else return "redirect:/admin/filecollect/category/"+parentcateid;
    }

    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
    public String DeleteCate(int cateid){
//        categoryDao.updateCateName(newname, cateid);
        CategoryEntity cate = new CategoryEntity();
        cate.setId(cateid);
        categoryDao.delete(cate);
        System.out.println(cateid);
        return "redirect:/admin/filecollect/category/"+cateid;
    }

    @RequestMapping(value = "/search" , method = RequestMethod.POST)
    public String search(HttpSession httpSession,Model model,String key){
        UserEntity user = (UserEntity)httpSession.getAttribute("loginUser");
        List<FilesEntity> files = resourcesDao.getResourcesByKeyword(1,7,key).getList();


        httpSession.setAttribute("relationsFiles",resourcesDao.getRelationFileByOne(files));
        httpSession.setAttribute("searchresult",files);
        httpSession.setAttribute("flagofcollection",0);
        if(user.getAuthority().equals("1")){
            System.out.println("你是管理员！");
            return "admin/file_result";
        }
        else {
            System.out.println("你是普通用户！");
            return "redirect:/file/toresult";
        }

//        return "admin/file_result";
    }

    @RequestMapping(value = "/newcate" , method = RequestMethod.POST)
    public String newcate(String catename,int cate1,int cate2,int cate3){
        CategoryEntity categoryEntity = new CategoryEntity();
        String path = new String();
        if(cate1==0){
            categoryEntity.setCategory1Id(0);
            path = "redirect:/admin/filecollect";
        }
        if(cate2==0){
            categoryEntity.setCategory1Id(cate1);
            categoryEntity.setCategory2Id(0);
            path = "redirect:/admin/filecollect/category/"+cate1;
        }
        if(cate3==0){
            categoryEntity.setCategory1Id(cate1);
            categoryEntity.setCategory2Id(cate2);
            categoryEntity.setCategory3Id(0);
            path = "redirect:/admin/filecollect/category/"+cate2;
        }
        categoryEntity.setCategoryName(catename);
        categoryDao.save(categoryEntity);
        return path;
    }
}