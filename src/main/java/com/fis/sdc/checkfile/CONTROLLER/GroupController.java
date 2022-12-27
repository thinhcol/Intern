package com.fis.sdc.checkfile.CONTROLLER;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.Groupes;
import com.fis.sdc.checkfile.SERVICE.AccountService;
import com.fis.sdc.checkfile.SERVICE.FileService;
import com.fis.sdc.checkfile.SERVICE.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GroupController {
    @Autowired
    GroupService service;
    @Autowired
    AccountService accdao;
    @Autowired
    FileService fdao;
    @RequestMapping("/trang6")
    public String index(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("gr",service.findByUser(auth.getName()));
        return "demo6";
    }

    @RequestMapping("/savegr")
    public String savegr(Groupes gr, @RequestParam String email){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(checkgroup(auth.getName(),gr.getName()) == false){
            Account acc = accdao.findOne(auth.getName());
            gr.setUser(acc);
            service.create(gr);
        }else{
            Account acc = accdao.findByEmail(email);
            gr.setUser(acc);
            service.create(gr);
        }


        return "redirect:/trang6";
    }

    public boolean checkgroup(String username,String namegr){
        if(service.checkgr(username,namegr) == null){
            return false;
        }else{
            return true;
        }
    }

}
