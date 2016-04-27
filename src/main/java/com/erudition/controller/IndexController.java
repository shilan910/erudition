package com.erudition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sl on 16-4-27.
 */

@Controller
@RequestMapping("/")
public class IndexController {


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {

        System.out.println("IndexController");
        return "index";
    }
}
