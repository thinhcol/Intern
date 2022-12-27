package com.fis.sdc.checkfile.CONTROLLER.REST;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.Groupes;
import com.fis.sdc.checkfile.SERVICE.AccountService;
import com.fis.sdc.checkfile.SERVICE.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/groupes")
public class GroupRestController {
    @Autowired
    GroupService dao;
    @GetMapping()
    public List<Groupes> index(){
        return dao.findAll();
    }
    @GetMapping("{namegr}")
    public List<Account> all(@PathVariable String namegr){
        return dao.checkus(namegr);
    }
    @GetMapping("/user")
    public List<Groupes> gruser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return dao.findByUser(auth.getName());
    }
}
