package com.erudition.controller;

import com.erudition.bean.CollectionEntity;
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
    public void addtoCollection(Model model,HttpSession session,@PathVariable("fid") int fid){
        UserEntity user = (UserEntity)session.getAttribute("loginUser");
        int userid = user.getId();
        List<CollectionEntity> collections = collectionDao.queryByFidUid(fid,userid);
        if(collections.isEmpty()){
            collectionDao.createARecord(fid,userid);
            System.out.println("插入成功！");
            model.addAttribute("collectionflag","0");
        }else{
            System.out.println("已经存在的记录！");
            model.addAttribute("collectionflag","1");
        }

       // return "index";
    }
}
