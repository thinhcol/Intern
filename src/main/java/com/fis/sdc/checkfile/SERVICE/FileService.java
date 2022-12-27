package com.fis.sdc.checkfile.SERVICE;

import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.MODEL.FileZile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FileService {
    List<File> findAll();
    File create(File file);
    List<File> findByFolder(String folder,String username);
    File findOne(long fileid);
    int findsoluongfilecungticket(boolean ticket,long idtick,String username);
    int findsoluongfilecungtask(boolean task,long idtask,String username);
    List<File> filetaskclose(boolean task,String username);
    List<File> findagui(String username);
    List<File> finduocgui(String email);
    List<File> findgansao(String username);
    List<File> nameFolder();
    File saveFolder(MultipartFile file, String username,File files,String path,String name);
    void deletebyid(long idfile);
    List<File> HaveFolder(String username,String folder);

    File saveFile(MultipartFile file,String folder,String username);
    List<File> FileGroup(String name);
    List<File> FindByUser(String username);
    String folder(long IDFile);
    List<File> filefolder(String folder);
    Long tongsizefolder(String usernamem,String folder);
    List<FileZile> ticketfile();
    List<FileZile> taskfile();
    List<File> nofolder();
    Long getMaxTask(long task);
    Long getMaxTicket(long ticket);
    List<File> fileticket();
    List<File> filetask();
}

