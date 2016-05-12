package com.erudition.controller.admin;

import com.erudition.bean.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by sl on 16-5-12.
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController {



    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpSession httpSession){

        String username = (String)httpSession.getAttribute("username");
        if(username==null) return "login";
        else{
            UserEntity user = (UserEntity)httpSession.getAttribute("loginUser");
            if(user.getAuthority().equals("0"))return "redirect:/index";
        }
        return "admin/index";
    }
}
