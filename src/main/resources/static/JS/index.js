app = angular.module("vinasoy", ["ngRoute"]);
app.config(function ($routeProvider) {
    $routeProvider
        .when("/quanlyvatudonghoaquytrinh", {
            templateUrl: "../../templates/quanlytudonghoa.html", controller: "quanlytudonghoa-ctrl"
        })
        .when("/giaoviectudong", {
            templateUrl: "../../templates/giaoviectudong.html", controller: "giaoviectudong-ctrl"
        })
        .when("/tailieutoidachiase", {
            templateUrl: "../../templates/tailieutoidachiase.html", controller: "tailieutoidachiase-ctrl"
        })
        .when("/tailieutoiduocchiase", {
            templateUrl: "../../resources/templates/tailieutoiduocchiase.html", controller: "tailieutoiduocchiase-ctrl"
        })
        .when("/tailieuduocgansao", {
            templateUrl: "/templates/tailieuduocgansao.html", controller: "tailieuduocgansao-ctrl"
        })
        .when("/kiemtratinhtoanven", {
            templateUrl: "../../templates/kiemtratinhtoanven.html", controller: "kiemtratinhtoanven-ctrl"
        }).otherwise({
        template: "Đây là admin"
    });

});
