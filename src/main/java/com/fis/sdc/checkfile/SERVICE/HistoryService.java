package com.fis.sdc.checkfile.SERVICE;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.MODEL.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface HistoryService {
    List<History> findAll();
    History findOne(long IDHis);
    History create(History his);
    List<History> findByUser(String username);
    Page<History> findtop7his(String username, Pageable pageable);
    int capnhatthoigian(Date datehis, Account username, File IDFile);
    History findbytkanid(String username, long IDFile);
    void insert(Date datehis,Account username,File IDFile);
}
