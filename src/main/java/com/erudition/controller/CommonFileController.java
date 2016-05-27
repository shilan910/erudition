package com.erudition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tsj on 16-5-27.
 */
@Controller
@RequestMapping("/file")
public class CommonFileController {

    @RequestMapping(value = "/toresult" , method = RequestMethod.GET)
    public String toCommonResult(){
        return "result";
    }
}
