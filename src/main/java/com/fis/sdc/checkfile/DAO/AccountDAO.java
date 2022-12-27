package com.fis.sdc.checkfile.DAO;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountDAO extends JpaRepository<Account,String> {
    @Query("Select s.user From Share s where s.email = ?1")
    List<Account> finduocgui(String email);

    @Query("Select u From Account u where u.email = ?1")
    Account findbyEmail(String email);

    @Query("Select u.email From Account u where u.username like ?1")
    List<String> findEmail(String username);

    @Query("Select u.username From Account u where not u.username like ?1")
    List<String> finduser(String username);
}
