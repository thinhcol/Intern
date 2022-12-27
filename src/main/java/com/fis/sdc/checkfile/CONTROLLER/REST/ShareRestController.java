package com.fis.sdc.checkfile.CONTROLLER.REST;

import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.MODEL.FileZile;
import com.fis.sdc.checkfile.MODEL.FileZileShare;
import com.fis.sdc.checkfile.MODEL.Share;
import com.fis.sdc.checkfile.SERVICE.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/rest/shares")
public class ShareRestController {
    @Autowired
    ShareService shareService;
    @GetMapping("fileda")
    public List<Share> filedashare()  {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return shareService.filedashare(auth.getName());
    }
    @GetMapping("fileduoc")
    public List<Share> fileduocshare()  {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return shareService.fileduocshare(auth.getName());
    }
    @GetMapping("folderda")
    public List<FileZileShare> fodlerdashare()  {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return shareService.folderdashare(auth.getName());
    }
    @GetMapping("folderduoc")
    public List<FileZileShare> fodlerduocshare()  {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return shareService.folderduocshare(auth.getName());
    }
    @PostMapping()
    public int chiase(@RequestBody Share sr)  {
        return shareService.them(sr.getEmail(),sr.getFile(),sr.getUser(),sr.getDateshare(),sr.getFolder());
    }
}
