package com.fis.sdc.checkfile.SERVICE.Impl;

import com.fis.sdc.checkfile.DAO.RoleAccDAO;
import com.fis.sdc.checkfile.DAO.RoleDAO;
import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.Role;
import com.fis.sdc.checkfile.MODEL.RoleAcc;
import com.fis.sdc.checkfile.SERVICE.RoleAccService;
import com.fis.sdc.checkfile.SERVICE.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleAccServiceImpl implements RoleAccService {
    @Autowired
    RoleAccDAO dao;
    @Override
    public List<RoleAcc> findAll() {
        return dao.findAll();
    }

    @Override
    public RoleAcc findOne(int IDGR) {
        return dao.findById(IDGR).get();
    }

    @Override
    public RoleAcc create(RoleAcc gr) {
        return dao.save(gr);
    }

    @Override
    public void delelete(int IDGR) {
        dao.deleteById(IDGR);
    }

    @Override
    public List<Account> findAcc() {
        return dao.findAcc();
    }

    @Override
    public RoleAcc findByAcc(String username) {
        return dao.findByAcc(username);
    }
}
