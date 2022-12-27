package com.fis.sdc.checkfile.DAO;

import com.fis.sdc.checkfile.MODEL.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface ShareDAO extends JpaRepository<Share,Long> {
    @Modifying
    @Query(value = "INSERT into Share (email,IDFile,username,dateshare,folder) values (:email,:IDFile,:username,:dateshare,:folder)", nativeQuery =true )
    @Transactional
    int them(@Param("email") String email, @Param("IDFile") File IDFile, @Param("username") Account username, @Param("dateshare")Date dateshare, @Param("folder")String folder);

    @Query("Select s from Share s where s.dateshare = ?1")
    List<Share> findbydatehis(Date dateshare);
    @Query("Select s.dateshare From Share s where s.user.username like ?1 group by s.dateshare")
    List<Date> shareduocgui(String username);

    @Query("Select count(s.dateshare) From Share s where s.user.username like ?1 and s.dateshare = ?2")
    int soluongngaytrung(String username,Date dateshare);

    @Query("Select new com.fis.sdc.checkfile.MODEL.FileZileShare(s.file.folder,s.file.datefile,sum(s.file.size),s.user.fullname,s.dateshare) From Share s where s.user.username like ?1 and not s.folder = '' group by s.folder")
    List<FileZileShare> folderdashare(String username);
    @Query("Select s From Share s where s.user.username like ?1 and s.folder = '' group by s.file.IDFile")
    List<Share> filedashare(String username);
    @Query("Select new com.fis.sdc.checkfile.MODEL.FileZileShare(s.file.folder,s.file.datefile,sum(s.file.size),s.user.fullname,s.dateshare) From Share s where s.email like ?1 and not s.folder = '' group by s.folder")
    List<FileZileShare> folderduocshare(String email);
    @Query("Select s From Share s where s.email like ?1 and s.folder = '' group by s.file.IDFile")
    List<Share> fileduocshare(String email);
    @Modifying
    @Query(value = "INSERT into Share (dateshare,email,cc,username,IDFile) values (:dateshare,:email,:cc,:username,:IDFile)", nativeQuery =true )
    @Transactional
    int insert(@Param("dateshare")Date dateshare,@Param("email") String email,@Param("cc") String cc,@Param("username") Account username,@Param("IDFile") File IDFile);
}
