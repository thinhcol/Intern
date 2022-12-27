package com.fis.sdc.checkfile.CONTROLLER;

import com.fis.sdc.checkfile.DAO.FileDAO;
import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.SERVICE.AccountService;
import com.fis.sdc.checkfile.SERVICE.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

@Controller
public class SoSanhFile {
    @Autowired
    FileService dao;
    @Autowired
    AccountService service;

    @RequestMapping("/sosanh")
    public String uploadfile(Model model,@RequestParam("myFile") MultipartFile file1,@RequestParam("myFile1") MultipartFile file2){
        try {
            java.io.File filex = convert(file1);
            java.io.File filey = convert(file2);


            if(compare(filex,filey)){
                model.addAttribute("mess","2 file tương đương nhau");
                System.out.println("2 file tương đương nhau");
            }else{
                model.addAttribute("mess","2 file khác nhau");
                System.out.println("2 file khác nhau");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }
    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getResource().getDescription());
        convFile.createNewFile();
        BufferedReader input =  new BufferedReader(new FileReader(convFile));
        input.read();
        input.close();

        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
    public static boolean compare(File path1, File path2) throws IOException {
        try (RandomAccessFile randomAccessFile1 = new RandomAccessFile(path1, "r");
             RandomAccessFile randomAccessFile2 = new RandomAccessFile(path2, "r")) {

            FileChannel ch1 = randomAccessFile1.getChannel();
            FileChannel ch2 = randomAccessFile2.getChannel();
            if (ch1.size() != ch2.size()) {
                return false;
            }
            long size = ch1.size();
            MappedByteBuffer m1 = ch1.map(FileChannel.MapMode.READ_ONLY, 0L, size);
            MappedByteBuffer m2 = ch2.map(FileChannel.MapMode.READ_ONLY, 0L, size);
            return m1.equals(m2);
        }
    }
}
