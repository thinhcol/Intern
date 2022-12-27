package com.fis.sdc.checkfile.MODEL;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Account")
public class Account {
    @Id
    private String username;

    private String pass;

    private String fullname;

    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<File> file;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<History> histories;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Groupes> groups;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Share> share;
    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<decentralization> decentralizations;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<RoleAcc> roleaccs;
    public Account(String username, String pass) {
    }


}

