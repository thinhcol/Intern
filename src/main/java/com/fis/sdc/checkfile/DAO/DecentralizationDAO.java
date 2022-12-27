package com.fis.sdc.checkfile.DAO;

import com.fis.sdc.checkfile.MODEL.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface DecentralizationDAO extends JpaRepository<decentralization,Long> {
    @Modifying
    @Query(value = "INSERT into decentralization (groupname,folder,idprm,username,IDFile) values (:groupname,:folder,:idprm,:username,:IDFile)", nativeQuery =true )
    @Transactional
    int insert(@Param("groupname") String groupname,@Param("folder") String folder, @Param("idprm") permission idprm, @Param("username") Account username, @Param("IDFile") File IDFile);

    @Query("select g from decentralization g where g.user.username like ?1")
    List<decentralization> findByUser(String username);

    @Query("select g from decentralization g where g.folder like ?1")
    List<decentralization> findByFolder(String folder);
    @Query("Select g from decentralization g where g.folder like ?1 and g.per.idprm = ?2")
    List<decentralization> findByfolder(String folder,int idprm);
;
    @Query("Select g from decentralization g where g.folder like ?1 and g.per.idprm = ?2 and g.user.username like ?3")
    List<decentralization> findbigdata(String folder,int idprm,String username);
    @Query("Select g from decentralization g where g.folder like ?1 and g.per.idprm = ?2 and g.groupname like ?3")
    List<decentralization> findgroup(String folder,int idprm,String groupname);
    @Query("Select g from decentralization g where g.per.idprm = ?1 group by g.groupname")
    List<decentralization> findgroupname(int idprm);
    @Query("Select g from decentralization g where g.per.idprm = ?1 group by g.user.username")
    List<decentralization> usernameprm(int idprm);
//    @Query("Select g from decentralization g where g.per.idprm = ?1 group by g.user.username")
//    List<decentralization> user(int idprm);
    @Query("Select g.groupname from decentralization g where g.per.idprm = ?1 group by g.groupname")
    List<String> findgroupby(int idprm);
    @Modifying
    @Transactional
    @Query(value="DELETE from decentralization where idprm like ?1 and username like ?2",nativeQuery = true)
    void DeleteByUsername(int idprm,Account user);
    @Modifying
    @Transactional
    @Query(value="DELETE from decentralization where decentralization.idprm = :idprm and decentralization.groupname = :groupname",nativeQuery = true)
    void DeleteByGroup(@Param("idprm") int idprm,@Param("groupname") String groupname);
}
