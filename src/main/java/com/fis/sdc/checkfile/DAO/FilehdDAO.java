package com.fis.sdc.checkfile.DAO;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.Filehd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilehdDAO extends JpaRepository<Filehd,Integer> {

}
