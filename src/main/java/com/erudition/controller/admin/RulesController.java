package com.erudition.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by sl on 16-6-15.
 */
@Controller
@RequestMapping("/admin/file")
public class RulesController {

    @RequestMapping(value = "/rules", method = RequestMethod.GET)
    public String rules(HttpSession session){
        session.setAttribute("rule_relation",3);
        session.setAttribute("rule_delete",7);
        session.setAttribute("rule_collection",30);
        session.setAttribute("adminSidebarActive",2);
        return "admin/rule";
    }

    @RequestMapping(value = "/rules", method = RequestMethod.POST)
    public String rules(HttpSession session , String rule_relation ,
                        String rule_delete , String rule_collection){

        session.setAttribute("rule_relation",rule_relation);
        session.setAttribute("rule_delete",rule_delete);
        session.setAttribute("rule_collection",rule_collection);
        session.setAttribute("adminSidebarActive",2);
        return "admin/rule";
    }


}
