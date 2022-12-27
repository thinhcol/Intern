package com.fis.sdc.checkfile.SERVICE;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.RoleAcc;

import java.util.List;


public interface RoleAccService {
    List<RoleAcc> findAll();
    RoleAcc findOne(int IDGR);
    RoleAcc create(RoleAcc gr);
    void delelete(int IDGR);
    List<Account> findAcc();
    RoleAcc findByAcc(String username);
}
