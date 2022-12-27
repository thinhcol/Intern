package com.fis.sdc.checkfile.SERVICE;

import com.fis.sdc.checkfile.MODEL.decentralization;
import com.fis.sdc.checkfile.MODEL.permission;

import java.util.List;

public interface PermissionService {
    List<permission> findAll();
    permission create(permission per);
    permission findOne(int id);

}
