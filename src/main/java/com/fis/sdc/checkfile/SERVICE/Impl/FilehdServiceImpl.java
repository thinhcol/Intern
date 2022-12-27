package com.fis.sdc.checkfile.SERVICE.Impl;

import com.fis.sdc.checkfile.DAO.DecentralizationDAO;
import com.fis.sdc.checkfile.DAO.FilehdDAO;
import com.fis.sdc.checkfile.MODEL.*;
import com.fis.sdc.checkfile.SERVICE.DecentralizationService;
import com.fis.sdc.checkfile.SERVICE.FilehdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FilehdServiceImpl implements FilehdService {
    @Autowired
    private FilehdDAO dao;
    @Override
    public List<Filehd> findAll() {
        return dao.findAll();
    }

    @Override
    public Filehd create(MultipartFile file) throws IOException {
        if(file.getContentType().equals("application/pdf")){
            String docname = file.getOriginalFilename();
            Filehd filehd = new Filehd(docname, file.getSize(), file.getContentType());
            savefilestoge(file);
            return dao.save(filehd);
        }else{
            return null;
        }

    }

    @Override
    public Filehd findOne(int file) {
        return dao.findById(file).get();
    }

    public void savefilestoge(MultipartFile file) throws IOException {
        String folder = "C:\\filehuongdan\\";
        byte[] bytes = file.getBytes();
        Path path = Paths.get(folder + file.getOriginalFilename());
        Files.write(path, bytes);
    }

}
