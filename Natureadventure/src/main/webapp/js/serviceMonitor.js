/**
 * 
 */


var app = angular.module("app", []); 
	
//Filtro para la paginación
app.filter('offset', function () {
    return function (input, start) {
        start = parseInt(start, 10);
        return input.slice(start);
    };
});
	app.controller('MonitorActividadesCtrl', ['$scope', 'ReservasService',
	                                            function ($scope, ReservasService) { //Inyecta los atributos
		app.baseURI = 'http://localhost:8080/Natureadventure/monitor/reservas/';
		
				
		$scope.username =  window.location.href.slice(window.location.href.indexOf('?') + 1).split('=')[1];
	    
		
		$scope.class1 = "active";
	    
		$scope.changeClass = function(tipo){
	        if ($scope.class1 === "active" && tipo=="2"){
	            $scope.class2 = "active";
	            $scope.class1 = "";
	            $scope.reservas = ReservasService.retrieveAllPasadas()
	    		.success(function(data){
	    			$scope.reservas = data.reserva;
	    			$scope.pagReservas.reservas = data.reserva;
	    		});
	        }
	         else if($scope.class2 == "active" && tipo=="1"){
	            $scope.class2 = "";
	            $scope.class1 = "active";
	            $scope.reservas = ReservasService.retrieveAllSupervisar()
	    		.success(function(data){
	    			$scope.pendientes = Object.keys(data.reserva).length;
	    			$scope.reservas = data.reserva;
	    			$scope.pagReservas.reservas = data.reserva;
	    		});
	         }
	    };
	    
	    
//		***************************************
	    //Datos para la paginación
	           $scope.paginacion = {};
	           $scope.paginacion.itemsPerPage = 5;
	           $scope.paginacion.currentPage = 0;
	           $scope.paginacion.range = {};
	           //Fin datos para la paginación


	   // Funciones para la paginación
	           $scope.range = function () {
	               var rangeSize = 5;
	               var ret = [];
	               var start;

	               start = $scope.paginacion.currentPage;
	               if (start > $scope.pageCount() - rangeSize) {
	                   start = $scope.pageCount() - rangeSize + 1;
	               }
	                           
	               if( start < 0 ) {
	                   start = 0;
	                   rangeSize = $scope.pageCount()+1;
	               }
	                          
	               for (var i = start; i < start + rangeSize; i++) {
	                   ret.push(i);
	               }
	               return ret;
	           };

	           $scope.prevPage = function () {
	               if ($scope.paginacion.currentPage > 0) {
	                   $scope.paginacion.currentPage--;
	               }
	           };

	           $scope.prevPageDisabled = function () {
	               return $scope.paginacion.currentPage === 0 ? "disabled" : "";
	           };

	           $scope.pageCount = function () {
	               return Math.ceil($scope.pagReservas.reservas.length / $scope.paginacion.itemsPerPage) - 1;
	           };

	           $scope.nextPage = function () {
	               if ($scope.paginacion.currentPage < $scope.pageCount()) {
	                   $scope.paginacion.currentPage++;
	               }
	           };

	           $scope.nextPageDisabled = function () {
	               return $scope.paginacion.currentPage === $scope.pageCount() ? "disabled" : "";
	           };

	           $scope.setPage = function (n) {
	               $scope.paginacion.currentPage = n;
	           };
	           // Fin funciones para la paginación

		
//		***************************************
       $scope.pagReservas = {};
		
	  //Para la primera vez que carge la página en supervisar
		$scope.reservas = ReservasService.retrieveAllSupervisar()
		.success(function(data){
			$scope.pendientes = Object.keys(data.reserva).length;
			$scope.reservas = data.reserva;
			$scope.pagReservas.reservas = data.reserva;
		});
	
	}]);
	
//	Dashboard controller
	
	app.controller('DashboardCtrl', ['$scope', function ($scope) { //Inyecta los atributos
		$scope.username =  window.location.href.slice(window.location.href.indexOf('?') + 1).split('=')[1];
	}]);
	
	//	SERVICIOS WEB RESERVAS
	app.service('ReservasService', ['$http', function($http) {
		this.retrieveAllSupervisar = function() {
			var user = window.location.href.slice(window.location.href.indexOf('?') + 1).split('=')[1];
			var url = app.baseURI+"supervisar/"+user;
			return $http.get(url);
		};
		
		this.retrieveAllPasadas = function() {
			var user = window.location.href.slice(window.location.href.indexOf('?') + 1).split('=')[1];
			var url = app.baseURI+"pasadas/"+user;
			return $http.get(url);
		};
	}]);

		 