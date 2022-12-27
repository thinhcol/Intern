package com.fis.sdc.checkfile.DAO;

import com.fis.sdc.checkfile.MODEL.testfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestFileDAO extends JpaRepository<testfile,Integer> {
}
