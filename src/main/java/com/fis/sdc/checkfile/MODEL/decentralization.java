package com.fis.sdc.checkfile.MODEL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "decentralization")
public class decentralization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idde;
    private String groupname;
    private String folder;
    @JoinColumn(name = "username")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Account user;

    @JoinColumn(name = "idprm")
    @ManyToOne
    private permission per;

    @JoinColumn(name = "IDFile")
    @ManyToOne
    private File file;
}
