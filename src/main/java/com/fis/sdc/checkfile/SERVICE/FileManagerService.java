package com.fis.sdc.checkfile.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManagerService {
    @Autowired
    ServletContext app;
    private Path getPath(String filename) {
        String folder = "C:\\filehuongdan\\";
        File dir = Paths.get(folder).toFile();
        if(!dir.exists()) {
            dir.mkdirs();
        }
        return Paths.get(dir.getAbsolutePath(),filename);
    }

    public byte[] read(String filename) {
        Path path = this.getPath(filename);
        try {
            return Files.readAllBytes(path);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> save(MultipartFile[] files){
        List<String> filenames = new ArrayList<String>();
        for(MultipartFile file : files) {
            String name = System.currentTimeMillis() + file.getOriginalFilename();
            String filename = Integer.toHexString(name.hashCode()) + name.substring(name.lastIndexOf("."));
            Path path = this.getPath(filename);
            try {
                file.transferTo(path);
                filenames.add(filename);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        return filenames;
    }


    public List<String> list(){
        String folder = "C:\\filehuongdan\\";
        List<String> filenames = new ArrayList<String>();
        File dir = Paths.get(folder).toFile();
        if(dir.exists()) {
            File[] files = dir.listFiles();
            for(File file: files) {
                filenames.add(file.getName());
            }
        }
        return filenames;
    }
}
