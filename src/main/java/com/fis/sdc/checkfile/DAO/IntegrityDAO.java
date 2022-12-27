package com.fis.sdc.checkfile.DAO;

import com.fis.sdc.checkfile.MODEL.Integrity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntegrityDAO extends JpaRepository<Integrity,Long> {
}
