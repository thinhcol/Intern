package com.fis.sdc.checkfile.MODEL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Share")
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IDShare;
    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date dateshare;
    private String email;
    private String folder;

    @JoinColumn(name = "IDFile")
    @ManyToOne(cascade = CascadeType.ALL)
    private File file;

    @JoinColumn(name = "username")
    @ManyToOne
    private Account user;

}
