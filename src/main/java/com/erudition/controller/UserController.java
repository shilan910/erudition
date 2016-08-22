package com.erudition.controller;

import com.erudition.bean.CollectionEntity;
import com.erudition.bean.FilesEntity;
import com.erudition.bean.LogEntity;
import com.erudition.bean.UserEntity;
import com.erudition.dao.*;
import com.erudition.util.GlobalVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    @Qualifier("logDao")
    private LogDao logDao;

    @Autowired
    @Qualifier("configDao")
    private ConfigDao configDao;

    @Autowired
    @Qualifier("collectionDao")
    private CollectionDao collectionDao;

    @Autowired
    @Qualifier("resourcesDao")
    private ResourcesDao resourcesDao;

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
                session.setAttribute("userid",user.getId());
                session.setAttribute("rule_relation",configDao.getByKey("rule_relation"));
                session.setAttribute("rule_delete", configDao.getByKey("rule_delete"));
                session.setAttribute("rule_collection", configDao.getByKey("rule_collection"));
                session.setAttribute("adminSidebarActive", configDao.getByKey("adminSidebarActive"));

                System.out.println("message1 : " + usernmaemessage);
                request.getSession().setAttribute("username", username);      //session中设置值


                //登录的时候即将该用户常用目录中的内容加入session中
//                List<FilesEntity>files = new ArrayList<FilesEntity>();
////                List<CollectionEntity> collections = collectionDao.getByUid(user.getId());
////                for(CollectionEntity collectionEntity:collections){
////                    files.add(resourcesDao.getById(collectionEntity.getFileId()));
////                }
//                session.setAttribute("usercollections",files);
                session.setAttribute("userid",user.getId());
                // redirectAttributes.addAttribute("loginMsg",message);


                deleteTimeOut();
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
                UserEntity testuser = userDao.getByName(username);
                if(testuser==null){
                    userDao.save(username, password);
                    httpSession.setAttribute("loginUser",userDao.getByName(username));
                    System.out.println("UserController : "+username);
                    httpSession.setAttribute("username",username);
                    return "redirect:/index";
                } else{
                    reusernmaemessage = "已经存在的用户名！";
                }


                //return "index";
            }
        }
        httpSession.setAttribute("reusernmaemessage",reusernmaemessage);
        httpSession.setAttribute("repasswordmessage",repasswordmessage);
        httpSession.setAttribute("recodemessage",recodemessage);
        System.out.println("UserController : "+username);
        httpSession.setAttribute("username",username);

        System.out.println("reusernmaemessage:" + reusernmaemessage);
        System.out.println("repasswordmessage:" + repasswordmessage);
        System.out.println("recodemessage:" + recodemessage);

        return "redirect:/user/changetoregist";
//        return "redirect:/index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/index";
    }


    public void deleteTimeOut(){
        List<FilesEntity> files = resourcesDao.getAllFiles();
        List<FilesEntity> filesToDelete = new ArrayList<>();

        for(FilesEntity f:files){
            String originaltime = f.getCreateTime().toString();
            System.out.println("originaltime:   "+originaltime);
            Date date=new Date();
            DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currenttime=format.format(date);
            int real_delete = daysBetween(originaltime,currenttime);
            int rule_delete = Integer.parseInt(configDao.getByKey("rule_delete"));

            System.out.println("zqh:test:fileid="+f.getId());


            if (real_delete > rule_delete ){
                if(f.getRelations()==null){
                    resourcesDao.delete(f);
                }else{
                    String[] relations = f.getRelations().split(",");

                    for(String relation : relations){
                        if(!relation.equals("") && relation!=null){
                            FilesEntity fileRela = resourcesDao.getById(Integer.valueOf(relation));
                            String[] res=null;
                            String newRes = "";
                            String fileRela_rela = fileRela.getRelations();

                            System.out.println("zqh:test:relation="+relation);

                            System.out.println("zqh:test:fileRela_rela="+fileRela_rela);

                            if(fileRela_rela.indexOf(",")!=-1){
                                res = fileRela_rela.split(",");
                                for(String re : res){
                                    System.out.println("zqh:test:re="+re);

                                    if(Integer.valueOf(re)!=f.getId()){
                                        if(!newRes.equals("")){
                                            newRes += ",";
                                        }
                                        newRes += re;
                                    }
                                }
                            }else if(!fileRela_rela.equals("") && Integer.valueOf(fileRela.getRelations())!=f.getId()){
                                newRes = fileRela.getRelations();
                            }

                            System.out.println("zqh:test:newRes="+newRes);

                            fileRela.setRelations(newRes);
                            resourcesDao.update(fileRela);
                        }
                    }

                    resourcesDao.delete(f);
                }

                filesToDelete.add(f);

                logDao.deteleLogByFileId(f.getId());

                collectionDao.deleteByFid(f.getId());
            }
        }


        System.out.println("删除超期文件！！！！！！！！！！！");
    }


    public static int daysBetween(String smdate,String bdate){
        long between_days = 0;
       try{
           SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
           Calendar cal = Calendar.getInstance();
           cal.setTime(sdf.parse(smdate));
           long time1 = cal.getTimeInMillis();
           cal.setTime(sdf.parse(bdate));
           long time2 = cal.getTimeInMillis();
           between_days=(time2-time1)/(1000*3600*24);


       }catch (Exception e){
           System.out.println(e.getStackTrace());
       }
        return Integer.parseInt(String.valueOf(between_days));
    }


}
