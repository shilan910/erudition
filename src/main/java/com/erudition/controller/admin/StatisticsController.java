package com.erudition.controller.admin;

import com.erudition.entity.Statistics;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by sl on 16-8-21.
 */
@Controller
@RequestMapping("/admin/statistics")
public class StatisticsController {

    public List<Statistics> getDataByWeek(){
            return  null;
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String test(HttpSession session){
        session.setAttribute("adminSidebarActive",3);
        return "admin/statistics";
    }

}
