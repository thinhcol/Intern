package com.fis.sdc.checkfile.CONTROLLER;

import com.fis.sdc.checkfile.MODEL.*;
import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.SERVICE.*;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zeroturnaround.zip.commons.FileUtils;


import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class testfilecontroller {

    @Autowired
    AccountService acdao;
//    @Autowired
//    EmailService email;
    @Autowired
    ShareService sdao;
    @Autowired
    FileService dao;
    @Autowired
    FavoriteService fdao;
    @Autowired
    GroupService grdao;
    @Autowired
    HistoryService hisdao;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/model/admin")
    public String get(Model model, @RequestParam("p") Optional<Integer> p) {
        try {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Account acc = acdao.findOne(auth.getName());
            Pageable pageable = PageRequest.of(p.orElse(0), 7);
            model.addAttribute("history", hisdao.findtop7his(acc.getUsername(), pageable));
//            model.addAttribute("docs", dao.sizefile(acc.getUsername()));
            model.addAttribute("docs2folder", sdao.folderdashare(acc.getUsername()));
            model.addAttribute("docs2", sdao.filedashare(acc.getUsername()));
            model.addAttribute("docs3folder", sdao.folderduocshare(acc.getEmail()));
            model.addAttribute("docs3", sdao.fileduocshare(acc.getEmail()));
            model.addAttribute("gmail",acdao.findAll());
            model.addAttribute("acc",acc.getUsername());
            List<File> doc1 = dao.filetaskclose(false, auth.getName());
            for (File docs : doc1) {
                if (docs.isTask() == false) {
                    String madau = "VND.PM_";
                    String ngaythang = String.valueOf(docs.getTask_date());
                    String ticid = String.valueOf(docs.getTask_id());
                    String sb = docs.getName();
                    String sl = String.valueOf(dao.findsoluongfilecungtask(false, docs.getTask_id(), auth.getName()));
                    String tongname = madau + ngaythang + "_" + "Board_" + ticid + "_" + sb + "_" + sl;
                    docs.setName(tongname);
                }
            }
            model.addAttribute("docs1", doc1);


            model.addAttribute("docs4", dao.findgansao(auth.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "demo";
    }

//    @PostMapping("/uploadfile")
//    public String uploadfile(@RequestParam("files") MultipartFile[] files) throws IOException {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        for (MultipartFile file : files) {
//            dao.saveFolder(file, auth.getName());
////            savefolderstoge(file);
//        }
//        return "redirect:/";
//    }

    @PostMapping("/guifile/{folder}")
    public String guifile(Share share, @PathVariable String folder) {
        Date date = new Date();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account acc = acdao.findOne(auth.getName());
//        share.setUser(acc);
        share.setDateshare(new Timestamp(date.getTime()));
        List<File> files = dao.findByFolder(folder, auth.getName());

        return "redirect:/";
    }

    @RequestMapping("/delete/{fileid}")
    public String deletefile(@PathVariable Long fileid) {
        dao.deletebyid(fileid);
        return "redirect:/";
    }

    @RequestMapping("/favorite/{fileid}")
    public String favoritefile(Favorite fav, @PathVariable Long fileid) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (checksao(auth.getName(), fileid) == false) {

            File file = dao.findOne(fileid);
            Account acc = acdao.findOne(auth.getName());
            fav.setUser(acc);
            fav.setFile(file);
            fdao.save(fav);
        } else {
            Favorite fv = fdao.checksao(auth.getName(), fileid);
            fdao.deleteById(fv.getIDFR());
        }
        return "redirect:/";
    }

    public Boolean checksao(String username, long IDFile) {
        if (fdao.checksao(username, IDFile) == null) {
            return false;
        } else {
            return true;
        }
    }

    @PostMapping("/sendmailall/{fileid}")
    public String sendfile(@PathVariable Long fileid, Share share,@RequestParam("sendmail") String sendmail) {
        if (sendmail.contains(",") == false) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            File file = dao.findOne(fileid);
            Account acc = acdao.findOne(auth.getName());
                share.setUser(acc);
                share.setFile(file);
                share.setEmail(sendmail);
                sdao.create(share);
            }
        else {
            System.out.println("Check 123 "+sendmail);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            File file = dao.findOne(fileid);
            Account acc = acdao.findOne(auth.getName());
            String[] chuoi = sendmail.split(",");
            for(String email : chuoi){
                Date date = new Date();
                date = new Timestamp(date.getTime());
                sdao.insert(date,email,"",acc,file);

            }

        }

        return "redirect:/";
    }

    @RequestMapping("/sharegr/{fileid}")
    public String sharegr(@PathVariable Long fileid, Groupes gr) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        File file = dao.findOne(fileid);
        Account acc = acdao.findOne(auth.getName());
        gr.setUser(acc);
        gr.setFile(file);
        grdao.create(gr);
        return "redirect:/";
    }

    @RequestMapping("/foldershare/{folder}")
    public String foldershare(Model model, @PathVariable String folder) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Auth.folder = folder;
        model.addAttribute("docs", dao.filefolder(folder));
        model.addAttribute("folder", folder);
        return "demofile";
    }

    @RequestMapping("/folder/{folder}")
    public String folder(Model model, @PathVariable String folder) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Auth.folder = folder;
        model.addAttribute("docs", dao.findByFolder(folder, auth.getName()));
        model.addAttribute("folder", folder);
        return "demofile";
    }

    //    @RequestMapping("/foldertozip")
//    public String file(@RequestParam("check1") String ad){
//        System.out.println(ad);
//        try {
//                zip(convert(dao.filefolder(ad)));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return "redirect:/";
//    }
    public void zip(java.io.File[] path) {

        try {
            java.io.File[] files = path;
            FileOutputStream fos = new FileOutputStream("C:/FPT/thinh.zip");
            ZipOutputStream zipout = new ZipOutputStream(fos);
            if (files == null) {
                System.out.println(path);
            } else {
                for (java.io.File zipthis : files) {
                    FileInputStream fis = new FileInputStream(zipthis);
                    ZipEntry zipEntry = new ZipEntry(zipthis.getName());
                    zipout.putNextEntry(zipEntry);
                    byte[] bytes = new byte[2048];
                    int length;
                    while ((length = fis.read(bytes)) >= 0) {
                        zipout.write(bytes, 0, length);
                    }
                    fis.close();
                }
                zipout.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void savefolderstoge(MultipartFile file, String foldername) throws IOException {
//        Path filepath = Paths.get("C:\\FPT\\JAVA6\\spro-logfile\\checkfile\\src\\main\\resources\\static\\SaveFile\\", file.getOriginalFilename());
//        try (OutputStream os = Files.newOutputStream(filepath)) {
//            os.write(file.getBytes());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        String folder = "C:\\FPT\\JAVA6\\spro-logfile\\checkfile\\src\\main\\resources\\static\\SaveFile\\" + foldername + "\\";
        java.io.File crtfolder = new java.io.File(folder);
        crtfolder.mkdirs();
        String fileName = file.getOriginalFilename();
        java.io.File executeFile = new java.io.File(fileName);
        FileUtil.deleteContents(executeFile);
        file.transferTo(executeFile);
        System.out.println(executeFile.getAbsolutePath());
        FileUtils.copyDirectory(executeFile, crtfolder);
    }



}
