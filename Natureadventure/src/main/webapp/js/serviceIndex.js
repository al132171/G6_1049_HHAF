(function () {

    var app = angular.module("app", []);
    alert("hago app");

    app.controller('IndiceCtrl', ['$scope', 'IndiceService', function($scope, IndiceService) {
        alert("jajajaja");
        app.baseURI = 'http://localhost:8080/Natureadventure/cliente/actividades/';
        var self = this;

        $scope.feed = {};

        alert("angular");
        $scope.feed.novedades = IndiceService.retrieveNovedades().success(function(data){
            alert("ok");
            $scope.feed.novedades = data.actividad;
        }).error(function(data){
            alert("error");
        });

    }]);

    app.service('IndiceService', ['http', function($http) {

        this.retrieveNovedades = function() {
            var url = app.baseURI + "novedades";
            return $http.get(url);
        };

        this.retrieveOfertas = function() {
            var url = app.baseURI + "ofertas";
            return $http.get(url);
        };

    }]);

})();