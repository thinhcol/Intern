package com.fis.sdc.checkfile.DAO;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.Role;
import com.fis.sdc.checkfile.MODEL.RoleAcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleAccDAO extends JpaRepository<RoleAcc,Integer> {
    @Query("Select ra.user from RoleAcc ra where ra.role.idrole like 'user' ")
    List<Account> findAcc();
    @Query("Select ra from RoleAcc ra where ra.user.username like ?1")
    RoleAcc findByAcc(String username);
}
