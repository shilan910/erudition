package com.erudition.controller;

import com.erudition.bean.CollectionEntity;
import com.erudition.bean.FilesEntity;
import com.erudition.bean.UserEntity;
import com.erudition.dao.CollectionDao;
import com.erudition.entity.MessageStatus;
import org.apache.commons.io.monitor.FileEntry;
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
    public MessageStatus addtoCollection(Model model,HttpSession session,@PathVariable("fid") int fid){

        boolean flag=false;
        String message="";
        int status=0;
        UserEntity user = (UserEntity)session.getAttribute("loginUser");
        List<FilesEntity> collections = collectionDao.getByUid(user.getId());
        int rule_collection = Integer.parseInt((String)session.getAttribute("rule_collection"));
        int real_collection = collections.size();
        if(real_collection>=rule_collection){
            message = "收藏量已达上限！";
        }else{
            System.out.println("begin check file!!!");
            for(FilesEntity file:collections){
                System.out.println(file.getId());
                if(file.getId()==fid){
                    flag = true;
                    break;
                }
            }
            System.out.println("文件最终判断!!!");
            if(!flag){
                collectionDao.createARecord(fid,user.getId());
                System.out.println("插入成功！");
                message = "成功添加至常用目录";
                status = 1;  //成功添加时状态为1
            }else{
                System.out.println("已经存在的记录！");
                message = "常用目录已存在该文件";
                status = 0;  //重复添加添加时状态为0
            }
        }
       return new MessageStatus(message,status);
    }

    @ResponseBody
    @RequestMapping(value = "/showcollections" , method = RequestMethod.GET)
    public List<FilesEntity> showCollections(Model model,HttpSession session){

        session.setAttribute("flagofcollection",1);
        session.setAttribute("cateIsActive",1);
        System.out.println("show collections!");
//        model.addAttribute("showcollections",collectionDao.getByUid((int)session.getAttribute("userid")));
        UserEntity user = (UserEntity) session.getAttribute("loginUser");
        int collectionsum = Integer.parseInt((String)session.getAttribute("rule_collection"));
        List<FilesEntity> collections = collectionDao.getByUid(user.getId());
        List<FilesEntity> result = new ArrayList<FilesEntity>();
        int realsum = collections.size();
        if(realsum>collectionsum){
            for(int i=0;i<collectionsum;i++){
                result.add(collections.get(i));
            }
            return result;
        }

//        return "index";
        return collections;
    }
}
