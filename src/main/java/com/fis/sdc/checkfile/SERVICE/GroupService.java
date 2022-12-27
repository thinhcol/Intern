package com.fis.sdc.checkfile.SERVICE;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.Groupes;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GroupService {
    List<Groupes> findAll();
    Groupes findOne(long IDGR);
    Groupes create(Groupes gr);
    void delelete(long IDGR);
    List<Groupes> findByUser(String username);
    Groupes checkgr(String username,String namegr);
    List<Account> checkus(String namegr);
}
