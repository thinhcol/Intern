package com.fis.sdc.checkfile.CONTROLLER;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.MODEL.Groupes;
import com.fis.sdc.checkfile.SERVICE.AccountService;
import com.fis.sdc.checkfile.SERVICE.FileService;
import com.fis.sdc.checkfile.SERVICE.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GroupFile {
    @Autowired
    FileService service;
    @Autowired
    GroupService grdao;

    @Autowired
    AccountService accservice;
    private static String name;
    @RequestMapping("/group/{namegr}")
    public String index(Model model, @PathVariable String namegr){
        model.addAttribute("docs",service.FileGroup(namegr));
        name = namegr;
        return "demo7";
    }
//    @GetMapping("/downloadfilegr/{fileid}")
//    public ResponseEntity<ByteArrayResource> downfile(@PathVariable Long fileid){
//        File files = service.findOne(fileid);
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(files.getType())).
//                header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+files.getName()+"\"")
//                .body(new ByteArrayResource(files.getContent()));
//    }
    @PostMapping("/uploadfilegr")
    public String uploadfile(@RequestParam("files") MultipartFile[] files, Groupes gr){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account acc = accservice.findOne(auth.getName());
        for(MultipartFile file : files){
            gr.setFile(service.saveFile(file,"",auth.getName()));
            gr.setName(name);
            gr.setUser(acc);
            grdao.create(gr);
        }
        return "redirect:/group/"+ name;
    }
}
