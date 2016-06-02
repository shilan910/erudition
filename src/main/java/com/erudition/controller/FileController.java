package com.erudition.controller;

import com.erudition.bean.FilesEntity;
import com.erudition.bean.UserEntity;
import com.erudition.dao.CategoryDao;
import com.erudition.dao.ResourcesDao;
import com.erudition.util.RelationUtil;
import org.apache.commons.io.FileUtils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.jms.Session;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by sl on 16-5-3.
 */
@Controller
@RequestMapping("/admin/file")
public class FileController {

    @Autowired
    @Qualifier("resourcesDao")
    ResourcesDao resourcesDao;

    @Autowired
    @Qualifier("categoryDao")
    CategoryDao categoryDao;


    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload(HttpSession session, Model model) {
        session.setAttribute("adminSidebarActive", 1);
        model.addAttribute("firstCategorys", categoryDao.getFirstCategory());
        List<FilesEntity> files = resourcesDao.getAll("FilesEntity");
        session.setAttribute("allfiles",files);
        return "admin/upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(String cate1, String cate2, String cate3,
                         @RequestParam MultipartFile[] files, HttpSession session) {

    //        Configuration config = new Configuration().configure("hibernate.cfg.xml");
    //        SessionFactory SessionFactory = config.buildSessionFactory();
    //        org.hibernate.Session session1 = SessionFactory.getCurrentSession();
        System.out.println("uploadController000 in ......");

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                System.out.println(file.getOriginalFilename());
                //TODO 在这里为新文件设置relations，可能要修改 resourcesDao.saveFiles方法的接口
                resourcesDao.saveFiles(cate1, cate2, cate3, file, (UserEntity) session.getAttribute("loginUser"));
//                session1.refresh(file);
                System.out.println("uploadController in ......");
            }

        }

        return "admin/upload";
    }


    @RequestMapping(value = "/download/{fid}", method = RequestMethod.GET)
    public ResponseEntity download(@PathVariable("fid") int fid) throws IOException {
        FilesEntity file = resourcesDao.getById(fid);
        String dfileName = new String(file.getTitle().getBytes("gb2312"), "iso8859-1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        File fileReal = new File(file.getUrl());
        return new ResponseEntity(FileUtils.readFileToByteArray(fileReal), headers, HttpStatus.CREATED);
    }


    //当找出所有与新上传的文件有关联关系的文件id时
    //除了要设置新文件的relations字段，还要更新所有相关文件的relations字段
    private void updateRelations(String[] relations,int newfileid){
        for(String re:relations){
            FilesEntity relationalfile= (FilesEntity)resourcesDao.getById(Integer.parseInt(re));
            String relation = relationalfile.getRelations();
            relationalfile.setRelations(relation+","+newfileid);
            resourcesDao.save(relationalfile);
        }
    }


}
