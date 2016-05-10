package com.erudition.controller;

import com.erudition.bean.FilesEntity;
import com.erudition.dao.ResourcesDao;
import com.erudition.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/getResourcesByPage/{tid}/{pageNum}" , method = RequestMethod.GET)
    public String getResourcesByPage(HttpSession httpSession,@PathVariable("tid") String ThirdId,@PathVariable ("pageNum") int pageNum){

        Page<FilesEntity> resources  = resourcesDao.getResourcesByPage(pageNum,20,ThirdId);
        httpSession.setAttribute("resources",resources);
        return "index";
    }

    @RequestMapping(value = "/getRelations/{fileid}" , method = RequestMethod.POST)
    public String getRelations(HttpSession httpSession,@PathVariable("fileid") int fileId){

        List<FilesEntity> relationfiles = new ArrayList<FilesEntity>();

        FilesEntity file  = resourcesDao.getById(fileId);
        String relations = file.getRelations();
        if(!relations.isEmpty()){
            String [] relationsarr = relations.split(",");
            for(String re:relationsarr){
                if(!re.isEmpty()){
                    System.out.println(re);
                    relationfiles.add(resourcesDao.getById(Integer.parseInt(re)));
                }
            }
        }

        httpSession.setAttribute("relationalresources",relationfiles);
        return "index";
    }
}
