package com.fis.sdc.checkfile.MODEL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileZileShare {
    private String folder;
    private Date datecreate;
    private Long sumsize;
    private String username;
    private Date dateshare;
}
