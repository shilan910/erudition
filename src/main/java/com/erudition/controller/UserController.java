package com.erudition.controller;

import com.erudition.bean.CollectionEntity;
import com.erudition.bean.UserEntity;
import com.erudition.dao.CollectionDao;
import com.erudition.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.AssertFalse;
import java.util.List;

/**
 * Created by Administrator on 2016/5/7.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Autowired
    @Qualifier("collectionDao")
    private CollectionDao collectionDao;

    @RequestMapping(value = "/changetoregist", method = RequestMethod.GET)
    public String changeToRegist() {
        return "regist";
    }

    @RequestMapping(value = "/changetologin", method = RequestMethod.GET)
    public String changeToLogin() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)           //调用post方法
    public String login(HttpServletRequest request, String username,
                        String password, String codenum,HttpSession session) {
        int status = 0;
        System.out.println("userController:\nusername:" + username + " password: " + password);
        String usernmaemessage = new String();
        String passwordmessage = new String();
        String codemessage = new String();

        if (username.isEmpty()) {
            usernmaemessage = "请输入用户名";
        } else {
            UserEntity user = userDao.getByName(username);            //暴露出来的接口
            if(user==null){
                usernmaemessage = "不存在的用户！";
            }
            else if (password.isEmpty()) {                //前台直接进行交互，，name="password",,,,
                passwordmessage = "请输入密码";
            } else if (!user.getPassword().equals(password)) {
                passwordmessage = "密码错误";
            }
            else if(!codenum.equalsIgnoreCase(session.getAttribute("code").toString())){
                codemessage = "验证码错误";
            }
            else {
                status = 1;
                usernmaemessage = "用户登陆成功";
                session.setAttribute("loginUser", user);
                System.out.println("message1 : " + usernmaemessage);
                request.getSession().setAttribute("username", username);      //session中设置值


                //登录的时候即将该用户常用目录中的内容加入session中
                List<CollectionEntity> collections = collectionDao.getByUid(user.getId());
                session.setAttribute("usercollections",collections);
                //session.setAttribute("userid",user.getId());
                // redirectAttributes.addAttribute("loginMsg",message);



                System.out.println("message : " + usernmaemessage);
                if(user.getAuthority().equals("1"))return "redirect:/admin/index";
                return "redirect:/index";
            }
        }
        session.setAttribute("usernmaemessage",usernmaemessage);
        session.setAttribute("passwordmessage",passwordmessage);
        session.setAttribute("codemessage",codemessage);

        System.out.println("usernmaemessage : " + usernmaemessage);
        System.out.println("passwordmessage : " + passwordmessage);
        System.out.println("codemessage : " + codemessage);

        session.setAttribute("val", "nihao");
        return "redirect:/login";
    }


    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(HttpSession httpSession, String username, String password, String password2) {
        String reusernmaemessage = new String();
        String repasswordmessage = new String();
        String recodemessage = new String();

        if (username.isEmpty()) {
            reusernmaemessage = "请输入用户名";
        } else if (password.isEmpty()) {
            repasswordmessage = "请输入密码";
        } else {
            if (!password.equals(password2)) {
                recodemessage = "请保持密码和确认密码一致！";
            } else {
                userDao.save(username, password);

                //return "index";
            }
        }
        httpSession.setAttribute("reusernmaemessage",reusernmaemessage);
        httpSession.setAttribute("repasswordmessage",repasswordmessage);
        httpSession.setAttribute("recodemessage",recodemessage);
        httpSession.setAttribute("username",username);

        System.out.println("reusernmaemessage:" + reusernmaemessage);
        System.out.println("repasswordmessage:" + repasswordmessage);
        System.out.println("recodemessage:" + recodemessage);

        return "redirect:/index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/index";
    }

}
