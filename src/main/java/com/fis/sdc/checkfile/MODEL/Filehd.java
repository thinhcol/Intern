package com.fis.sdc.checkfile.MODEL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "filehd")
public class Filehd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idfilehd;
    private String name;
    private long size;
    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date datefile;
    private String type;
    public Filehd(String name, long size,String type) {
        this.name = name;
        this.size = size;
        this.type = type;
    }
}
