package com.fis.sdc.checkfile.CONTROLLER.REST;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.RoleAcc;
import com.fis.sdc.checkfile.SERVICE.AccountService;
import com.fis.sdc.checkfile.SERVICE.RoleAccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/roleaccs")
public class RoleAccRestController {
    @Autowired
    RoleAccService dao;
    @GetMapping()
    public List<RoleAcc> index(){
        return dao.findAll();
    }
    @GetMapping("/role")
    public RoleAcc all(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return dao.findByAcc(auth.getName());
    }

    @GetMapping("/user")
    public List<Account> user(){
        return dao.findAcc();
    }
}
