package com.fis.sdc.checkfile.SERVICE.Impl;

import com.fis.sdc.checkfile.DAO.DecentralizationDAO;
import com.fis.sdc.checkfile.DAO.PermissionDAO;
import com.fis.sdc.checkfile.MODEL.decentralization;
import com.fis.sdc.checkfile.MODEL.permission;
import com.fis.sdc.checkfile.SERVICE.DecentralizationService;
import com.fis.sdc.checkfile.SERVICE.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDAO dao;
    @Override
    public List<permission> findAll() {
        return dao.findAll();
    }

    @Override
    public permission create(permission per) {
        return dao.save(per);
    }

    @Override
    public permission findOne(int id) {
       return dao.findById(id).get();
    }
}
