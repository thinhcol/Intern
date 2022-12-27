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
@Table(name = "History")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IDHis;
    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date datehis;
    @JoinColumn(name = "IDFile")
    @ManyToOne
    private File file;

    @JoinColumn(name = "username")
    @ManyToOne
    private Account user;
}
