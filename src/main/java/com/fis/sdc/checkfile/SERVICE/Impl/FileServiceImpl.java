package com.fis.sdc.checkfile.SERVICE.Impl;

import com.fis.sdc.checkfile.DAO.AccountDAO;
import com.fis.sdc.checkfile.DAO.FileDAO;
import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.MODEL.FileZile;
import com.fis.sdc.checkfile.SERVICE.FileService;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zeroturnaround.zip.commons.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDAO dao;
    @Autowired
    private AccountDAO acdao;

    @Override
    public File saveFolder(MultipartFile file, String username,File files,String path,String name) {
        try {
            Account user = acdao.findById(username).get();
            files.setUser(user);
            savefilestoge(path, file,name);
            return create(files);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public File saveFile(MultipartFile file, String folder, String username) {
        String namefile = file.getOriginalFilename();
        try {
            File fils = new File();
            Account user = acdao.findById(username).get();
            fils.setUser(user);
            return create(fils);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<File> FileGroup(String name) {
        return dao.FileGroup(name);
    }

    @Override
    public List<File> FindByUser(String username) {
        return dao.FindByUser(username);
    }

    @Override
    public String folder(long IDFile) {
        return dao.folder(IDFile);
    }

    @Override
    public List<File> filefolder(String folder) {
        return dao.filefolder(folder);
    }

    @Override
    public Long tongsizefolder(String username, String folder) {
        return dao.tongsizefolder(username, folder);
    }

    @Override
    public List<FileZile> taskfile() {
        return dao.taskfile();
    }

    @Override
    public List<FileZile> ticketfile() {
        return dao.ticketfile();
    }

    @Override
    public List<File> nofolder() {
        return dao.nofolder();
    }

    @Override
    public Long getMaxTask(long task) {
        return dao.getMaxTask(task);
    }

    @Override
    public Long getMaxTicket(long ticket) {
        return dao.getMaxTicket(ticket);
    }

    @Override
    public List<File> fileticket() {
        return dao.fileticket();
    }

    @Override
    public List<File> filetask() {
        return dao.filetask();
    }


//    @Override
//    public List<File> findtop7his(String username) {
//        return dao.findtop7his(username);
//    }

    @Override
    public List<File> findAll() {
        return dao.findAll();
    }

    @Override
    public File create(File file) {
        return dao.save(file);
    }

    @Override
    public List<File> findByFolder(String folder, String username) {
        return dao.findFolder(folder, username);
    }

    @Override
    public File findOne(long fileid) {
        return dao.findById(fileid).get();
    }

    @Override
    public int findsoluongfilecungticket(boolean ticket, long idtick, String username) {
        return dao.findsoluongfilecungticket(ticket, idtick, username);
    }

    @Override
    public int findsoluongfilecungtask(boolean task, long idtask, String username) {
        return dao.findsoluongfilecungtask(task, idtask, username);
    }

    @Override
    public List<File> filetaskclose(boolean task, String username) {
        return dao.filetaskclose(task, username);
    }

    @Override
    public List<File> findagui(String username) {
        return dao.findagui(username);
    }

    @Override
    public List<File> finduocgui(String email) {
        return dao.finduocgui(email);
    }

    @Override
    public List<File> findgansao(String username) {
        return dao.findgansao(username);
    }

    @Override
    public List<File> nameFolder() {
        return dao.nameFolder();
    }

    @Override
    public void deletebyid(long idfile) {
        dao.deleteById(idfile);
    }

    @Override
    public List<File> HaveFolder(String username, String folder) {
        return dao.HaveFolder(username, folder);
    }

    public void savefilestoge(String folder, MultipartFile file,String name) throws IOException {
//        String folder = "C:\\FPT\\JAVA6\\spro-logfile\\checkfile\\src\\main\\resources\\static\\SaveFile\\";
        byte[] bytes = file.getBytes();
        Path path = Paths.get(folder + name);
        Files.write(path, bytes);
    }

    public void savefolderstoge(MultipartFile file, String namefolder) throws IOException {
        String folder = "C:\\FPT\\JAVA6\\spro-logfile\\checkfile\\src\\main\\resources\\static\\SaveFile\\" + namefolder + "\\";
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
