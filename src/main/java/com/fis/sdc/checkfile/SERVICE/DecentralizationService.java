package com.fis.sdc.checkfile.SERVICE;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.MODEL.decentralization;
import com.fis.sdc.checkfile.MODEL.permission;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DecentralizationService {
    List<decentralization> findAll();

    decentralization create(decentralization per);

    decentralization findOne(long id);

    void deleteOne(long id);

    int insert(String groupname, String folder, permission idprm, Account username, File IDFile);

    List<decentralization> findByUser(String username);

    List<decentralization> findByFolder(String folder);

    List<decentralization> findByfolder(String folder, int idprm);

    List<decentralization> findgroupname(int idprm);

    List<decentralization> findbigdata(String folder, int idprm, String username);

    List<decentralization> findgroup(String folder, int idprm, String groupname);

    List<String> findgroupby(int idprm);
    void DeleteByGroup(int idprm,String groupname);
    void DeleteByUsername(int idprm,Account username);
    List<decentralization> usernameprm(int idprm);
//    List<String> user(int idprm);

}
