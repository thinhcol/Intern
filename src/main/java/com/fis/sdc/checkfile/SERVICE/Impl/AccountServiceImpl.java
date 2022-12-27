package com.fis.sdc.checkfile.SERVICE.Impl;

import com.fis.sdc.checkfile.DAO.AccountDAO;
import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.SERVICE.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDAO dao;
    @Override
    public List<Account> findAll() {
        return dao.findAll();
    }

    @Override
    public Account create(Account account) {
        return dao.save(account);
    }

    @Override
    public List<Account> finduocgui(String email) {
        return dao.finduocgui(email);
    }

    @Override
    public Account findOne(String username) {
        return dao.findById(username).get();
    }

    @Override
    public Account findByEmail(String email) {
        return dao.findbyEmail(email);
    }


    @Override
    public List<String> findEmail(String username) {
        return dao.findEmail(username);
    }

    @Override
    public List<String> finduser(String username) {
        return dao.finduser(username);
    }
}
