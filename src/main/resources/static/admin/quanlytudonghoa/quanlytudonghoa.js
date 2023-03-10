app.controller("quanlytudonghoa-ctrl", function ($scope, $http, Tag) {
    $scope.folder = [];
    $scope.nhom = [];
    $scope.history = [];
    $scope.filelist = [];
    $scope.files = [];
    $scope.cates = [];
    $scope.favorites = [];
    $scope.idfile = [];
    $scope.accounts = [];
    $scope.file = {};
    $scope.filemail = [];
    $scope.account = {};
    $scope.role = [];
    $scope.lst = [];
    $scope.result = [];
    $scope.thanhvien = [];
    $scope.canhan = [];
    $scope.formphanquyen = [];
    $scope.accountgr = [];
    $scope.group = [];
    $scope.notice = {text: ""};
    // $scope.content = {text: ""};
    $scope.che = [];
    $scope.roleacc = [];
    $scope.text = [];
    $scope.namethumuc = [];
    $scope.listfav = [];
    $scope.mail = [];
    $scope.listquyen = []
    $scope.rolefile = [];
    $scope.form = {};
    $scope.filephanquyen = [];
    $scope.folderquyen = {};
    $scope.timgroup = [];
    $scope.groupnames = "";
    $scope.taikhoan = "";
    $scope.taikhoans = [];
    $scope.tennhomphanquyen = [];
    $scope.chuoi = [];
    $scope.tennguoi = [];
    $scope.field = [];
    $scope.fileall = function () {
        $http.get("/rest/files/tickfile").then(resp => {
            $scope.nofolder = resp.data;
        });
        $http.get("/rest/decentralization").then(resp => {
            $scope.rolefile = resp.data;

        });
        $http.get("/rest/files/tickfolder").then(resp => {
            $scope.folder = resp.data;
        });
        $http.get("/rest/histories").then(resp => {
            $scope.history = resp.data;
        });

        $http.get("/rest/account/all").then(resp => {
            $scope.accounts = resp.data;

        });
        $http.get("/rest/favorites").then(resp => {
            $scope.favorites = resp.data;

        });

        $http.get("/rest/groupes/user").then(resp => {
            $scope.group = resp.data;

        });
        $http.get("/rest/decentralization/user").then(resp => {
            $scope.role = resp.data;
        });
        $http.get("/rest/account").then(resp => {
            $scope.account = resp.data;
        });

        $http.get("/rest/files").then(resp => {
            $scope.files = resp.data;

        });
        $http.get("/rest/roleaccs/user").then(resp => {
            $scope.roleacc = resp.data;
        });

    }

    // Ph??n trang
    $scope.pager = {
        page: 0, size: 4, get foldername() {
            var start = this.page * this.size;
            return $scope.foldername.slice(start, start + this.size);
        }, get count() {
            return Math.ceil(1.0 * $scope.foldername.length / this.size);
        }, first() {
            this.page = 0;
        }, prev() {
            this.page--;
            if (this.page < 0) {
                this.last();
            }
        }, next() {
            this.page++;
            if (this.page >= this.count) {
                this.first();
            }
        }, last() {
            this.page = this.count - 1;
        }
    }

    //reset form
    $scope.reset = function () {
        $scope.idfile = [];
        $scope.lst = [];
        $scope.notice = {text: ""};
        $scope.content = {text: ""};
        document.getElementById("ccshare").value = null;
        document.getElementById("subjectshare").value = null;
    }


    $scope.checksize = function (files) {
        var num = files / 1024;
        if (num >= 1024) {
            return true;
        } else {
            return false;
        }
    }


    // Checkbox file
    $scope.checkbox = function (id) {
        var index = $scope.idfile.find(ur => ur == id);
        if (index) {
            var stt = $scope.idfile.findIndex(ur => ur == id);
            $http.get(`/rest/files/openfolder/${id}`).then(resp => {
                $scope.mail = resp.data;
                for (let i = 0; i < $scope.mail.length; i++) {
                    var number = $scope.filemail.findIndex(ur => ur.idfile == $scope.mail[i].idfile);
                    $scope.filemail.splice(number, 1);
                    console.log($scope.filemail);
                }
            });
            $scope.idfile.splice(stt, 1);
        } else {

            $scope.idfile.push(id);



            // var folder = $scope.idfile[i];
            $http.get(`/rest/files/openfolder/${id}`).then(resp => {
                $scope.filemail = $scope.filemail.concat(resp.data);
            });

        }
    }


    $scope.checkfile = function (id) {
        return $scope.idfile.find(ur => ur == id);
    }


    $scope.checkboxfile = function (id) {
        var index = $scope.checkfilecon(id);
        if (index) {
            var ok = $scope.filemail.findIndex(t => t.idfile == id.idfile);
            $scope.filemail.splice(ok, 1);
        } else {
            $scope.filemail.push(id);

        }
    }


    $scope.checkfilecon = function (id) {
        return $scope.filemail.find(ur => ur.idfile == id.idfile);
    }
// Taginput
    $scope.kiemtra = function (list) {
        return $scope.lst.find(ur => ur.text == list);
    }

    $scope.change = function (list) {
        var chindex = $scope.kiemtra(list);
        if (chindex) {
            $scope.xoa(list);

        } else {
            $scope.them(list);
        }
    };
    $scope.them = function (list) {
        var d = {
            "text": list
        }
        $scope.lst.push(d);
    }
    $scope.xoa = function (list) {
        var index = $scope.lst.findIndex(t => t.text == list);
        $scope.lst.splice(index, 1);

    }

    $scope.loadTags = function ($query) {
        var params = {
            text: $query
        }
        return Tag.getTags(params);
    };
// Chia s??? file
    $scope.chiase = function (acc) {
        if ($scope.filemail.length > 0 && $scope.lst.length > 0) {
            for (let j = 0; j < $scope.lst.length; j++) {
                var us = $scope.lst[j].text;
                $scope.text.push($scope.lst[j].text);
                var cmt = $scope.notice.text;
                $http.get(`/rest/account/email/${us}`).then(resp => {
                    $scope.usemail = resp.data;
                    var subject = "Th??ng b??o chia s??? t???p tin/th?? m???c";
                    var sendmail = {
                        to: $scope.usemail[0],
                        cc: $scope.usemail[0],
                        idfile: $scope.filemail,
                        subject: subject,
                        content: cmt
                    }
                    $http.post("/rest/sendmail/thongbao", sendmail).then(resp => {
                        console.log("thanh cong");
                        $scope.reset();
                    })
                });
                for (let i = 0; i < $scope.filemail.length; i++) {
                    var share = {
                        dateshare: new Date(),
                        email: $scope.text[j],
                        user: {username: acc.username},
                        file: {idfile: $scope.filemail[i].idfile},
                        folder: $scope.filemail[i].folder
                    }
                    $http.post("/rest/shares", share).then(resp => {
                        console.log("thanh cong");
                    })
                }

            }


            $scope.idclass = "showthanhcong";
            $scope.image = "shield%201.png";
            $scope.note = "Th??nh c??ng";
            $scope.reset();
        } else {
            $scope.idclass = "showchonfile";
            $scope.image = "No data-cuate (1) 1.png";
            $scope.note = "Vui l??ng ch???n t???p tin/th?? m???c ho???c ng?????i chia s???";

        }

    }

//G???i mail
    $scope.sharemail = function () {
        if ($scope.filemail.length > 0 && $scope.lst.length > 0) {
            for (let i = 0; i < $scope.lst.length; i++) {
                var us = $scope.lst[i].text;
                var ccshare = document.getElementById("ccshare").value;
                var subjectshare = document.getElementById("subjectshare").value;
                var content = "";
                $http.get(`/rest/account/email/${us}`).then(resp => {
                    $scope.usemail = resp.data;
                    for (let i = 0; i < $scope.usemail.length; i++) {
                        var sendmail = {
                            to: $scope.usemail[i], cc: $scope.usemail[i], subject: subjectshare, idfile: $scope.filemail
                        }
                        var json = JSON.stringify(sendmail);

                        $http.post(`/rest/sendmail`, json).then(resp => {

                            $scope.reset();
                        }).catch(error => {
                            console.log("Error", error);
                        })
                    }

                });

            }


            $scope.idclass = "showthanhcong";
            $scope.image = "shield%201.png";
            $scope.note = "Th??nh c??ng";
            $scope.reset();
        } else {
            $scope.idclass = "showchonfile";
            $scope.image = "No data-cuate (1) 1.png";
            $scope.note = "Vui l??ng ch???n t???p tin/th?? m???c ho???c ng?????i ???????c chia s???";

        }
    }

    $scope.sharemailfile = function () {
        if ($scope.idfile.length > 0) {
            var toshare = document.getElementById("toshare").value;
            var ccshare = document.getElementById("ccshare").value;
            var subjectshare = document.getElementById("subjectshare").value;
            var content = $scope.content;
            var sendmail = {
                to: toshare, cc: ccshare, subject: subjectshare, content: content, idfile: $scope.idfile
            }
            var json = JSON.stringify(sendmail);
            $http.post(`/rest/sendmail`, json).then(resp => {

                $scope.reset();
            }).catch(error => {
                console.log("Error", error);
            })
            $scope.idclass = "showthanhcong";
            $scope.image = "shield%201.png";
            $scope.note = "Th??nh c??ng";
            $scope.reset();
        } else {
            $scope.idclass = "showchonfile";
            $scope.image = "No data-cuate (1) 1.png";
            $scope.note = "Vui l??ng ch???n file";

        }
    }
// File c?? d???u sao
    $scope.checkfavoritefile = function (files, acc) {
        var chfav = $scope.favorites.find(ur => ur.user.username == acc.username && ur.file.idfile == files.idfile);
        if (chfav) {
            return true;

        } else {
            return false;
        }

    }

    $scope.actionfav = function (files, acc) {
        var chfav = $scope.favorites.find(ur => ur.user.username == acc.username && ur.file.idfile == files.idfile);
        if (chfav) {
            console.log(chfav);
            $http.delete(`/rest/favorites/${chfav.idfr}`).then(resp => {
                var index = $scope.favorites.findIndex(a => a.idfr == chfav.idfr);
                $scope.favorites.splice(index, 1);
            }).catch(error => {
                console.log("Error", error);
            })

        } else {
            var f = {
                datefv: new Date(), user: {username: acc.username}, file: {idfile: files.idfile}
            }
            $http.post(`/rest/favorites`, f).then(resp => {
                $scope.favorites.push(resp.data);

            }).catch(error => {

            })
        }
    }


    $scope.checkfavorite = function (item, acc) {
        var fail = false;
        for (let i = 0; i < $scope.favorites.length; i++) {

            if ($scope.favorites[i].folder == item.folder && $scope.favorites[i].user.username == acc.username) {

                fail = true;
                break;
            }
        }
        if (fail) {
            return true;
        } else {
            return false;

        }
    }
    $scope.xoafav = function (item, acc) {

        $http.get(`/rest/favorites/folder/${acc.username}/${item.folder}`).then(resp => {
            $scope.listfav = resp.data;

            for (let i = 0; i < $scope.listfav.length; i++) {
                var id = $scope.listfav[i].idfr;
                $http.delete(`/rest/favorites/${id}`).then(resp => {
                    var index = $scope.favorites.findIndex(a => a.idfr == $scope.listfav[i].idfr);
                    $scope.favorites.splice(index, 1);

                }).catch(error => {
                    console.log("Error", error);
                })
            }


        });


    }
    $scope.themfav = function (item, acc) {
        // var index = $scope.checkfavorite(item,acc);

        $http.get(`/rest/files/openfolder/${item.folder}`).then(resp => {
            $scope.file = resp.data;
            for (let i = 0; i < $scope.file.length; i++) {
                var f = {
                    datefv: new Date(),
                    user: {username: acc.username},
                    file: {idfile: $scope.file[i].idfile},
                    folder: item.folder
                }
                $http.post(`/rest/favorites`, f).then(resp => {
                    $scope.favorites.push(resp.data);

                }).catch(error => {
                    console.log("Error", error);
                })
            }

        });
    }


// Ph??n quy???n
    $scope.filedform = function (item) {
        $http.get(`/rest/decentralization/folder/${item.folder}`).then(resp => {
            $scope.formphanquyen = resp.data;
            console.log($scope.formphanquyen);
        });
        $scope.folderquyen = item;
        document.getElementById("testsgr").style.display = 'none';
        document.getElementById("testsonu").style.display = 'none';
        document.getElementById("testsofg").style.display = 'none';
        document.getElementById("tests").style.display = 'none';

        document.getElementById("downgr").style.display = 'none';
        document.getElementById("downu").style.display = 'none';
        document.getElementById("downungr").style.display = 'none';
        document.getElementById("downungu").style.display = 'none';

        document.getElementById("sharegr").style.display = 'none';
        document.getElementById("shareu").style.display = 'none';
        document.getElementById("shareungr").style.display = 'none';
        document.getElementById("shareunu").style.display = 'none';
        $scope.groupnames = "";
        $scope.taikhoan = "";
    }
    $scope.checkinputgr = function (group, value) {
        return $scope.rolefile.find(ur => ur.groupname == group && ur.per.idprm == value);
    }
    $scope.checkquyenxoatk = function (value, username) {
        return $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == value && ur.user.username == username);
    }
    $scope.checkquyenuser = function (value) {
        return $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == value);
    }

    $scope.checkquyencanhan = function (id, value) {
        var index = $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == value);
        if (index) {
            for (let i = 0; i < $scope.rolefile.length; i++) {

                if ($scope.rolefile[i].folder == $scope.folderquyen.folder && $scope.rolefile[i].per.idprm == value) {
                    var tim = $scope.field.find(ur => ur == $scope.rolefile[i].user.username);
                    if (!tim) {
                        $scope.field.push($scope.rolefile[i].user.username);

                    }
                }
            }

            for (let j = 0; j < $scope.field.length; j++) {
                $scope.taikhoan += $scope.field[j] + ",";
            }

            document.getElementById(id).style.display = 'block';
        } else {
            $scope.taikhoan = "";
        }
        return $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == value);
    }

    $scope.checkquyen = function (id, value) {
        var index = $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == value);
        if (index) {
            for (let i = 0; i < $scope.rolefile.length; i++) {
                if ($scope.rolefile[i].folder == $scope.folderquyen.folder && $scope.rolefile[i].per.idprm == value) {
                    if ($scope.groupnames.includes($scope.rolefile[i].groupname) == false) {
                        $scope.groupnames += $scope.rolefile[i].groupname + ",";
                    }
                }
            }
            // var toshare = document.getElementById(idinput).value;
            document.getElementById(id).style.display = 'block';

        } else {
            $scope.groupnames = "";
        }
        return $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == value);
    }

    $scope.xoaphanquyencanhan = function (index) {
        $http.get(`/rest/decentralization/alldata/${index.folder}/${index.per.idprm}/${index.user.username}`).then(resp => {
            $scope.listquyen = resp.data;

            for (let i = 0; i < $scope.listquyen.length; i++) {
                var id = $scope.listquyen[i].idde;
                $http.delete(`/rest/decentralization/${id}`).then(resp => {
                    var index = $scope.rolefile.findIndex(a => a.idde == $scope.listquyen[i].idde);
                    $scope.rolefile.splice(index, 1);

                }).catch(error => {
                    console.log("Error", error);
                })
            }

        });
    }
    $scope.xoaphanquyennhom = function (index) {
        $http.get(`/rest/decentralization/group/${index.folder}/${index.per.idprm}/${index.groupname}`).then(resp => {
            $scope.listquyen = resp.data;
            for (let i = 0; i < $scope.listquyen.length; i++) {
                var id = $scope.listquyen[i].idde;
                $http.delete(`/rest/decentralization/${id}`).then(resp => {
                    var index = $scope.rolefile.findIndex(a => a.idde == $scope.listquyen[i].idde);
                    $scope.rolefile.splice(index, 1);
                }).catch(error => {
                    console.log("Error", error);
                })
            }

        });
    }
    $scope.nguoidungphanquyen = function (chb) {
        var index = $scope.checkquyenuser(chb);
        var folder = $scope.folderquyen.folder;
        if (index) {
            $scope.xoaphanquyen(index);
            // for (let i = 0; i < $scope.roleacc.length; i++) {

            //     $http.get(`/rest/files/openfolder/${folder}`).then(resp => {
            //         $scope.filephanquyen = resp.data;

            //         for (let j = 0; j < $scope.filephanquyen.length; j++) {
            //             var d = {
            //                 per: {idprm: chb},
            //                 user: {username: $scope.roleacc[i].username},
            //                 file: {idfile: $scope.filephanquyen[j].idfile},
            //                 folder: $scope.filephanquyen[j].folder
            //             }
            //             $http.post("/rest/decentralization", d).then(resp => {
            //                 $scope.rolefile.push(d);
            //             });
            //         }
            //     });
            // }
        } else {
            for (let i = 0; i < $scope.roleacc.length; i++) {
                $http.get(`/rest/files/openfolder/${folder}`).then(resp => {
                    $scope.filephanquyen = resp.data;

                    for (let j = 0; j < $scope.filephanquyen.length; j++) {
                        var d = {
                            per: {idprm: chb},
                            user: {username: $scope.roleacc[i].username},
                            file: {idfile: $scope.filephanquyen[j].idfile},
                            folder: $scope.filephanquyen[j].folder
                        }
                        $http.post("/rest/decentralization", d).then(resp => {
                            $scope.rolefile.push(d);
                        });
                    }
                });
            }

            for (let i = 0; i < $scope.roleacc.length; i++) {
                var x = chb + 1;

                var congm = $scope.checkquyenxoatk(x, $scope.roleacc[i].username);
                if (congm) {

                    $scope.xoaphanquyencanhan(congm);
                }
                var y = chb + 2;
                var congh = $scope.checkquyenxoatk(y, $scope.roleacc[i].username);

                if (congh) {

                    $scope.xoaphanquyencanhan(congh);
                }

            }


        }
        $scope.idclass = "showthanhcong";
        $scope.image = "shield%201.png";
        $scope.note = "Th??nh c??ng";

    }
    $scope.userus = function (id, chb) {
        var index = $scope.checkquyenuser(chb);
        var folder = $scope.folderquyen.folder;
        var toshare = document.getElementById(id).value;
        $scope.thanhvien = toshare.split(",");
        if (index) {
            $http.get(`/rest/decentralization/user/${chb}`).then(resp => {
                $scope.tennguoi = resp.data;

                for (let t = 0; t < $scope.tennguoi.length; t++) {
                    var tennhomdata =  $scope.thanhvien.find(ur => ur == $scope.tennguoi[t].user.username);
                    if (!tennhomdata) {
                        $http.post(`/rest/decentralization/checkuser/${chb}`, $scope.tennguoi[t].user)
                    }
                }

                for (let p = 0; p <  $scope.thanhvien.length; p++) {
                    var tennhominput = $scope.tennguoi.find(ur => ur.user.username ==  $scope.thanhvien[p]);
                    if (tennhominput == undefined) {
                        var folder = $scope.folderquyen.folder;

                        $http.get(`/rest/files/openfolder/${folder}`).then(resp => {
                            $scope.filephanquyen = resp.data;
                            for (let i = 0; i < $scope.filephanquyen.length; i++) {
                                    var d = {
                                        per: {idprm: chb},
                                        user: {username: $scope.thanhvien[p]},
                                        file: {idfile: $scope.filephanquyen[i].idfile},
                                        folder: $scope.filephanquyen[i].folder
                                    }
                                    $http.post("/rest/decentralization", d)
                            }
                        });
                        for (let i = 0; i < $scope.thanhvien.length; i++) {
                            if (chb == 3 || chb == 8 || chb == 13) {
                                var x = chb - 2;

                                var congm = $scope.checkquyenxoatk(x, $scope.thanhvien[i]);

                                if (congm) {

                                    $scope.xoaphanquyencanhan(congm);
                                }
                                var y = chb + 1;
                                var congh = $scope.checkquyenxoatk(y, $scope.thanhvien[i]);
                                if (congh) {
                                    $scope.xoaphanquyencanhan(congh);
                                }
                                var z = chb + 2;
                                var congb = $scope.checkquyenxoatk(z, $scope.thanhvien[i]);
                                if (congb) {
                                    $scope.xoaphanquyencanhan(congb);
                                }
                            }
                            if (chb == 5 || chb == 10 || chb == 15) {
                                var x = chb - 3;
                                var congm = $scope.checkquyenxoatk(x, $scope.thanhvien[i]);
                                if (congm) {

                                    $scope.xoaphanquyencanhan(congm);
                                }
                                var y = chb - 2;
                                var congh = $scope.checkquyenxoatk(y, $scope.thanhvien[i]);
                                if (congh) {

                                    $scope.xoaphanquyencanhan(congh);
                                }
                            }
                        }

                    }
                }
            })
        } else {

            $http.get(`/rest/files/openfolder/${folder}`).then(resp => {
                $scope.filephanquyen = resp.data;
                for (let i = 0; i < $scope.filephanquyen.length; i++) {

                    for (let j = 0; j < $scope.thanhvien.length; j++) {
                        var gr = $scope.thanhvien[j];

                        var d = {
                            per: {idprm: chb},
                            user: {username: $scope.thanhvien[j]},
                            file: {idfile: $scope.filephanquyen[i].idfile},
                            folder: $scope.filephanquyen[i].folder
                        }
                        $http.post("/rest/decentralization", d)
                    }

                }
            });

            for (let i = 0; i < $scope.thanhvien.length; i++) {
                if (chb == 3 || chb == 8 || chb == 13) {
                    var x = chb - 2;

                    var congm = $scope.checkquyenxoatk(x, $scope.thanhvien[i]);

                    if (congm) {

                        $scope.xoaphanquyencanhan(congm);
                    }
                    var y = chb + 1;
                    var congh = $scope.checkquyenxoatk(y, $scope.thanhvien[i]);
                    if (congh) {
                        $scope.xoaphanquyencanhan(congh);
                    }
                    var z = chb + 2;
                    var congb = $scope.checkquyenxoatk(z, $scope.thanhvien[i]);
                    if (congb) {
                        $scope.xoaphanquyencanhan(congb);
                    }
                }
                if (chb == 5 || chb == 10 || chb == 15) {
                    var x = chb - 3;
                    var congm = $scope.checkquyenxoatk(x, $scope.thanhvien[i]);
                    if (congm) {

                        $scope.xoaphanquyencanhan(congm);
                    }
                    var y = chb - 2;
                    var congh = $scope.checkquyenxoatk(y, $scope.thanhvien[i]);
                    if (congh) {

                        $scope.xoaphanquyencanhan(congh);
                    }
                }
            }


        }
        $scope.idclass = "showthanhcong";
        $scope.image = "shield%201.png";
        $scope.note = "Th??nh c??ng";
    }

    $scope.usergr = function (id, chb) {
        var index = $scope.checkquyenuser(chb);
        var toshare = document.getElementById(id).value;
        $scope.nhom = toshare.split(",");
        if (index) {
            $http.get(`/rest/decentralization/findgroup/${chb}`).then(resp => {
                $scope.chuoi = resp.data;
                for (let t = 0; t < $scope.chuoi.length; t++) {
                    var tennhomdata = $scope.nhom.find(ur => ur == $scope.chuoi[t]);

                    if (!tennhomdata) {
                        $http.post(`/rest/decentralization/check/${chb}`, $scope.chuoi[t])
                    }
                }
                for (let p = 0; p < $scope.nhom.length; p++) {
                    var tennhominput = $scope.chuoi.find(ur => ur == $scope.nhom[p]);
                    if (tennhominput == undefined) {

                        // var check = $scope.checkinputgr($scope.nhom[p], chb);
                        // if (!check) {
                        var folder = $scope.folderquyen.folder;

                        $http.get(`/rest/files/openfolder/${folder}`).then(resp => {
                            $scope.filephanquyen = resp.data;
                            for (let i = 0; i < $scope.filephanquyen.length; i++) {
                                var toshare = document.getElementById(id).value;
                                $scope.nhom = toshare.split(",");
                                var gr = $scope.nhom[p];

                                $http.get(`/rest/groupes/${gr}`).then(resp => {
                                    $scope.accountgr = resp.data;
                                    for (let j = 0; j < $scope.accountgr.length; j++) {
                                        var d = {
                                            per: {idprm: chb},
                                            user: {username: $scope.accountgr[j].username},
                                            file: {idfile: $scope.filephanquyen[i].idfile},
                                            groupname: $scope.nhom[p],
                                            folder: $scope.filephanquyen[i].folder
                                        }

                                        $http.post("/rest/decentralization", d).then(resp => {
                                            $scope.rolefile.push(d);
                                        });
                                    }
                                });

                            }

                        });

                        var gr = $scope.nhom[p];
                        $http.get(`/rest/groupes/${gr}`).then(resp => {
                            $scope.accountgr = resp.data;
                            console.log($scope.accountgr);
                            for (let i = 0; i < $scope.accountgr.length; i++) {
                                if (chb == 2 || chb == 7 || chb == 12) {
                                    var x = chb - 1;
                                    var congm = $scope.checkquyenxoatk(x, $scope.accountgr[i].username);
                                    if (congm) {

                                        $scope.xoaphanquyencanhan(congm);
                                    }
                                    var y = chb + 2;
                                    var congh = $scope.checkquyenxoatk(y, $scope.accountgr[i].username);
                                    if (congh) {

                                        $scope.xoaphanquyencanhan(congh);
                                    }
                                    var z = chb + 3;
                                    var congb = $scope.checkquyenxoatk(z, $scope.accountgr[i].username);
                                    if (congb) {

                                        $scope.xoaphanquyencanhan(congb);
                                    }
                                }


                                if (chb == 4 || chb == 9 || chb == 14) {
                                    var x = chb - 2;
                                    var congx = $scope.checkquyenxoatk(x, $scope.accountgr[i].username);
                                    if (congx) {

                                        $scope.xoaphanquyencanhan(congx);
                                    }
                                    var y = chb - 1;
                                    var congy = $scope.checkquyenxoatk(y, $scope.accountgr[i].username);
                                    if (congy) {

                                        $scope.xoaphanquyencanhan(congy);
                                    }
                                }
                            }
                        });


                    }

                }
            })

        } else {
            var folder = $scope.folderquyen.folder;
            $http.get(`/rest/files/openfolder/${folder}`).then(resp => {
                $scope.filephanquyen = resp.data;
                for (let i = 0; i < $scope.filephanquyen.length; i++) {
                    var toshare = document.getElementById(id).value;
                    $scope.nhom = toshare.split(",");
                    for (let z = 0; z < $scope.nhom.length; z++) {
                        var gr = $scope.nhom[z];

                        $http.get(`/rest/groupes/${gr}`).then(resp => {
                            $scope.accountgr = resp.data;

                            for (let j = 0; j < $scope.accountgr.length; j++) {
                                var d = {
                                    per: {idprm: chb},
                                    user: {username: $scope.accountgr[j].username},
                                    file: {idfile: $scope.filephanquyen[i].idfile},
                                    groupname: $scope.nhom[z],
                                    folder: $scope.filephanquyen[i].folder
                                }


                                $http.post("/rest/decentralization", d).then(resp => {
                                    $scope.rolefile.push(d);
                                });
                            }
                        });
                    }

                }

            });

            for (let z = 0; z < $scope.nhom.length; z++) {
                var gr = $scope.nhom[z];
                $http.get(`/rest/groupes/${gr}`).then(resp => {
                    $scope.accountgr = resp.data;

                    for (let i = 0; i < $scope.accountgr.length; i++) {
                        if (chb == 2 || chb == 7 || chb == 12) {
                            var x = chb - 1;
                            var congm = $scope.checkquyenxoatk(x, $scope.accountgr[i].username);
                            if (congm) {

                                $scope.xoaphanquyencanhan(congm);
                            }
                            var y = chb + 2;
                            var congh = $scope.checkquyenxoatk(y, $scope.accountgr[i].username);
                            if (congh) {

                                $scope.xoaphanquyencanhan(congh);
                            }
                            var z = chb + 3;
                            var congb = $scope.checkquyenxoatk(z, $scope.accountgr[i].username);
                            if (congb) {

                                $scope.xoaphanquyencanhan(congb);
                            }
                        }


                        if (chb == 4 || chb == 9 || chb == 14) {
                            var x = chb - 2;
                            var congx = $scope.checkquyenxoatk(x, $scope.accountgr[i].username);
                            if (congx) {

                                $scope.xoaphanquyencanhan(congx);
                            }
                            var y = chb - 1;
                            var congy = $scope.checkquyenxoatk(y, $scope.accountgr[i].username);
                            if (congy) {

                                $scope.xoaphanquyencanhan(congy);
                            }
                        }
                    }
                });
            }


        }
        $scope.idclass = "showthanhcong";
        $scope.image = "shield%201.png";
        $scope.note = "Th??nh c??ng";

    }


    $scope.xoaphanquyen = function (index) {

        $http.get(`/rest/decentralization/dulieu/${index.folder}/${index.per.idprm}`).then(resp => {
            $scope.listquyen = resp.data;
            for (let i = 0; i < $scope.listquyen.length; i++) {
                var id = $scope.listquyen[i].idde;
                $http.delete(`/rest/decentralization/${id}`).then(resp => {
                    var index = $scope.rolefile.findIndex(a => a.idde == $scope.listquyen[i].idde);
                    $scope.rolefile.splice(index, 1);


                }).catch(error => {
                    console.log("Error", error);
                })
            }

        });
    }

    $scope.quyenfile = function () {
        // //1
        // if (document.getElementById('anuser').checked) {
        //     var hienthius = document.querySelector('#anuser:checked').value;
        //     $scope.nguoidungphanquyen(hienthius);
        //
        // }else {
        //     var tim = $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == 1);
        //     if (tim != undefined) {
        //         $scope.xoaphanquyen(tim);
        //     }
        // }
        // //6
        // if (document.getElementById('undownuser').checked) {
        //     var hienthius = document.querySelector('#undownuser:checked').value;
        //     $scope.nguoidungphanquyen(hienthius);
        //
        // }else {
        //     var tim = $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == 6);
        //     if (tim != undefined) {
        //         $scope.xoaphanquyen(tim);
        //     }
        // }
        // //11
        // if (document.getElementById('unshareus').checked) {
        //     var hienthius = document.querySelector('#unshareus:checked').value;
        //     $scope.nguoidungphanquyen(hienthius);
        // }else {
        //     var tim = $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == 11);
        //     if (tim != undefined) {
        //         $scope.xoaphanquyen(tim);
        //     }
        // }
        //3
        if (document.getElementById('seeCheckon').checked) {
            var hienthius = document.querySelector('#seeCheckon:checked').value;
            $scope.userus("hienthiuser", hienthius);

        } else {
            var tim = $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == 3);
            if (tim != undefined) {
                $scope.xoaphanquyen(tim);
            }
        }
        //5
        if (document.getElementById('seeCheck').checked) {
            var hienthigr = document.querySelector('#seeCheck:checked').value;
            $scope.userus("khienthouser", hienthigr);
        } else {
            var tim = $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == 5);
            if (tim != undefined) {
                $scope.xoaphanquyen(tim);
            }
        }

        //8
        if (document.getElementById('seedownu').checked) {
            var hienthius = document.querySelector('#seedownu:checked').value;
            $scope.userus("taiuser", hienthius);
        } else {
            var tim = $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == 8);
            if (tim != undefined) {
                $scope.xoaphanquyen(tim);
            }

        }

        //10
        if (document.getElementById('seedownunu').checked) {
            var hienthigr = document.querySelector('#seedownunu:checked').value;
            $scope.userus("ktaiuser", hienthigr);
        } else {
            var tim = $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == 10);
            if (tim != undefined) {
                $scope.xoaphanquyen(tim);
            }

        }


        //13
        if (document.getElementById('seeshareu').checked) {
            var hienthius = document.querySelector('#seeshareu:checked').value;
            $scope.userus("chiaseuser", hienthius);
        } else {
            var tim = $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == 13);
            if (tim != undefined) {
                $scope.xoaphanquyen(tim);
            }

        }

        //15
        if (document.getElementById('seeshareunu').checked) {
            var hienthigr = document.querySelector('#seeshareunu:checked').value;
            $scope.userus("kchiaseuser", hienthigr);
        } else {
            var tim = $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == 15);
            if (tim != undefined) {
                $scope.xoaphanquyen(tim);
            }

        }


        //2
        if (document.getElementById('seeCheck1').checked) {
            var hienthigr = document.querySelector('#seeCheck1:checked').value;
            $scope.usergr("hienthigr", hienthigr);
            // document.getElementById("testsgr").style.display = 'block';
        } else {
            var tim = $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == 2);
            if (tim != undefined) {
                $scope.xoaphanquyen(tim);
                document.getElementById("testsgr").style.display = 'none';
            }
        }
        //4
        if (document.getElementById('seeCheckoff').checked) {
            var hienthigr = document.querySelector('#seeCheckoff:checked').value;
            $scope.usergr("khienthigr", hienthigr);
        } else {
            var tim = $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == 4);
            if (tim != undefined) {
                $scope.xoaphanquyen(tim);
            }

        }
        //7
        if (document.getElementById('seedowngr').checked) {
            var hienthigr = document.querySelector('#seedowngr:checked').value;
            $scope.usergr("taigr", hienthigr);
        } else {
            // var index = $scope.checkquyen(hienthigr);
            var tim = $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == 7);
            if (tim != undefined) {
                $scope.xoaphanquyen(tim);
            }
        }
        //9
        if (document.getElementById('seedownungr').checked) {
            var hienthigr = document.querySelector('#seedownungr:checked').value;
            $scope.usergr("ktaigr", hienthigr);
        } else {
            // var index = $scope.checkquyen(hienthigr);
            var tim = $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == 9);
            if (tim != undefined) {
                $scope.xoaphanquyen(tim);
            }
        }
        //12
        if (document.getElementById('seesharegr').checked) {
            var hienthigr = document.querySelector('#seesharegr:checked').value;
            $scope.usergr("chiasegr", hienthigr);
        } else {
            // var index = $scope.checkquyen(hienthigr);
            var tim = $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == 12);
            if (tim != undefined) {
                $scope.xoaphanquyen(tim);
            }
        }
        //14
        if (document.getElementById('seeshareugr').checked) {
            var hienthigr = document.querySelector('#seeshareugr:checked').value;
            $scope.usergr("kchiasegr", hienthigr);
        } else {
            // var index = $scope.checkquyen(hienthigr);
            var tim = $scope.rolefile.find(ur => ur.folder == $scope.folderquyen.folder && ur.per.idprm == 14);
            if (tim != undefined) {
                $scope.xoaphanquyen(tim);
            }
        }


    }

// Ph??n quy???n hi???n th???

    $scope.checkquyenpq = function (files, idprm) {
        var index = $scope.role.find(ur => ur.folder == files && ur.per.idprm == idprm);
        if (index) {
            return true;
        } else {
            return false;
        }
    }


    $scope.checkquyengr = function (files, account, idprm) {
        var index = $scope.role.find(ur => ur.user.username == account.username && ur.folder == files && ur.per.idprm == idprm);

        if (index) {
            return true;
        } else {
            return false;
        }
    }

    $scope.checkquyengrfile = function (files, account, idprm) {
        return $scope.role.find(ur => ur.user.username == account.username && ur.folder == files && ur.per.idprm == idprm);

    }


    $scope.xemcheckbox = function (file, value) {
        return $scope.role.find(ur => ur.file.idfile == file.idfile && ur.per.idprm == value);
    }


    $scope.truefalse = function (files, idprm1, idprm2, idprm3) {
        var index =  $scope.role.find(ur => ur.folder == files && ur.per.idprm == idprm1 || ur.per.idprm == idprm2 || ur.per.idprm == idprm3 );
        if (index) {
            return true;
        } else {
            return false;
        }
    }


    $scope.menu = "Qu???n l?? v?? t??? ?????ng h??a quy tr??nh"
    $scope.quanly = "quanlyvatudonghoaquytrinh"
    $scope.fileall();

});
(function () {
    'use strict';
    angular
        .module('vinasoy')
        .factory('Tag', Tag);

    Tag.$inject = ['$http', '$q', '$timeout'];

    function Tag($http, $q, $timeout) {
        var service = {
            getTags: getTags
        };
        var _this = this;

        return service;

        // SCOPE FUNCTIONS
        function getTags(query) {
            console.log(query);
            return $http({
                method: 'GET', url: '/rest/account/user', // my local
                // url: 'tags.json',
                params: query
            });
        }
    }
}());