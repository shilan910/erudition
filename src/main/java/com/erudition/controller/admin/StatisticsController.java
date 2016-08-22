package com.erudition.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/8/22.
 */
@Controller
@RequestMapping("/admin/file")
public class StatisticsController {
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String test(HttpSession session){
        return "admin/statistics";
    }
}
