package com.fis.sdc.checkfile.SERVICE.Impl;

import com.fis.sdc.checkfile.DAO.HistoryDAO;
import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.MODEL.History;
import com.fis.sdc.checkfile.SERVICE.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryDAO dao;
    @PersistenceContext
    public EntityManager em;
    @Override
    public List<History> findAll() {
        return dao.findAll();
    }

    @Override
    public History findOne(long IDHis) {
        return dao.findById(IDHis).get();
    }

    @Override
    public History create(History his) {
        return dao.save(his);
    }

    @Override
    public List<History> findByUser(String username) {
        return dao.findByUser(username);
    }

    @Override
    public Page<History> findtop7his(String username, Pageable pageable) {
        return dao.findtop7his(username,pageable);
    }

    @Override
    public int capnhatthoigian(Date datehis, Account username, File IDFile) {
        return dao.UpdateSl(datehis, username, IDFile);
    }

    @Override
    public History findbytkanid(String username, long IDFile) {
        return dao.findbytkanid(username, IDFile);
    }


    @Override
    public void insert(Date datehis, Account username, File IDFile) {
        dao.insert(datehis,username,IDFile);
    }


}
