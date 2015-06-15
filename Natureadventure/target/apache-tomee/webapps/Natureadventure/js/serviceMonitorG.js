/**
 * Created by oscar on 13/11/14.
 */

//Borra los datos de los campos del formulario
function borraCampos($scope) {
	document.getElementById("createForm").reset();
}

//Comprobar que no existen errores en los campos del formulario
function validator($scope, tipo) { 
	if(tipo == "create"){
		if ($scope.createForm.$valid) {
			return true;
		} else {
			return false;
		}
	}else{
		if ($scope.updateForm.$valid) {
			return true;
		} else {
			return false;
		}
	}
};


var app = angular.module("app", []); //Define el modulo de la aplicacion (ng-app="app") para todo el fichero

//Filtro para la paginación
app.filter('offset', function () {
    return function (input, start) {
        start = parseInt(start, 10);
        return input.slice(start);
    };
});

app.controller('MonitoresCtrl', ['$scope', 'MonitorGService', 
                                 function ($scope, MonitorGService) { //Inyecta los atributos
	app.baseURI = 'http://localhost:8080/Natureadventure/gerente/monitores/';

	var self = this;
	
	$scope.username =  window.location.href.slice(window.location.href.indexOf('?') + 1).split('=')[1];

	$scope.class1 = "active";
    
	$scope.changeClass = function(tipo){
        if ($scope.class1 === "active" && tipo=="2"){
            $scope.class2 = "active";
            $scope.class1 = "";
            $scope.monitores = MonitorGService.retrieveAllNoDisponibles()
    		.success(function(data){
    			$scope.monitores = data.usuario;
    			$scope.pagMonitores.usuario = data.usuario;
    		});
        }
         else if($scope.class2 == "active" && tipo=="1"){
            $scope.class2 = "";
            $scope.class1 = "active";
            $scope.monitores = MonitorGService.retrieveAllActivos()
    		.success(function(data){
    			$scope.monitores = data.usuario;
    			$scope.pagMonitores.usuario = data.usuario;
    		});
         }
    };

//	Limpiar el formulario para que si sales de la ventana modal se limpien los mensajes de error y formato
	$scope.resetForm = function(user){ 
		var defaultForm = {
				nombre:"", apellidos:"", dni:"", especialidad:"", correo:"", telefono:"", username:"", password:""
		};
		$scope.createForm.$setPristine();
		$scope.user = defaultForm;
	};

//	Comprueba en tiempo real si el dni introducido ya existe y mostrar o quitar el error
	$scope.comprobarDni = function(dni){

		MonitorGService.retrieveContact(dni).success(function(data){
			$scope.createForm.dni.$setValidity("dni", false);
			$scope.updateForm.dniU.$setValidity("dniU", false);
		}).error(function(){
			$scope.createForm.dni.$setValidity("dni", true);
			$scope.updateForm.dniU.$setValidity("dniU", true);
		});
	};
	
	$scope.telefono = { 
			word: /^([0-9-])*$/
	};
	
	$scope.telefonoU = { 
			word: /^([0-9-])*$/
	};

	$scope.feed = {};
	$scope.feed.especialidades = [
		                       {'name': 'Bicicleta',
		                    	   'value': 'Bicicleta'},
		                    	   {'name': 'Running',
		                    		   'value': 'Running'},
		                    		   {'name': 'Extremo',
		                    			   'value': 'Extremo'},
			                    		   {'name': 'Acuaticos',
			                    			   'value': 'Acuaticos'},
				                    		   {'name': 'Motor',
				                    			   'value': 'Motor'},
					                    		   {'name': 'Montaña',
					                    			   'value': 'Montaña'},
						                    		   {'name': 'Otros',
						                    			   'value': 'Otros'}
		                    		   ];

		$scope.feed.especialidad = $scope.feed.especialidades[0].value;
		$scope.feed.especialidadU = $scope.feed.especialidades[0].value;

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
	               return Math.ceil($scope.pagMonitores.usuario.length / $scope.paginacion.itemsPerPage) - 1;
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
	  $scope.pagMonitores = {};



	$scope.monitores = MonitorGService.retrieveAllActivos()
	.success(function(data){
		$scope.monitores = data.usuario;	
		$scope.pagMonitores.usuario = data.usuario;
	});

	self.create = function (nombre,apellidos,dni, correo, telefono, username, password,especialidad) {

		var bool = validator($scope, "create");

		if(bool == true){
			MonitorGService.create(nombre,apellidos,dni, correo, telefono, username, password, especialidad)
			.success(function (data) {
				borraCampos($scope);
				MonitorGService.retrieveAll()
				.success(function (data) {
					$scope.monitores = data.usuario;
	    			$scope.pagMonitores.usuario = data.usuario;
					$scope.createForm.$setPristine(); 
				});
			});
			$('#create').modal('hide');
		};
	};

	self.cambiaEstado = function (dni) {
		MonitorGService.cambiaEstado(dni)
		.success(function (data) {
			MonitorGService.retrieveAllActivos()
			.success(function (data) {
				$scope.monitores = data.usuario;
    			$scope.pagMonitores.usuario = data.usuario;
				$scope.class1 = "active";
				$scope.class2 = "";
			});
		});
	};

	self.retreiveContact = function(dni) {
		MonitorGService.retrieveContact(dni)
		.success(function(data) {
			$scope.currentMonitor = data;
		});
	};

	self.update = function(nombre,apellidos,dni, correo, telefono, username, password, especialidad) {
		var bool = validator($scope, "update");

		if(bool == true){
			MonitorGService.update(nombre,apellidos,dni, correo, telefono,username, password,especialidad)
			.success(function(data) {

				MonitorGService.retrieveAllActivos()
				.success(function (data) {
					$scope.monitores = data.usuario;
	    			$scope.pagMonitores.usuario = data.usuario;
				});
				$('#actualizar').modal('hide');
			});

		};
	};

}]);



//FUNCION SERVICIOS WEB MONITORES
app.service('MonitorGService', ['$http', function($http) {

	this.create = function(nombre,apellidos,dni, correo, telefono,username, password, especialidad) {
		dato = {'usuario': {'nombre': nombre, 'apellidos': apellidos, 'dni': dni, 'email':correo,
			'telefono':telefono, 'username':username, 'password':password, 'especialidad':especialidad, 'rol':'M', 'estado':'A'}};
		
		var url = app.baseURI +"anyadir/"+ dni;
		return $http.put(url, dato);
	}

	this.retrieveAllActivos = function() {
		var url = app.baseURI+"activos";
		return $http.get(url);
	}
	
	this.retrieveAllNoDisponibles = function() {
		var url = app.baseURI+"nodisponibles";
		return $http.get(url);
	}

	this.retrieveContact = function(dni) {
		var url = app.baseURI + dni;
		return $http.get(url);
	}

	this.cambiaEstado = function(dni) {
		var url = app.baseURI + dni;
		var dato = {'dni': dni};
		return $http.put(url, dato);
	}

	this.update = function (monitor) {
		var url = app.baseURI +"actualizar/" + monitor.usuario.dni;
		return $http.put(url, monitor);
	};
}]);


