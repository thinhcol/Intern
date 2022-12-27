package com.fis.sdc.checkfile.DAO;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.Groupes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupesDAO extends JpaRepository<Groupes,Long> {
    @Query("select g from Groupes g where g.user.username like ?1 group by g.name")
    List<Groupes> findByUser(String username);

    @Query("select g from Groupes g where g.user.username like ?1 and g.name like ?2")
    Groupes checkgr(String username,String namegr);

    @Query("select g.user from Groupes g where g.name like ?1")
    List<Account> checkus(String namegr);

}
