package com.erudition.controller;

import com.erudition.bean.CategoryEntity;
import com.erudition.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/7.
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpSession httpSession) {
        System.out.println("LoginController");
        return "login";
    }
}
