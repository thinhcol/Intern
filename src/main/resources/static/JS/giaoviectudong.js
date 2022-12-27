var app = angular.module("logfile", []);
app.controller("logfile-ctrl", function ($scope, $http) {
    $scope.files = [];
    $scope.cates = [];

    $scope.favorites = [];
    $scope.fileall = function () {
        $http.get("/rest/files").then(resp => {
            $scope.files = resp.data;
        });
    }
    $scope.favoriteall = function () {
        $http.get("/rest/favorites").then(resp => {
            $scope.files = resp.data;
        });
    }

    $scope.fav = {
        // account: {username: $("#namenguoi").text()},
        // file: {idfile: 362},
        // datefile: new Date(),
        check: false,
        checklike() {
            $http.get(`/rest/favorites/check?id=362&u=thinh`).then(resp => {
                check = resp.data
                console.log(check);

            })
        },
        // isLike: check[0],
        triggle(fileid, username) {
            if (check = true) {
                $http.delete(`/rest/favorites/delete?id=${fileid}&u=${username}`)
                this.check = false;
                console.log("Đúng nha 3")
            } else {
                this.check = true;
                var f = {
                    datefile: new Date(),
                    account: {username: username},
                    file: {idfile: fileid},
                }
                $http.post('/rest/favorites', f)
            }
        }

    };


})