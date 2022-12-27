package com.fis.sdc.checkfile.MODEL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "roleacc")
public class RoleAcc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idroleacc;

    @JoinColumn(name = "idrole")
    @ManyToOne
    private Role role;

    @JoinColumn(name = "username")
    @ManyToOne
    private Account user;
}
