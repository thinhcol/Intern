app.controller("filehuongdan-ctrl", function ($scope, $http) {
    $scope.listfile = [];
    $scope.fileall = function () {
        $http.get("/rest/filehd").then(resp => {
            $scope.listfile = resp.data;
            console.log($scope.listfile);
        });
    }
    $scope.file = function (files){
        $scope.fileitem = files;
    }
    $scope.upload = function () {
        var form = new FormData();
        for(var i = 0 ; i < $scope.fileitem.length;i++){
            form.append("files",$scope.fileitem[i]);
        }

        $http.post("/rest/filehd/save",form,{
            transformRequest:angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            $scope.fileall();

        }).catch(error => {
            console.log("Errors", error);
            $scope.idclass = "showthanhcong";
            $scope.image = "shield%201.png";
            $scope.note = "Vui lòng upload file dung lượng tối đa 10MB và dạng file pdf";

        });
        $scope.idclass = "showthanhcong";
        $scope.image = "shield%201.png";
        $scope.note = "Thành công";
    }
    $scope.checksize = function (files) {
        var num = files / 1024;
        if (num >= 1024) {
            return true;
        } else {
            return false;
        }
    }
    // $scope.guestModal = function() {
    //     ModalService.showModal({
    //         templateUrl: "./guestModal.html"
    //     });
    // };

    $scope.pager = {
        page: 0, size: 4, get listfile() {
            var start = this.page * this.size;
            return $scope.listfile.slice(start, start + this.size);
        }, get count() {
            return Math.ceil(1.0 * $scope.listfile.length / this.size);
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

    $scope.fileall();
});