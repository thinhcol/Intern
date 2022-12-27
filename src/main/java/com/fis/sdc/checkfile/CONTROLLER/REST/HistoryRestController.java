package com.fis.sdc.checkfile.CONTROLLER.REST;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.History;
import com.fis.sdc.checkfile.SERVICE.AccountService;
import com.fis.sdc.checkfile.SERVICE.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/histories")
public class HistoryRestController {
    @Autowired
    HistoryService dao;
    @Autowired
    AccountService acdao;
    @GetMapping()
    public Page<History> index(@RequestParam("p") Optional<Integer> p){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account acc = acdao.findOne(auth.getName());
        Pageable pageable = PageRequest.of(p.orElse(0), 7);
        return dao.findtop7his(acc.getUsername(),pageable);
    }
}
