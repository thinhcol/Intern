package com.fis.sdc.checkfile.DAO;

import com.fis.sdc.checkfile.MODEL.Favorite;
import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.MODEL.FileZile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import javax.transaction.Transactional;
import java.util.List;

public interface FavoriteDAO extends JpaRepository<Favorite,Long> {
    @Query("Select f from Favorite f where f.user.username = ?1 and f.file.IDFile = ?2")
    Favorite checksao(String username, long IDFile);
    @Query("Select f from Favorite f where f.user.username = ?1")
    List<Favorite> favorbyuser(String username);

    @Query("Select f from Favorite f where f.folder like ?1 and f.user.username like ?2")
    List<Favorite> findByfolder(String folder,String username);
    @Modifying
    @Transactional
    @Query(value="DELETE from Favorite where Favorite.user.username = :username and Favorite.folder = :folder",nativeQuery = true)
    void DeleteByUsIf(@Param("username") String username,@Param("folder") String folder);

    @Query("Select new com.fis.sdc.checkfile.MODEL.FileZile(s.folder,s.file.datefile,sum(s.file.size)) from Favorite s where not s.folder = '' group by s.folder")
    List<FileZile> folderfavorite();

    @Query("Select s.file from Favorite s where s.folder is null group by s.file.IDFile")
    List<File> filefavorite();
}
