package com.fis.sdc.checkfile.CONTROLLER.REST;

import com.fis.sdc.checkfile.MODEL.Filehd;
import com.fis.sdc.checkfile.SERVICE.FilehdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/filehd")
public class FilehdRestController {
    @Autowired
    FilehdService dao;

    @GetMapping()
    public List<Filehd> index() {
        return dao.findAll();
    }

    @GetMapping("findone/{id}")
    public Filehd findone(@PathVariable("id") int id) {
        return dao.findOne(id);
    }

    @PostMapping("save")
    public void uploadfile(@RequestParam("files") MultipartFile myFile) throws IOException {
        String path = "C:\\filehuongdan\\";
        Files.copy(myFile.getInputStream(), Paths.get(path + java.io.File.separator + myFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

        dao.create(myFile);
    }

    @GetMapping("/readfilehd/{IDFile}")
    public ResponseEntity<ByteArrayResource> readfile(@PathVariable int IDFile) throws IOException {
        Filehd file = dao.findOne(IDFile);
        java.io.File fi = new java.io.File("C:\\filehuongdan\\" + file.getName());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getType()))
                .body(new ByteArrayResource(Files.readAllBytes(fi.toPath())));


    }

}
