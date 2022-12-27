package com.fis.sdc.checkfile.DAO;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.MODEL.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface HistoryDAO extends JpaRepository<History,Long> {
    @Query("Select h from History h where h.user.username like ?1")
    List<History> findByUser(String username);

    @Query("Select h from History h where h.user.username like ?1 order by h.datehis desc")
    Page<History> findtop7his(String username, Pageable pageable);
    @Modifying
    @Transactional
    @Query(value = "UPDATE History h set h.datehis = ?1 where username =?2 and IDFile = ?3", nativeQuery =true )
    int UpdateSl(Date datehis, Account username, File IDFile);
    @Query("Select h from History h where h.user.username like ?1 and h.file.IDFile = ?2")
    History findbytkanid(String username, long IDFile);
    @Modifying
    @Query(value = "INSERT into History (datehis,username,IDFile) values (:datehis,:username,:IDFile)", nativeQuery =true )
    @Transactional
    void insert(@Param("datehis")Date datehis,@Param("username") Account username,@Param("IDFile") File IDFile);
}
