package com.fis.sdc.checkfile.CONTROLLER;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.Favorite;
import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.MODEL.Share;
import com.fis.sdc.checkfile.SERVICE.*;
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
public class FileFolder {
    @Autowired
    FileService service;
    @Autowired
    AccountService acdao;
//    @Autowired
//    EmailService email;
    @Autowired
    ShareService sdao;

    @Autowired
    FavoriteService fdao;




//    @GetMapping("/downloadfile/{fileid}")
//    public ResponseEntity<ByteArrayResource> downfile(@PathVariable Long fileid){
//        File files = service.findOne(fileid);
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(files.getType())).
//                header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+files.getName()+"\"")
//                .body(new ByteArrayResource(files.getContent()));
//    }

//    @RequestMapping ("/delete/{fileid}")
//    public String deletefile(@PathVariable Long fileid){
//        service.deletebyid(fileid);
//        return "redirect:/folder/"+ Auth.folder;
//    }
//    @RequestMapping ("/favorite/{fileid}")
//    public String favoritefile(Favorite fav, @PathVariable Long fileid){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if(checksao(auth.getName(),fileid) == false){
//
//            File file = service.findOne(fileid);
//            Account acc = acdao.findOne(auth.getName());
//            fav.setUser(acc);
//            fav.setFile(file);
//            fdao.save(fav);
//        }else{
//            Favorite fv = fdao.checksao(auth.getName(), fileid);
//            fdao.deleteById(fv.getIDFR());
//        }
//        return "redirect:/folder/"+ Auth.folder;
//    }
//
//    public Boolean checksao(String username, long IDFile){
//        if(fdao.checksao(username,IDFile) == null){
//            return false;
//        }else{
//            return true;
//        }
//    }
    @PostMapping("/uploadfile2")
    public String uploadfile(@RequestParam("files") MultipartFile[] files){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for(MultipartFile file : files){
            service.saveFile(file,Auth.folder,auth.getName());
        }
        return "redirect:/folder/"+ Auth.folder;
    }

}
