app.controller("openfolder-ctrl", function ($scope, $http, $location,$routeParams) {
    var id = $routeParams.id;
    $scope.folder = $routeParams.id;
    $scope.menu = $routeParams.menu;
    $scope.quanly = $routeParams.quanly;
    $scope.foldername = [];
    $scope.nhom = [];
    $scope.history = [];

    $scope.files = [];
    $scope.cates = [];
    $scope.favorites = [];
    $scope.idfile = [];
    $scope.accounts = [];
    $scope.file = {};
    $scope.account = {};
    $scope.role = [];
    $scope.lst = [];
    $scope.result = [];
    $scope.nhom = [];
    $scope.canhan = [];
    $scope.accountgr = [];
    $scope.group = [];
    $scope.notice = {text: ""};
    $scope.content = {text: ""};
    $scope.che = [];
    $scope.roleacc = [];

    $scope.fileall = function () {
        $http.get(`/rest/files/openfolder/${id}`).then(resp => {
            $scope.foldername = resp.data;
            console.log($scope.foldername);
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
    }

    // Phân trang
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
        document.getElementById("toshare").value = null;
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
        var index = $scope.checkfile(id);
        if (index) {
            var ok = $scope.idfile.findIndex(t => t.idfile == id.idfile);
            $scope.idfile.splice(ok, 1);
            console.log($scope.idfile)
        } else {
            $scope.idfile.push(id);
            console.log($scope.idfile)
        }
    }


    $scope.checkfile = function (id) {
        return $scope.idfile.find(ur => ur.idfile == id.idfile);
    }
    // Chia sẻ file
    $scope.chiase = function (acc) {
        if ($scope.idfile.length > 0) {
            for (let i = 0; i < $scope.idfile.length; i++) {
                for (let j = 0; j < $scope.lst.length; j++) {
                    var share = {
                        cc: $scope.notice.text,
                        dateshare: new Date(),
                        email: $scope.lst[j].text,
                        user: {username: acc.username},
                        file: {idfile: $scope.idfile[i].idfile},
                        folder:  $routeParams.id
                    }

                    $http.post("/rest/shares", share)

                }
            }
            $scope.idclass = "showthanhcong";
            $scope.image = "shield%201.png";
            $scope.note = "Thành công";
            $scope.reset();
        } else {
            $scope.idclass = "showchonfile";
            $scope.image = "No data-cuate (1) 1.png";
            $scope.note = "Vui lòng chọn file";

        }

    }

    //Gửi mail
    $scope.sharemail = function () {
        console.log($scope.content)
        if ($scope.idfile.length > 0) {
            var toshare = document.getElementById("toshare").value;
            var ccshare = document.getElementById("ccshare").value;
            var subjectshare = document.getElementById("subjectshare").value;
            var content = $scope.content;
            var sendmail = {
                to: toshare, cc: ccshare, subject: subjectshare, content: content, idfile: $scope.idfile
            }
            var json = JSON.stringify(sendmail);
            console.log(json);
            $http.post(`/rest/sendmail`, json).then(resp => {
                console.log("thanh cong");
                $scope.reset();
            }).catch(error => {
                console.log("Error", error);
            })
            $scope.idclass = "showthanhcong";
            $scope.image = "shield%201.png";
            $scope.note = "Thành công";
            $scope.reset();
        } else {
            $scope.idclass = "showchonfile";
            $scope.image = "No data-cuate (1) 1.png";
            $scope.note = "Vui lòng chọn file";

        }
    }

    // File có dấu sao
    $scope.checkfavorite = function (files, acc) {
        var chfav = $scope.favorites.find(ur => ur.user.username == acc.username && ur.file.idfile == files.idfile);
        if (chfav) {
            return true;
            console.log(files);
        } else {
            return false;
            console.log(files);
        }

    }

    $scope.actionfav = function (files, acc) {
        var chfav = $scope.favorites.find(ur => ur.user.username == acc.username && ur.file.idfile == files.idfile);
        if (chfav) {
            console.log(chfav);
            $http.delete(`/rest/favorites/${chfav.idfr}`).then(resp => {
                var index = $scope.favorites.findIndex(a => a.idfr == chfav.idfr);
                $scope.favorites.splice(index, 1);
                console.log("xoa thanh cong");
            }).catch(error => {
                console.log("Error", error);
            })

        } else {
            var f = {
                datefv: new Date(), user: {username: acc.username}, file: {idfile: files.idfile}
            }
            $http.post(`/rest/favorites`, f).then(resp => {
                $scope.favorites.push(resp.data);
                console.log("thanh cong");
            }).catch(error => {
                console.log("Error", error);
            })
        }
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
        console.log($scope.lst);
    }
    $scope.xoa = function (list) {
        var index = $scope.lst.findIndex(t => t.text == list);
        $scope.lst.splice(index, 1);
        console.log($scope.lst);
    }

    $scope.loadTags = function ($query) {
        var params = {
            text: $query
        }
        console.log(params);
        return Tag.getTags(params);
    };





    // Phân quyền
    // $scope.userus = function (value, chb) {
    //     if ($scope.idfile.length > 0) {
    //         for (let i = 0; i < $scope.idfile.length; i++) {
    //             var d = {
    //                 per: {idprm: chb}, user: {username: value}, file: {idfile: $scope.idfile[i].idfile}
    //             }
    //             $http.post("/rest/decentralization", d)
    //         }
    //         $scope.idclass = "showthanhcong";
    //         $scope.image = "shield%201.png";
    //         $scope.note = "Thành công";
    //     } else {
    //         $scope.idclass = "showchonfile";
    //         $scope.image = "No data-cuate (1) 1.png";
    //         $scope.note = "Vui lòng chọn file";
    //     }
    //
    //
    // }
    // $scope.hienthiuser = function (id, chb) {
    //     var toshare = document.getElementById(id).value;
    //     $scope.canhan = toshare.split(",");
    //     for (let i = 0; i < $scope.canhan.length; i++) {
    //         var gr = $scope.canhan[i];
    //         $scope.userus(gr, chb);
    //     }
    // }
    // $scope.usergr = function (value, chb) {
    //     if ($scope.idfile.length > 0) {
    //         $http.get(`/rest/groupes/${value}`).then(resp => {
    //             $scope.accountgr = resp.data;
    //             for (let j = 0; j < $scope.idfile.length; j++) {
    //                 for (let i = 0; i < $scope.accountgr.length; i++) {
    //                     var d = {
    //                         per: {idprm: chb},
    //                         user: $scope.accountgr[i],
    //                         file: {idfile: $scope.idfile[j].idfile},
    //                         groupname: value
    //                     }
    //                     console.log($scope.accountgr)
    //                     $http.post("/rest/decentralization", d)
    //                 }
    //
    //
    //             }
    //         });
    //         $scope.idclass = "showthanhcong";
    //         $scope.image = "shield%201.png";
    //         $scope.note = "Thành công";
    //     } else {
    //         $scope.idclass = "showchonfile";
    //         $scope.image = "No data-cuate (1) 1.png";
    //         $scope.note = "Vui lòng chọn file";
    //     }
    //
    // }
    // $scope.hienthigr = function (id, chb) {
    //     var toshare = document.getElementById(id).value;
    //     $scope.nhom = toshare.split(",");
    //     for (let i = 0; i < $scope.nhom.length; i++) {
    //         var gr = $scope.nhom[i];
    //         $scope.usergr(gr, chb);
    //     }
    // }
    //
    // $scope.nguoidungphanquyen = function (chb) {
    //     if ($scope.idfile.length > 0) {
    //         $http.get("/rest/roleaccs/user").then(resp => {
    //             $scope.roleacc = resp.data;
    //             console.log($scope.roleacc);
    //             for (let j = 0; j < $scope.idfile.length; j++) {
    //                 for (let i = 0; i < $scope.roleacc.length; i++) {
    //
    //                     var d = {
    //                         per: {idprm: chb},
    //                         user: {username: $scope.roleacc[i].username},
    //                         file: {idfile: $scope.idfile[j].idfile}
    //                     }
    //                     $http.post("/rest/decentralization", d)
    //                 }
    //             }
    //         });
    //         $scope.idclass = "showthanhcong";
    //         $scope.image = "shield%201.png";
    //         $scope.note = "Thành công 1";
    //     } else {
    //         $scope.idclass = "showchonfile";
    //         $scope.image = "No data-cuate (1) 1.png";
    //         $scope.note = "Vui lòng chọn file";
    //     }
    //
    // }
    //
    //
    // $scope.phanquyen = function () {
    //
    //
    //     if (document.getElementById('anuser').checked) {
    //         var hienthigr = document.querySelector('#anuser:checked').value;
    //         $scope.nguoidungphanquyen(hienthigr);
    //     }
    //     if (document.getElementById('undownuser').checked) {
    //         var hienthigr = document.querySelector('#undownuser:checked').value;
    //         $scope.nguoidungphanquyen(hienthigr);
    //     }
    //     if (document.getElementById('anuser').checked) {
    //         var hienthigr = document.querySelector('#anuser:checked').value;
    //         $scope.nguoidungphanquyen(hienthigr);
    //     }
    //
    //
    //     if (document.getElementById('seeCheck1').checked) {
    //         var hienthigr = document.querySelector('#seeCheck1:checked').value;
    //         $scope.hienthigr("hienthigr", hienthigr);
    //     }
    //     if (document.getElementById('seeCheckon').checked) {
    //         var hienthius = document.querySelector('#seeCheckon:checked').value;
    //         $scope.hienthiuser("hienthiuser", hienthius);
    //     }
    //     if (document.getElementById('seeCheckoff').checked) {
    //         var hienthigr = document.querySelector('#seeCheckoff:checked').value;
    //         $scope.hienthigr("khienthigr", hienthigr);
    //     }
    //     if (document.getElementById('seeCheck').checked) {
    //         var hienthigr = document.querySelector('#seeCheck:checked').value;
    //         $scope.hienthiuser("khienthouser", hienthigr);
    //     }
    //
    //
    //     if (document.getElementById('seedowngr').checked) {
    //         var hienthigr = document.querySelector('#seedowngr:checked').value;
    //         $scope.hienthigr("taigr", hienthigr);
    //     }
    //     if (document.getElementById('seedownu').checked) {
    //         var hienthius = document.querySelector('#seedownu:checked').value;
    //         $scope.hienthiuser("taiuser", hienthius);
    //     }
    //     if (document.getElementById('seedownungr').checked) {
    //         var hienthigr = document.querySelector('#seedownungr:checked').value;
    //         $scope.hienthigr("ktaigr", hienthigr);
    //     }
    //     if (document.getElementById('seedownunu').checked) {
    //         var hienthigr = document.querySelector('#seedownunu:checked').value;
    //         $scope.hienthiuser("ktaiuser", hienthigr);
    //     }
    //
    //
    //     if (document.getElementById('seesharegr').checked) {
    //         var hienthigr = document.querySelector('#seesharegr:checked').value;
    //         $scope.hienthigr("chiasegr", hienthigr);
    //     }
    //     if (document.getElementById('seeshareu').checked) {
    //         var hienthius = document.querySelector('#seeshareu:checked').value;
    //         $scope.hienthiuser("chiaseuser", hienthius);
    //     }
    //     if (document.getElementById('seeshareugr').checked) {
    //         var hienthigr = document.querySelector('#seeshareugr:checked').value;
    //         $scope.hienthigr("kchiasegr", hienthigr);
    //     }
    //     if (document.getElementById('seeshareunu').checked) {
    //         var hienthigr = document.querySelector('#seeshareunu:checked').value;
    //         $scope.hienthiuser("kchiaseuser", hienthigr);
    //     }
    // }
    //
    // // Phân quyền hiẻn thị
    // $scope.checkquyen = function (files, account, idprm) {
    //     var index = $scope.role.find(ur => ur.user.username == account.username && ur.file.idfile == files.idfile && ur.per.idprm == idprm);
    //     if (index) {
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }
    //
    //
    // $scope.checkquyengr = function (files, account, idprm) {
    //     var index = $scope.role.find(ur => ur.user.username == account.username && ur.file.idfile == files.idfile && ur.per.idprm == idprm);
    //     if (index) {
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }
    //
    // $scope.checkquyengrfile = function (files, account, idprm) {
    //     return $scope.role.find(ur => ur.user.username == account.username && ur.file.idfile == files.idfile && ur.per.idprm == idprm);
    //
    // }
    //
    // $scope.checkquyenfile = function (files, account, idprm) {
    //     return $scope.role.find(ur => ur.user.username == account.username && ur.file.idfile == files.idfile && ur.per.idprm == idprm);
    //
    // }
    //
    // $scope.xemcheckbox = function (file,value){
    //     return $scope.role.find(ur => ur.file.idfile == file.idfile && ur.per.idprm == value);
    // }
    // $scope.xemcheckbox = function (file,value){
    //     var index  = $scope.role.find(ur => ur.file.idfile == file.idfile && ur.per.idprm == value);
    //
    // }
    //
    // $scope.truefalse = function (files, account, idprm1, idprm2, idprm3) {
    //     var x = $scope.checkquyengr(files, account, idprm1);
    //     var y = $scope.checkquyen(files, account, idprm2);
    //     var z = $scope.checkquyen(files, account, idprm3);
    //     if (x == true && y == true && z == true) {
    //
    //         return true;
    //     } else if (x == true && y == false && z == true) {
    //
    //
    //         return true;
    //     } else if (x == true && y == true && z == false) {
    //
    //
    //         return true;
    //     } else if (x == true && y == false && z == false) {
    //
    //         return true;
    //
    //     } else if (x == false && y == true && z == true) {
    //
    //
    //         return true;
    //     } else if (x == false && y == true && z == false) {
    //
    //
    //         return true;
    //     } else if (x == false && y == false && z == true) {
    //
    //         return true;
    //
    //     } else {
    //         return false;
    //     }
    // }


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