package com.fis.sdc.checkfile.CONTROLLER.REST;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.SERVICE.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/account")
public class AccountRestController {
    @Autowired
    AccountService accdao;
    @GetMapping()
    public Account index(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return accdao.findOne(auth.getName());
    }
    @GetMapping("/all")
    public List<Account> all(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return accdao.findAll();
    }

    @GetMapping("/email/{username}")
    public List<String> email(@PathVariable String username){
        return accdao.findEmail(username);
    }

    @GetMapping("/user")
    public List<String> user(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return accdao.finduser(auth.getName());
    }
}
