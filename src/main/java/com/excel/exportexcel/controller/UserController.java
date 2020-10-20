package com.excel.exportexcel.controller;

import com.excel.exportexcel.model.UserDetails;
import com.excel.exportexcel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute UserDetails user) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.validateUser(user);
        } catch (Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("username", user.getName());
            modelAndView.addObject("msg", e.getMessage());
            return modelAndView;
        }
        modelAndView.setViewName("export");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
    public ModelAndView createUser(UserDetails user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        UserDetails userDetails = null;
        try {
            userDetails = userService.saveUser(user);
        } catch (Exception e) {
            model.setViewName("error");
            model.addObject("username", user.getName());
            model.addObject("msg", e.getMessage());
            return model;
        }
        model.addObject("msg", "User has been registered successfully!");
        model.addObject("user", userDetails);
        model.setViewName("user-data");
        return model;
    }
}
