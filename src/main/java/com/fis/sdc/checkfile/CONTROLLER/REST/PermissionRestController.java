package com.fis.sdc.checkfile.CONTROLLER.REST;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.permission;
import com.fis.sdc.checkfile.SERVICE.AccountService;
import com.fis.sdc.checkfile.SERVICE.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/permission")
public class PermissionRestController {
    @Autowired
    PermissionService dao;
    @GetMapping()
    public List<permission> index(){
        return dao.findAll();
    }
    @GetMapping("{idprm}")
    public permission all(@PathVariable int idprm){
        return dao.findOne(idprm);
    }
}
