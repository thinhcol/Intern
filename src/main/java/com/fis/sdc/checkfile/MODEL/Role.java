package com.fis.sdc.checkfile.MODEL;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    private String idrole;
    private String namerole;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<RoleAcc> roleAccs;
}
