package com.erudition.controller;

import com.erudition.bean.FilesEntity;
import com.erudition.bean.UserEntity;
import com.erudition.dao.ResourcesDao;
import com.erudition.entity.File;
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
 * Created by tsj on 16-5-3.
 */
@Controller
@RequestMapping("/resources")
public class ResourcesController {
    @Autowired
    @Qualifier("resourcesDao")
    ResourcesDao resourcesDao;

    @ResponseBody
    @RequestMapping(value = "/{tid}" , method = RequestMethod.GET)
    public Page<FilesEntity> getResourcesByPage(HttpSession session, String page, Model model,
                                                @PathVariable("tid") int ThirdId){
        int pageNum = page == null ? 1 : Integer.valueOf(page);     //第一次访问为空就为第一页！

        Page<FilesEntity> resources  = resourcesDao.getResourcesByPage(pageNum,1,ThirdId);
        model.addAttribute("resources",resources);
        System.out.println("before");
        session.setAttribute("flagofcollection",0);
        session.removeAttribute("showcollections");
        model.addAttribute("page", resources);
        model.addAttribute("currentPage", pageNum);
        System.out.println("after");
        return resources;
    }

    @ResponseBody
    @RequestMapping(value = "/file/{fileid}" , method = RequestMethod.GET)
    public File getRelations(HttpSession httpSession,
                                          @PathVariable("fileid") int fileId,Model model){

        //当前文件添加进log表
        UserEntity user = (UserEntity) httpSession.getAttribute("loginUser");
        resourcesDao.addLog(user.getId(),fileId);


        File file = new File();

        List<FilesEntity> relationfiles = new ArrayList<FilesEntity>();

        FilesEntity file0  = resourcesDao.getById(fileId);
        String relations = file0.getRelations();

        file.setFile(file0);


        if(relations != null){
            String [] relationsarr = relations.split(",");
            for(String re:relationsarr){
                if(re != ""){
                    relationfiles.add(resourcesDao.getById(Integer.parseInt(re)));
                }
            }
        }

        file.setRelationfiles(relationfiles);
        return file;

    }
}
