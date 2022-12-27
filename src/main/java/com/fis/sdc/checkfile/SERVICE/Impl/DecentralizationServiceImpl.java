package com.fis.sdc.checkfile.SERVICE.Impl;

import com.fis.sdc.checkfile.DAO.AccountDAO;
import com.fis.sdc.checkfile.DAO.DecentralizationDAO;
import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.MODEL.decentralization;
import com.fis.sdc.checkfile.MODEL.permission;
import com.fis.sdc.checkfile.SERVICE.AccountService;
import com.fis.sdc.checkfile.SERVICE.DecentralizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DecentralizationServiceImpl implements DecentralizationService {
    @Autowired
    private DecentralizationDAO dao;

    @Override
    public List<decentralization> findAll() {
        return dao.findAll();
    }

    @Override
    public decentralization create(decentralization per) {
        return dao.save(per);
    }

    @Override
    public decentralization findOne(long id) {
        return dao.findById(id).get();
    }

    @Override
    public void deleteOne(long id) {
        dao.deleteById(id);
    }

    @Override
    public int insert(String groupname,String folder, permission idprm, Account username, File IDFile) {
        return dao.insert(groupname,folder, idprm, username, IDFile);
    }

    @Override
    public List<decentralization> findByUser(String username) {
        return dao.findByUser(username);
    }

    @Override
    public List<decentralization> findByFolder(String folder) {
        return dao.findByFolder(folder);
    }
    @Override
    public List<decentralization> findByfolder(String folder, int idprm) {
        return dao.findByfolder(folder,idprm);
    }

    @Override
    public List<decentralization> findgroupname(int idprm) {
        return dao.findgroupname(idprm);
    }

    @Override
    public List<decentralization> findbigdata(String folder, int idprm, String username) {
        return dao.findbigdata(folder, idprm, username);
    }

    @Override
    public List<decentralization> findgroup(String folder, int idprm, String groupname) {
        return dao.findgroup(folder, idprm, groupname);
    }

    @Override
    public List<String> findgroupby(int idprm) {
        return dao.findgroupby(idprm);
    }

    @Override
    public void DeleteByGroup(int idprm, String groupname) {
        dao.DeleteByGroup(idprm, groupname);
    }

    @Override
    public void DeleteByUsername(int idprm, Account username) {
        dao.DeleteByUsername(idprm, username);
    }

    @Override
    public List<decentralization> usernameprm(int idprm) {
        return dao.usernameprm(idprm);
    }

//    @Override
//    public List<String> user(int idprm) {
//        return dao.user(idprm);
//    }
}
