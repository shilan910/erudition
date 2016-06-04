package com.erudition.controller;

import com.erudition.bean.FilesEntity;
import com.erudition.bean.UserEntity;
import com.erudition.dao.CollectionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
    @ResponseBody
    public void addtoCollection(Model model,HttpSession session,@PathVariable("fid") int fid){
        boolean flag=false;
        UserEntity user = null;
        user = (UserEntity)session.getAttribute("loginUser");
        int userid = user.getId();
        List<FilesEntity> collections = null;
//        collections = (List<FilesEntity>)session.getAttribute("usercollections");    //这个session来源有问题
        collections = collectionDao.getByUid(userid);

        System.out.println("begin check file!!!");
        for(FilesEntity file:collections){              //这里是空的
            System.out.println(file.getId());
            if(file.getId()==fid){
                flag = true;
                break;
            }
        }
        System.out.println("文件最终判断!!!");
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

        model.addAttribute("showcollections",collectionDao.getByUid((int)session.getAttribute("userid")));
        session.setAttribute("flagofcollection",1);
        session.setAttribute("cateIsActive",1);
        System.out.println("show collections!");
        return "index";

    }
}
