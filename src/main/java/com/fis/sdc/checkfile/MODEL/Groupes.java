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
@Table(name = "Groupes")
public class Groupes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IDGR;
    private String name;
    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date dategr;

    @JoinColumn(name = "IDFile")
    @ManyToOne
    private File file;

    @JoinColumn(name = "username")
    @ManyToOne
    private Account user;
}
