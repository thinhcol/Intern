package com.fis.sdc.checkfile.SERVICE.Impl;

import com.fis.sdc.checkfile.DAO.ShareDAO;
import com.fis.sdc.checkfile.MODEL.*;
import com.fis.sdc.checkfile.SERVICE.AccountService;
import com.fis.sdc.checkfile.SERVICE.FileService;
import com.fis.sdc.checkfile.SERVICE.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShareServiceImpl implements ShareService {
    @Autowired
    private ShareDAO dao;
    @Autowired
    private AccountService acdao;
    @Autowired
    private FileService fidao;
    @Override
    public List<Share> findAll() {
        return dao.findAll();
    }

    @Override
    public Share create(Share share) {
        return dao.save(share);
    }

    @Override
    public Share findOne(long IDShare) {
        return dao.findById(IDShare).get();
    }

    @Override
    public void deleteById(long IDshare) {
        dao.deleteById(IDshare);
    }

    @Override
    public Share savefolder(Share share, Account acc, File file) {
        share.setUser(acc);
        share.setFile(file);
        return dao.save(share);
    }

    @Override
    public int them(String email, File IDFile, Account username, Date dateshare, String folder) {
        return dao.them(email, IDFile, username,dateshare,folder);
    }

    @Override
    public List<Share> findbydatehis(Date dateshare) {
        return dao.findbydatehis(dateshare);
    }

    @Override
    public List<Date> shareduocgui(String email) {
        return dao.shareduocgui(email);
    }

    @Override
    public int soluongngaytrung(String username, Date dateshare) {
        return dao.soluongngaytrung(username, dateshare);
    }

    @Override
    public List<FileZileShare> folderdashare(String username) {
        return dao.folderdashare(username);
    }

    @Override
    public List<Share> filedashare(String username) {
        return dao.filedashare(username);
    }

    @Override
    public List<FileZileShare> folderduocshare(String email) {
        return dao.folderduocshare(email);
    }

    @Override
    public List<Share> fileduocshare(String email) {
        return dao.fileduocshare(email);
    }

    @Override
    public int insert(Date dateshare, String email, String cc, Account username, File IDFile) {
       return dao.insert(dateshare, email, cc, username, IDFile);
    }
}
