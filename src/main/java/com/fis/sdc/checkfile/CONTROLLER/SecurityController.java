package com.fis.sdc.checkfile.CONTROLLER;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
    @RequestMapping("/logout")
    public String index() {
        return "demo";
    }
}
