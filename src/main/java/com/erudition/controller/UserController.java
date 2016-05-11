package com.erudition.controller;

import com.erudition.bean.UserEntity;
import com.erudition.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/5/7.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @RequestMapping(value = "/changetoregist", method = RequestMethod.GET)
    public String changeToRegist() {
        return "regist";
    }

    @RequestMapping(value = "/changetologin", method = RequestMethod.GET)
    public String changeToLogin() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)           //调用post方法
    public String login(HttpServletRequest request, String username,
                        String password, String codenum,HttpSession session) {
        int status = 0;
        System.out.println("1111111111111111111111" + username + "  " + password);
        String message = new String();

        if (username.isEmpty()) {
            message = "请输入用户名";
        } else {
            UserEntity user = userDao.getByName(username);            //暴露出来的接口
            if (password.isEmpty()) {                //前台直接进行交互，，name="password",,,,
                message = "请输入密码";
            } else if (!user.getPassword().equals(password)) {
                message = "密码错误";
            }
            else if(!codenum.equalsIgnoreCase(session.getAttribute("code").toString())){
                message = "验证码错误";
            }
            else {
                status = 1;
                message = "用户登陆成功";
                session.setAttribute("loginUser", user);
                System.out.println("message1 : " + message);
                request.getSession().setAttribute("username", username);      //session中设置值
                //session.setAttribute("userid",user.getId());
                // redirectAttributes.addAttribute("loginMsg",message);

                System.out.println("message : " + message);
                return "index";
            }
        }
        System.out.println("message : " + message);
        session.setAttribute("val", "nihao");
        return "redirect:/login";
    }


    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(HttpSession httpSession, String username, String password, String password2) {
        String message = new String();

        if (username.isEmpty()) {
            message = "请输入用户名";
        } else if (password.isEmpty()) {
            message = "请输入密码";
        } else {
            if (!password.equals(password2)) {
                message = "请保持密码和确认密码一致！";
            } else {
                userDao.save(username, password);
                message = "注册成功！";
                return "index";
            }
        }
        System.out.println("message:" + message);
        return "redirect:/regist";
    }

}
