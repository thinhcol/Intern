package com.fis.sdc.checkfile.CONTROLLER;

import com.fis.sdc.checkfile.DAO.FileDAO;
import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.SERVICE.AccountService;
import com.fis.sdc.checkfile.SERVICE.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DuocChiaSeController {
    @Autowired
    FileService dao;
    @Autowired
    AccountService service;
    @GetMapping("/trang3")
    public String get(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account acc = service.findOne(auth.getName());
        model.addAttribute("docs",dao.finduocgui(acc.getEmail()));
        return "demo3";
    }
}
