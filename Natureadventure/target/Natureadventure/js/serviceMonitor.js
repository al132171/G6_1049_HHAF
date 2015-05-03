/**
 * 
 */


var app = angular.module("app", []); 
	
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
	    		});
	        }
	         else if($scope.class2 == "active" && tipo=="1"){
	            $scope.class2 = "";
	            $scope.class1 = "active";
	            $scope.reservas = ReservasService.retrieveAllSupervisar()
	    		.success(function(data){
	    			$scope.pendientes = Object.keys(data.reserva).length;
	    			$scope.reservas = data.reserva;
	    		});
	         }
	    };
		
	  //Para la primera vez que carge la página en supervisar
		$scope.reservas = ReservasService.retrieveAllSupervisar()
		.success(function(data){
			$scope.pendientes = Object.keys(data.reserva).length;
			$scope.reservas = data.reserva;
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
		 