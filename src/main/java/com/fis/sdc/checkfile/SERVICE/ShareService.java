package com.fis.sdc.checkfile.SERVICE;

import com.fis.sdc.checkfile.MODEL.*;

import java.util.Date;
import java.util.List;

public interface ShareService {
    List<Share> findAll();
    Share create(Share share);
    Share findOne(long IDShare);
    void deleteById(long IDshare);
    Share savefolder(Share share, Account account, File file);
    int them(String email, File IDFile, Account username, Date dateshare, String folder);
    List<Share> findbydatehis(Date dateshare);
    List<Date> shareduocgui(String email);
    int soluongngaytrung(String username,Date dateshare);
    List<FileZileShare> folderdashare(String username);
    List<Share> filedashare(String username);
    List<FileZileShare> folderduocshare(String email);
    List<Share> fileduocshare(String email);
    int insert(Date dateshare, String email, String cc, Account username, File IDFile);
}
