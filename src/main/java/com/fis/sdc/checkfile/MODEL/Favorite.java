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
@Table(name = "Favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IDFR;
    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date datefv;
    private String folder;
    @JoinColumn(name = "username")
    @ManyToOne
    private Account user;

    @JoinColumn(name = "IDFile")
    @ManyToOne
    private File file;
}
