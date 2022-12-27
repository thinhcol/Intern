package com.fis.sdc.checkfile.CONTROLLER;

import com.fis.sdc.checkfile.DAO.FileDAO;
import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.SERVICE.FileService;
import com.fis.sdc.checkfile.SERVICE.Impl.FileServiceImpl;
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

import java.util.List;

@Controller
public class Taskfilecontroller {
    @Autowired
    FileService service;

    @GetMapping("/trang1")
    public String get(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<File> doc = service.filetaskclose(false,auth.getName());

        for(File docs : doc){
            if(docs.isTask() == false){
                String madau = "VND.PM_";
                String ngaythang = String.valueOf(docs.getTask_date());
                String ticid = String.valueOf(docs.getTask_id());
                String sb = docs.getName();
                String sl = String.valueOf(service.findsoluongfilecungtask(false,docs.getTask_id(),auth.getName()));
                String tongname = madau+ngaythang+"_"+"Board_"+ticid+"_"+sb+"_"+sl;
                docs.setName(tongname);
            }
        }
        model.addAttribute("docs",doc);
        return "demo1";
    }

//    @GetMapping("/downloadfile1/{fileid}")
//    public ResponseEntity<ByteArrayResource> downfile(@PathVariable Long fileid){
//        File files = service.findOne(fileid);
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(files.getType())).
//                header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+files.getName()+"\"")
//                .body(new ByteArrayResource(files.getContent()));
//    }

    @RequestMapping ("/delete1/{fileid}")
    public String deletefile(@PathVariable Long fileid){
        service.deletebyid(fileid);
        return "redirect:/trang1";
    }

}
