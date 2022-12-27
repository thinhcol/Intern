package com.fis.sdc.checkfile.SERVICE;

import com.fis.sdc.checkfile.MODEL.Favorite;
import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.MODEL.FileZile;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteService {
    List<Favorite> findAll();
    Favorite findOne(long IDFR);
    Favorite save(Favorite favorite);
    void deleteById(long IDFR);
    Favorite checksao(String username, long IDFile);
    List<Favorite> checkuser(String username);
    List<Favorite> findByfolder(String folder,String usernmae);
//    void DeleteByUsIf(String username,long idfile);
    void DeleteByUsIf(String username, String folder);
    List<FileZile> folderfavorite();

    List<File> filefavorite();
}
