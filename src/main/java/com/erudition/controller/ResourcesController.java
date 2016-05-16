package com.erudition.controller;

import com.erudition.bean.FilesEntity;
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
    @RequestMapping(value = "/{tid}/{pageNum}" , method = RequestMethod.GET)
    public Page<FilesEntity> getResourcesByPage(Model model,@PathVariable("tid") int ThirdId,@PathVariable ("pageNum") int pageNum){

        Page<FilesEntity> resources  = resourcesDao.getResourcesByPage(pageNum,7,ThirdId);
        model.addAttribute("resources",resources);
        return resources;
    }

    @RequestMapping(value = "/getRelations/{fileid}" , method = RequestMethod.POST)
    public String getRelations(Model model,@PathVariable("fileid") int fileId){

        List<FilesEntity> relationfiles = new ArrayList<FilesEntity>();

        FilesEntity file  = resourcesDao.getById(fileId);
        String relations = file.getRelations();
        System.out.println("relations"+relations);
        if(!relations.isEmpty()){
            String [] relationsarr = relations.split(",");
            for(String re:relationsarr){
                if(!re.isEmpty()){
                    System.out.println("re"+re);
                    relationfiles.add(resourcesDao.getById(Integer.parseInt(re)));
                }
            }
        }

        model.addAttribute("relationalresources", relationfiles);
        return "index";
    }
}
