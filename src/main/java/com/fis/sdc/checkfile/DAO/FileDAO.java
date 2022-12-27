package com.fis.sdc.checkfile.DAO;

import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.MODEL.FileZile;
import com.fis.sdc.checkfile.MODEL.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileDAO extends JpaRepository<File,Long> {
    @Query("select count(f.IDFile) FROM File f where f.ticket = ?1 and f.ticket_id = ?2 and f.user.username like ?3")
    int findsoluongfilecungticket(boolean ticket,long idtick,String username);
    @Query("select count(f.IDFile) FROM File f where f.task = ?1 and f.task_id = ?2 and f.user.username like ?3")
    int findsoluongfilecungtask(boolean task,long idtask,String username);
    @Query("select f FROM File f where f.task = ?1 and f.user.username like ?2")
    List<File> filetaskclose(boolean task, String username);
    @Query("Select s.file From Share s where s.user.username = ?1")
    List<File> findagui(String username);
    @Query("Select s.file From Share s where s.email like ?1")
    List<File> finduocgui(String email);
    @Query("Select f.file From Favorite f where f.user.username = ?1")
    List<File> findgansao(String username);
    @Query("Select f From File f where f.folder like ?1 and f.user.username like ?2")
    List<File> findFolder(String folder,String username);
    @Query("select f from File f group by f.folder")
    List<File> nameFolder();
    @Query("select f from File f where  f.user.username like ?1 and not f.folder like ?2 group by f.folder")
    List<File> HaveFolder(String username,String folder);
    @Query("Select g.file from Groupes g where g.name like ?1")
    List<File> FileGroup(String name);
    @Query("Select f from File f where f.user.username like ?1")
    List<File> FindByUser(String username);
    @Query("Select s.file From Share s where s.user.username = ?1 and s.file.IDFile = ?2")
    List<File> findaguiid(String username,long IDFile);
    @Query("Select s.folder From File s where s.IDFile = ?1")
    String folder(long IDFile);

    @Query("select f from File f where f.folder like %?1%")
    List<File> filefolder(String namefolder);

    @Query("select f from File f where f.folder = '' ")
    List<File> nofolder();

    @Query("Select sum(f.size) from File f where f.folder like ?1")
    Long tongsizefolder(String username,String folder);
    @Query("Select new com.fis.sdc.checkfile.MODEL.FileZile(f.folder,f.datefile,sum(f.size)) from File f where f.ticket = false and not f.folder = '' group by f.folder")
    List<FileZile> ticketfile();
    @Query("Select new com.fis.sdc.checkfile.MODEL.FileZile(f.folder,f.datefile,sum(f.size)) from File f where f.task = false and not f.folder = '' group by f.folder")
    List<FileZile> taskfile();
    @Query("SELECT coalesce(max(ch.task_name), 0) FROM File ch where ch.task_id = ?1")
    Long getMaxTask(long task_id);

    @Query("SELECT coalesce(max(ch.ticket_name), 0) FROM File ch where ch.ticket_id = ?1")
    Long getMaxTicket(long ticket_id);
    @Query("select f from File f where f.ticket = false and f.folder = '' ")
    List<File> fileticket();
    @Query("select f from File f where f.task = false and f.folder = '' ")
    List<File> filetask();
}
