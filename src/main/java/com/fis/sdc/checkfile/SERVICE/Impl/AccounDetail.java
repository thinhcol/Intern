package com.fis.sdc.checkfile.SERVICE.Impl;

import com.fis.sdc.checkfile.DAO.AccountDAO;
import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.SERVICE.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AccounDetail implements UserDetailsService {
    @Autowired
    private AccountService appUserDAO;


    private String idUser ;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account appUser = appUserDAO.findOne(userName);
        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
        setIdUser(appUser.getUsername());
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        UserDetails userDetails = (UserDetails) new Account(appUser.getUsername(),appUser.getPass());
        return userDetails;
    }


    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
