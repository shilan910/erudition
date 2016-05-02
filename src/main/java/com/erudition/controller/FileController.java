package com.erudition.controller;

import com.erudition.util.MultipartFileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * Created by sl on 16-5-3.
 */
@Controller
@RequestMapping("/file")
public class FileController {


    @RequestMapping(value = "/upload" , method = RequestMethod.GET)
    public String  upload(){
        return "file/upload";
    }

    @RequestMapping(value = "/upload" , method = RequestMethod.POST)
    public String  upload(String fileName ,
                          MultipartFile file , HttpSession session){

//        String title = request.getParameter("title");
//            System.out.println("addResource : " + title);
//            System.out.println("cates : "+cate1+" "+cate2+" "+cate3);
//            System.out.println("video size : "+video.getSize());

            if(!file.isEmpty()){

                String filePath = session.getAttribute("uploadFilePath").toString();
//            String videoUrl = MultipartFileUtils.saveFile(video,filePath);
                String videoUrl = MultipartFileUtils.saveFile(file, "/usr/local/erudition/video");

            }






        return "file/upload";
    }

}
