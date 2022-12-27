package com.fis.sdc.checkfile.SERVICE.Impl;

import com.fis.sdc.checkfile.DAO.FavoriteDAO;
import com.fis.sdc.checkfile.MODEL.Favorite;
import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.MODEL.FileZile;
import com.fis.sdc.checkfile.SERVICE.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    FavoriteDAO dao;
    @Override
    public List<Favorite> findAll() {
        return dao.findAll();
    }

    @Override
    public Favorite findOne(long IDFR) {
        return dao.findById(IDFR).get();
    }

    @Override
    public Favorite save(Favorite favorite) {
        return dao.save(favorite);
    }

    @Override
    public void deleteById(long IDFR) {
        dao.deleteById(IDFR);
    }

    @Override
    public Favorite checksao(String username, long IDFile) {
        return dao.checksao(username,IDFile);
    }

    @Override
    public List<Favorite> checkuser(String username) {
        return dao.favorbyuser(username);
    }

    @Override
    public List<Favorite> findByfolder(String folder,String username) {
        return dao.findByfolder(folder,username);
    }

    @Override
    public void DeleteByUsIf(String username, String folder) {
        dao.DeleteByUsIf(username, folder);
    }

    @Override
    public List<FileZile> folderfavorite() {
        return dao.folderfavorite();
    }

    @Override
    public List<File> filefavorite() {
        return dao.filefavorite();
    }

//    @Override
//    public void DeleteByUsIf(String username, long idfile) {
//         dao.DeleteByUsIf(username, idfile);
//    }


}
