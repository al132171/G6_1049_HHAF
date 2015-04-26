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
    		});
        }
         else if($scope.class2 == "active" && tipo=="1"){
            $scope.class2 = "";
            $scope.class1 = "active";
            $scope.monitores = MonitorGService.retrieveAllActivos()
    		.success(function(data){
    			$scope.monitores = data.usuario;
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





	$scope.monitores = MonitorGService.retrieveAllActivos()
	.success(function(data){
		$scope.monitores = data.usuario;	
	});

	self.create = function (nombre,apellidos,dni, correo, telefono, username, password,especialidad) {

		var bool = validator($scope, "create");

		if(bool == true){
			alert("correo "+especialidad);
			MonitorGService.create(nombre,apellidos,dni, correo, telefono, username, password, especialidad)
			.success(function (data) {
				borraCampos($scope);
				MonitorGService.retrieveAll()
				.success(function (data) {
					$scope.monitores = data.usuario;
					$scope.createForm.$setPristine(); 
				});
			});
			$('#create').modal('hide');
		};
	};

	self.delete = function (dni) {
		MonitorGService.delete(dni)
		.success(function (data) {
			MonitorGService.retrieveAll()
			.success(function (data) {
				$scope.monitores = data.usuario;
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
		alert("update");
		var bool = validator($scope, "update");

		if(bool == true){
			MonitorGService.update(nombre,apellidos,dni, correo, telefono,username, password,especialidad)
			.success(function(data) {

				MonitorGService.retrieveAll()
				.success(function (data) {
					$scope.monitores = data.usuario;
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
		alert(url);
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

	this.delete = function(dni) {
		var url = app.baseURI + dni;
		var dato = {'dni': dni};
		return $http.delete(url, dato);
	}

	this.update = function (monitor) {
		var url = app.baseURI +"actualizar/" + monitor.usuario.dni;
		alert(url);
		return $http.put(url, monitor);
	};
}]);


