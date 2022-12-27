package com.fis.sdc.checkfile.DAO;

import com.fis.sdc.checkfile.MODEL.permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionDAO extends JpaRepository<permission,Integer> {

}
