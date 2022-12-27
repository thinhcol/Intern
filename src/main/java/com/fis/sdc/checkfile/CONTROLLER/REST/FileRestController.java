package com.fis.sdc.checkfile.CONTROLLER.REST;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.MODEL.FileZile;
import com.fis.sdc.checkfile.MODEL.History;
import com.fis.sdc.checkfile.SERVICE.AccountService;
import com.fis.sdc.checkfile.SERVICE.FileService;
import com.fis.sdc.checkfile.SERVICE.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/files")
public class FileRestController {
    @Autowired
    FileService service;
    @Autowired
    AccountService acdao;
    @Autowired
    HistoryService hisdao;

    @GetMapping
    public List<File> getAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<File> doc1 = service.filetaskclose(false, auth.getName());
//        for (File docs : doc1) {
//            if (docs.isTask() == false) {
//                String madau = "VND.PM_";
//                String ngaythang = String.valueOf(docs.getTask_date());
//                String ticid = String.valueOf(docs.getTask_id());
//                String sb = docs.getName();
//                String sl = String.valueOf(service.findsoluongfilecungtask(false, docs.getTask_id(), auth.getName()));
//                String tongname = madau + ngaythang + "_" + "Board_" + ticid + "_" + sb + "_" + sl;
//                docs.setName(tongname);
//            }
//        }
        return doc1;
    }

    @GetMapping("tickfolder")
    public List<FileZile> folder() {
        return service.ticketfile();
    }

    @GetMapping("tickfile")
    public List<File> filetick() {
        return service.fileticket();
    }

    @GetMapping("taskfile")
    public List<File> filetask() {
        List<File> file = service.filetask();

        return service.filetask();
    }

    @GetMapping("tackfolder")
    public List<FileZile> tackfolder() {
        return service.taskfile();
    }

    @PostMapping()
    public String uploadfile(@RequestParam("files") MultipartFile file, @ModelAttribute File files) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date dateticket = new Date(stamp.getTime());
        files.setTicket_date(dateticket);
        DateFormat f1 = new SimpleDateFormat("dd.MM.yyyy");
        String dticket = f1.format(dateticket);

        Date datetask = new Date(stamp.getTime());
        files.setTask_date(datetask);
        DateFormat f2 = new SimpleDateFormat("dd.MM.yyyy");
        String dtask = f2.format(datetask);


        String str = file.getOriginalFilename();
        String namestt = str.substring(0, str.indexOf('.'));
        String duoi = str.substring(str.lastIndexOf('.') + 0).trim();
        String path = "";
        String name = "";
        if (files.isTask() == false) {
            files.setTask_name(service.getMaxTask(files.getTask_id()) + 1);
            path = "C:\\WF2\\";
            name = files.getIdsite() + "." + files.getIdpb() + "_" + dtask + "_" + files.getTask_id() + "_" + namestt + "_" + files.getTask_name() + duoi;
            files.setNametaskfile(name);
        }
        if (files.isTicket() == false) {
            files.setTicket_name(service.getMaxTicket(files.getTicket_id()) + 1);
            path = "C:\\WF1\\";
            name = files.getIdphanhe() + files.getIdquytrinh() + "_" + dticket + "_" + files.getTicket_id() + "_" + namestt + "_" + files.getTicket_name() + duoi;
            files.setNameticketfile(name);
        }
        System.out.println(name);
        files.setName(file.getOriginalFilename());
        files.setSize(file.getSize());
        files.setType(file.getContentType());

//        for(MultipartFile file : files){
        Files.copy(file.getInputStream(), Paths.get(path + java.io.File.separator + name), StandardCopyOption.REPLACE_EXISTING);
        service.saveFolder(file, auth.getName(), files, path, name);
//        }

        return "Successfully";
    }

    @GetMapping("/downloadfile/{IDFile}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable long IDFile) throws IOException {

        File file = service.findOne(IDFile);
        java.io.File fi = new java.io.File("C:\\FPT\\JAVA6\\spro-logfile\\checkfile\\src\\main\\resources\\static\\SaveFile\\" + file.getName());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getName() + "\"")
                .body(new ByteArrayResource(Files.readAllBytes(fi.toPath())));
    }

    @GetMapping("/openfolder/{namegroup}")
    public List<File> openfolder(@PathVariable String namegroup) throws IOException {
        return service.filefolder(namegroup);
    }

    @GetMapping("/nofolder")
    public List<File> nofolder() throws IOException {
        return service.nofolder();
    }

    @GetMapping("/readfileticket/{IDFile}")
    public ResponseEntity<ByteArrayResource> readfileticket(@PathVariable long IDFile) throws IOException {

        File file = service.findOne(IDFile);
        java.io.File fi = new java.io.File("C:\\WF1\\" + file.getNameticketfile());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        File files = service.findOne(IDFile);
        Account acc = acdao.findOne(auth.getName());
        History lichsu = hisdao.findbytkanid(acc.getUsername(), IDFile);
        if (files.getType().equals("text/plain") || files.getType().equals("application/pdf") || files.getType().equals("audio/mpeg") ||
                files.getType().equals("image/jpeg") || files.getType().equals("image/png")) {
            if (lichsu != null) {
                Date date = new Date();
                hisdao.capnhatthoigian(new Timestamp(date.getTime()), acc, files);
                System.out.println(files.getIDFile());
                System.out.println(files.getIDFile());
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(files.getType()))
                        .body(new ByteArrayResource(Files.readAllBytes(fi.toPath())));
            } else {
                Date date = new Date();
                hisdao.insert(new Timestamp(date.getTime()), acc, files);
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(files.getType()))
                        .body(new ByteArrayResource(Files.readAllBytes(fi.toPath())));
            }
        } else {
            System.out.println(files.getType());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(files.getType())).
                    header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + files.getName() + "\"")
                    .body(new ByteArrayResource(Files.readAllBytes(fi.toPath())));
        }
    }

    @GetMapping("/readfiletask/{IDFile}")
    public ResponseEntity<ByteArrayResource> readfiletask(@PathVariable long IDFile) throws IOException {

        File file = service.findOne(IDFile);
        java.io.File fi = new java.io.File("C:\\WF2\\" + file.getNametaskfile());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        File files = service.findOne(IDFile);
        Account acc = acdao.findOne(auth.getName());
        History lichsu = hisdao.findbytkanid(acc.getUsername(), IDFile);
        if (files.getType().equals("text/plain") || files.getType().equals("application/pdf") || files.getType().equals("audio/mpeg") ||
                files.getType().equals("image/jpeg") || files.getType().equals("image/png")) {
            if (lichsu != null) {
                Date date = new Date();
                hisdao.capnhatthoigian(new Timestamp(date.getTime()), acc, files);
                System.out.println(files.getIDFile());
                System.out.println(files.getIDFile());
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(files.getType()))
                        .body(new ByteArrayResource(Files.readAllBytes(fi.toPath())));
            } else {
                Date date = new Date();
                hisdao.insert(new Timestamp(date.getTime()), acc, files);
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(files.getType()))
                        .body(new ByteArrayResource(Files.readAllBytes(fi.toPath())));

            }
        } else {
            System.out.println(files.getType());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(files.getType())).
                    header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + files.getName() + "\"")
                    .body(new ByteArrayResource(Files.readAllBytes(fi.toPath())));
        }
    }
    @GetMapping("/downfileticket/{IDFile}")
    public ResponseEntity<ByteArrayResource> downloadfileticket(@PathVariable long IDFile) throws IOException {
        File file = service.findOne(IDFile);
        java.io.File fi = new java.io.File("C:\\WF1\\" + file.getNameticketfile());
        File files = service.findOne(IDFile);
        System.out.println(files.getType());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(files.getType())).
                header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + files.getName() + "\"")
                .body(new ByteArrayResource(Files.readAllBytes(fi.toPath())));

    }
    @GetMapping("/downfiletask/{IDFile}")
    public ResponseEntity<ByteArrayResource> downloadfiletask(@PathVariable long IDFile) throws IOException {
        File file = service.findOne(IDFile);
        java.io.File fi = new java.io.File("C:\\WF2\\" + file.getNametaskfile());
        File files = service.findOne(IDFile);
        System.out.println(files.getType());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(files.getType())).
                header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + files.getName() + "\"")
                .body(new ByteArrayResource(Files.readAllBytes(fi.toPath())));

    }
}
