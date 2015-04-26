
var app = angular.module("app", []); //Define el modulo de la aplicación (ng-app="app") para todo el fichero

app.controller('DashboardCtrl', ['$scope', 'EstadisticasGService',
                                   function ($scope, EstadisticasGService) { //Inyecta los atributos

	$scope.username =  window.location.href.slice(window.location.href.indexOf('?') + 1).split('=')[1];

	$scope.monitoresContratados = EstadisticasGService.retrieveMonitoresActivos()
	.success(function(data){
		$scope.monitoresContratados = Object.keys(data.usuario).length;
	});
	
	$scope.reservasNuevas = EstadisticasGService.retrieveReservas()
	.success(function(data){
		$scope.reservasNuevas = Object.keys(data.reserva).length;
	});
	

	$scope.actividadesActivas = EstadisticasGService.retrieveActividadesActivas()
	.success(function(data){
		$scope.actividadesActivas = Object.keys(data.actividad).length;
	});

}

]);

app.service('EstadisticasGService', ['$http', function($http) {
	this.retrieveMonitoresActivos = function() {
		app.baseURI = 'http://localhost:8080/Natureadventure/gerente/monitores/';
		var url = app.baseURI+"activos";
		return $http.get(url);
	};
	
	this.retrieveReservas = function() {
		app.baseURI = 'http://localhost:8080/Natureadventure/gerente/reservas/';
		var url = app.baseURI+"pendientes";
		return $http.get(url);
	};
	
	this.retrieveActividadesActivas = function() {
		app.baseURI = 'http://localhost:8080/Natureadventure/gerente/actividades/';
		var url = app.baseURI;
		return $http.get(url);
	};
	
}]);
