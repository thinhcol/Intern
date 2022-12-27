app = angular.module('vinasoy', ['ngRoute','ngTagsInput','angularModalService']);
app.config(function ($routeProvider) {

    $routeProvider
        .when("/quanlyvatudonghoaquytrinh", {
            templateUrl: "quanlytudonghoa.html", controller: "quanlytudonghoa-ctrl"
        })
        .when("/giaoviectudong", {
            templateUrl: "giaoviectudong.html", controller: "giaoviectudong-ctrl"
        })
        .when("/tailieutoidachiase", {
            templateUrl: "tailieutoidachiase.html", controller: "tailieutoidachiase-ctrl"
        })
        .when("/tailieutoiduocchiase", {
            templateUrl: "tailieutoiduocchiase.html", controller: "tailieutoiduocchiase-ctrl"
        })
        .when("/tailieuduocgansao", {
            templateUrl: "tailieuduocgansao.html", controller: "tailieuduocgansao-ctrl"
        })
        .when("/truyxuatfolder/:id/:menu/:quanly", {
            templateUrl: "openfolder.html", controller: "openfolder-ctrl"
        })
        .when("/kiemtratinhtoanven", {
            templateUrl: "kiemtratinhtoanven.html", controller: "kiemtratinhtoanven-ctrl"
        })
        .when("/filehuongdan", {
            templateUrl: "huongdansudung.html", controller: "filehuongdan-ctrl"
        })
        .otherwise({
            template: ""
        });

});
app.controller("my-ctrl", function ($rootScope, $http,$location) {
    $rootScope.account = {};
    $rootScope.history = [];
    $rootScope.fileall = function () {
        $http.get("/rest/account").then(resp => {
            $rootScope.account = resp.data;

        });
        $http.get("/rest/histories").then(resp => {
            $rootScope.history = resp.data;

        });
    }
    $rootScope.isActive = function (viewLocation) {
        var active = (viewLocation === $location.path());
        return active;
    };
    $rootScope.fileall();
});
