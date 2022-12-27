package com.fis.sdc.checkfile.SERVICE;

import com.fis.sdc.checkfile.MODEL.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account create(Account account);
    List<Account> finduocgui(String email);
    Account findOne(String username);

    Account findByEmail(String email);
    List<String> findEmail(String username);

    List<String> finduser(String username);
}
