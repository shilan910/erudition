package com.erudition.controller;

import com.erudition.bean.CollectionEntity;
import com.erudition.bean.FilesEntity;
import com.erudition.bean.UserEntity;
import com.erudition.dao.CollectionDao;
import com.erudition.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.AssertFalse;
import java.util.List;

/**
 * Created by tsj on 16-5-27.
 */
@Controller
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    @Qualifier("collectionDao")
    CollectionDao collectionDao;

    @RequestMapping(value = "/addtocollection/{fid}" , method = RequestMethod.GET)
    public void addtoCollection(Model model,HttpSession session,@PathVariable("fid") int fid){
        boolean flag=false;
        UserEntity user = (UserEntity)session.getAttribute("loginUser");
        int userid = user.getId();
        List<FilesEntity> collections = (List<FilesEntity>)session.getAttribute("usercollections");
        for(FilesEntity file:collections){
            if(file.getId()==fid){
                flag = true;
            }
        }
        if(!flag){
            collectionDao.createARecord(fid,userid);
            System.out.println("插入成功！");
            model.addAttribute("collectionflag","0");
        }else{
            System.out.println("已经存在的记录！");
            model.addAttribute("collectionflag","1");
        }

       // return "index";
    }

    @RequestMapping(value = "/showcollections" , method = RequestMethod.GET)
    public String showCollections(Model model,HttpSession session){
        model.addAttribute("showcollections",session.getAttribute("usercollections"));
        session.setAttribute("flagofcollection",1);
        System.out.println("show collections!");
        return "index";
    }
}
