package com.fis.sdc.checkfile.MODEL;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
//import java.util.Date;
import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "File")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IDFile;
    private String name;
    private long size;
    private String type;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date datefile;
    private boolean trangthai;
    private boolean task;
    private long task_id;
    private long task_name;
    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date task_date;
    private boolean ticket;
    private long ticket_id;
    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date ticket_date;
    private long ticket_name;
    private String folder;
    private String idphanhe;
    private String idquytrinh;
    private String idsite;
    private String idpb;
    private String nametaskfile;
    private String nameticketfile;
    @JoinColumn(name = "username")
    @ManyToOne
    private Account user;
    @JsonIgnore
    @OneToMany(mappedBy = "file")
    private List<Integrity> integrityList;
    @JsonIgnore
    @OneToMany(mappedBy = "file")
    private List<Favorite> favorites;
    @JsonIgnore
    @OneToMany(mappedBy = "file")
    private List<Share> shares;
    @JsonIgnore
    @OneToMany(mappedBy = "file")
    private List<History> histories;
    @JsonIgnore
    @OneToMany(mappedBy = "file")
    private List<Groupes> groups;
    @JsonIgnore
    @OneToMany(mappedBy = "file")
    private List<decentralization> decentralizations;


//    public File(String name, long size, String type, boolean trangthai, boolean task, long task_id, long task_name, boolean ticket, long ticket_id, long ticket_name,String folder) {
//        this.name = name;
//        this.size = size;
//        this.type = type;
//        this.trangthai = trangthai;
//        this.task = task;
//        this.task_id = task_id;
//        this.task_name = task_name;
//        this.ticket = ticket;
//        this.ticket_id = ticket_id;
//        this.ticket_name = ticket_name;
//        this.folder = folder;
//    }

    public File(String name, long size, String type, boolean trangthai, boolean task, long task_id, long task_name, boolean ticket, long ticket_id, long ticket_name, String folder, String idphanhe, String idquytrinh, String idsite, String idpb) {
        this.name = name;
        this.size = size;
        this.type = type;
        this.trangthai = trangthai;
        this.task = task;
        this.task_id = task_id;
        this.task_name = task_name;
        this.ticket = ticket;
        this.ticket_id = ticket_id;
        this.ticket_name = ticket_name;
        this.folder = folder;
        this.idphanhe = idphanhe;
        this.idquytrinh = idquytrinh;
        this.idsite = idsite;
        this.idpb = idpb;
    }
}
