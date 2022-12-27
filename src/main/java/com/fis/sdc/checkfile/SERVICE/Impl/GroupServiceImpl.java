package com.fis.sdc.checkfile.SERVICE.Impl;

import com.fis.sdc.checkfile.DAO.GroupesDAO;
import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.Groupes;
import com.fis.sdc.checkfile.SERVICE.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupesDAO dao;
    @Override
    public List<Groupes> findAll() {
        return dao.findAll();
    }

    @Override
    public Groupes findOne(long IDGR) {
        return dao.findById(IDGR).get();
    }

    @Override
    public Groupes create(Groupes gr) {
        return dao.save(gr);
    }

    @Override
    public void delelete(long IDGR) {
        dao.deleteById(IDGR);
    }

    @Override
    public List<Groupes> findByUser(String username) {
        return dao.findByUser(username);
    }

    @Override
    public Groupes checkgr(String username, String namegr) {
        return dao.checkgr(username,namegr);
    }

    @Override
    public List<Account> checkus(String namegr) {
        return dao.checkus(namegr);
    }
}
