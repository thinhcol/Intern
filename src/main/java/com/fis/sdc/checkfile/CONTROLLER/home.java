package com.fis.sdc.checkfile.CONTROLLER;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class home {
    @RequestMapping({"/admin","/"})
    public String index(){
        return "redirect:/admin/demo.html";
    }
}
