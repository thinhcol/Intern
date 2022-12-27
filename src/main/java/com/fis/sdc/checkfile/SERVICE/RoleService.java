package com.fis.sdc.checkfile.SERVICE;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.Role;

import java.util.List;


public interface RoleService {
    List<Role> findAll();
    Role findOne(int IDGR);
    Role create(Role gr);
    void delelete(int IDGR);
}
