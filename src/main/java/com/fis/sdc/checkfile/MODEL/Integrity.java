package com.fis.sdc.checkfile.MODEL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Integrity")
public class Integrity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IDTE;
    @JoinColumn(name = "IDFile")
    @ManyToOne
    private File file;


}
