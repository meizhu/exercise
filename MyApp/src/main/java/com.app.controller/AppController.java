package com.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/**")
public class AppController {

    @RequestMapping(method = RequestMethod.GET)
    public String myApp(ModelMap model) {
        model.addAttribute("message", "hello world");
        return "app";
    }
}

