package com.fis.sdc.checkfile.MODEL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "testfile")
public class testfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String docName;
    private String docType;

    @Lob
    private byte[] data;

    public testfile(String docName, String docType, byte[] data) {
        this.docName = docName;
        this.docType = docType;
        this.data = data;
    }
}
