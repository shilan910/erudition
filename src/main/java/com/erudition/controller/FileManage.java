package com.erudition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/5/13.
 */
@Controller
@RequestMapping("/admin/filecollect")
public class FileManage {
    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public String file(HttpSession httpSession){
        return "admin/file-collect";    //次处填jsp页面
    }
}