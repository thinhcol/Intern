package com.fis.sdc.checkfile.CONTROLLER;

import com.fis.sdc.checkfile.SERVICE.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DaGanSaoController {
    @Autowired
    FileService dao;
    @GetMapping("/trang4")
    public String get(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("docs",dao.findgansao(auth.getName()));
        return "demo4";
    }

}
