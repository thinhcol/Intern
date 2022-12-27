package com.fis.sdc.checkfile.SERVICE.Impl;

import com.fis.sdc.checkfile.DAO.AccountDAO;
import com.fis.sdc.checkfile.DAO.RoleDAO;
import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.Role;
import com.fis.sdc.checkfile.SERVICE.AccountService;
import com.fis.sdc.checkfile.SERVICE.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDAO dao;
    @Override
    public List<Role> findAll() {
        return dao.findAll();
    }

    @Override
    public Role findOne(int IDGR) {
        return dao.findById(IDGR).get();
    }

    @Override
    public Role create(Role gr) {
        return dao.save(gr);
    }

    @Override
    public void delelete(int IDGR) {
        dao.deleteById(IDGR);
    }
}
