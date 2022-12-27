package com.fis.sdc.checkfile.SERVICE;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.Filehd;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FilehdService {
    List<Filehd> findAll();
    Filehd create(MultipartFile file) throws IOException;
    Filehd findOne(int file);
}
