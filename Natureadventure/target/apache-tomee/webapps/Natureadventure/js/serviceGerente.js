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
	}else if(tipo == "update"){
		if ($scope.updateForm.$valid) {
			return true;
		} else {
			return false;
		}
	}else if (tipo=="perfil"){
		if ($scope.perfilForm.$valid) {
			return true;
		} else {
			return false;
		}
	}
};


	var app = angular.module("app", []); //Define el modulo de la aplicación (ng-app="app") para todo el fichero
	
	app.controller('ActividadesCtrl', ['$scope', 'ActividadGService', '$rootScope', '$location',
	                                            function ($scope, ActividadGService, $rootScope, $location) { //Inyecta los atributos
		app.baseURI = 'http://localhost:8080/Natureadventure/gerente/actividades/';
		

		var self = this;
		
	    $scope.class1 = "active";
	    
		$scope.changeClass = function(tipo){
	        if ($scope.class1 === "active" && tipo=="2"){
	            $scope.class2 = "active";
	            $scope.class1 = "";
	            $scope.add = true; //esconde el botón de añadir
	            $scope.actividades = ActividadGService.retrieveAllArchivadas()
	    		.success(function(data){
	    			$scope.actividades = data.actividad;
	    		});
	        }
	         else if($scope.class2 == "active" && tipo=="1"){
	            $scope.class2 = "";
	            $scope.class1 = "active";
	            $scope.add = false; // muestra el botón de añadir
	            $scope.actividades = ActividadGService.retrieveAllActivas()
	    		.success(function(data){
	    			$scope.actividades = data.actividad;
	    		});
	         }
	    };
		
		$scope.username =  window.location.href.slice(window.location.href.indexOf('?') + 1).split('=')[1];
//		Limpiar el formulario para que si sales de la ventana modal se limpien los mensajes de error y formato
		$scope.resetForm = function(user){ 
			var defaultForm = {
					nombre: "", duracion: "", horaInicio: "", fechaInicio: "", fechaFin: "",
					descripcion: "", nivel: "", precio: "", participantesMax: "", participantesMin: "",lugar: "", imagen:""
			};
			$scope.createForm.$setPristine();
			$scope.user = defaultForm;
		};

//		Comprueba en tiempo real si el nombre introducido ya existe y mostrar o quitar el error
		$scope.comprobar = function(nombre){

			ActividadGService.retrieveContact(nombre).success(function(data){
				//alert("esta" + nombre);
				$scope.createForm.nombre.$setValidity("nombre", false);
				$scope.updateForm.nombreU.$setValidity("nombreU", false);		  
				if(data.actividad.nombre == nombre){

					//alert(data.actividad.nombre);
				}
			}).error(function(){
				//alert("no está"+nombre);
				$scope.createForm.nombre.$setValidity("nombre", true);
				$scope.updateForm.nombreU.$setValidity("nombreU", true);

			});
		};



		/*$scope.$watch(function() {
		  return $scope.currentActividad.actividad.nombre;
		}, function(newValue, oldValue) {
		 alert("change detected: " + newValue +"--"+ oldValue);
		});*/


//		Comprueba si los datos introducidos son números o "-"
		$scope.fechaInicio1 = { 
				//word: /[^\d|\-+|\.+]/g
				word: /^([0-9-])*$/
		};

		//    	Comprueba si los datos introducidos son números o "-"
		$scope.horaInicio1 = { 
				//word: /[^\d|\-+|\.+]/g
				word: /^([0-9:])*$/
		};

//		Con estas tres funciones evito el espacio en blanco por defecto que pone ng-model en los select
		$scope.feed = {};
		$scope.feed.niveles = [
		                       {'name': 'Bajo',
		                    	   'value': 'Bajo'},
		                    	   {'name': 'Medio',
		                    		   'value': 'Medio'},
		                    		   {'name': 'Alto',
		                    			   'value': 'Alto'}
		                    		   ];

		$scope.feed.nivel = $scope.feed.niveles[0].value;
		$scope.feed.nivelU = $scope.feed.niveles[0].value;
		
		$scope.feed.categorias = [
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

		$scope.feed.categoria = $scope.feed.categorias[0].value;
		$scope.feed.categoriaU = $scope.feed.categorias[0].value;

//		Comprueba que la fecha inicio no sea mayor que la final
		$scope.compararFechas =  function(fechaInicio, fechaFin){
			var diaInicio = fechaInicio.substring(0, 2);
			var diaFin = fechaFin.substring(0, 2);

			var mesInicio = fechaInicio.substring(3, 5);
			var mesFin = fechaFin.substring(3, 5);

			var añoInicio = fechaInicio.substring(6);
			var añoFin = fechaFin.substring(6);


			if(añoInicio > añoFin){ 
				$scope.createForm.fechaFin.$setValidity("fechaFin", false); 
				$scope.updateForm.fechaFinU.$setValidity("fechaFinU", false); 
			}
			else if( (mesInicio > mesFin) && (añoInicio == añoFin) ){
				$scope.createForm.fechaFin.$setValidity("fechaFin", false);
				$scope.updateForm.fechaFinU.$setValidity("fechaFinU", false);
			}
			else if( (diaInicio > diaFin) && (mesInicio == mesFin) ){ 
				$scope.createForm.fechaFin.$setValidity("fechaFin", false);
				$scope.updateForm.fechaFinU.$setValidity("fechaFinU", false);
			}
			else{ 
				$scope.createForm.fechaFin.$setValidity("fechaFin", true);
				$scope.updateForm.fechaFinU.$setValidity("fechaFinU", true);
			}
		};

		//Para la primera vez que carge la página en activas
		$scope.actividades = ActividadGService.retrieveAllActivas()
		.success(function(data){
			$scope.actividades = data.actividad;
			
		});

		self.create = function (nombre, duracion, horaInicio, fechaInicio, fechaFin,
				descripcion, nivel, precio, participantesMax, participantesMin, lugar, imagen, categoria) {

			var bool = validator($scope, "create");

			if(bool == true){

				ActividadGService.create(nombre, duracion, horaInicio, fechaInicio, fechaFin,
						descripcion, nivel, precio, participantesMax, participantesMin, lugar, imagen, categoria)
						.success(function (data) {
							borraCampos($scope);
							ActividadGService.retrieveAllActivas()
							.success(function (data) {
								$scope.actividades = data.actividad;
								$scope.createForm.$setPristine(); 
							});
						});
				$('#create').modal('hide');
			};
		};

		self.delete = function (nombre) {
			ActividadGService.delete(nombre.actividad.nombre)
			.success(function (data) {
				ActividadGService.retrieveAllActivas()
				.success(function (data) {
					$scope.actividades = data.actividad;
					$scope.class1 = "active";
					$scope.class2 = "";
				});
			});
		};

		self.retreiveContact = function(nombre) {
			ActividadGService.retrieveContact(nombre)
			.success(function(data) {
				$scope.currentActividad = data;

			});
		};

		self.update = function(nombre, duracion, horaInicio, fechaInicio, fechaFin,
				descripcion, nivel, precio, participantesMax, participantesMin,lugar, imagen) {

			var bool = validator($scope, "update");

			if(bool == true){
				ActividadGService.update(nombre, duracion, horaInicio, fechaInicio, fechaFin,
						descripcion, nivel, precio, participantesMax, participantesMin, lugar, imagen)
						.success(function(data) {

							ActividadGService.retrieveAllActivas()
							.success(function (data) {
								$scope.actividades = data.actividad;
							});
							$('#actualizar').modal('hide');
						});

			};
		};

	}]);
	
//	*************** RESERVAS CONTROLLER **********************
	
	app.controller('ReservasCtrl', ['$scope', 'ReservasService',
                                       function ($scope, ReservasService) { 
		
		app.baseURI = 'http://localhost:8080/Natureadventure/gerente/reservas/';
		var self = this;
		//$scope.username = window.location.href.slice(window.location.href.indexOf('?') + 1).split('=')[1];;
		$scope.username = window.location.href.slice(window.location.href.indexOf('?') + 1).split('=')[1];;

		$scope.selected = '';
		$scope.class1 = "active";

		$scope.changeClass = function(tipo){
	        if ($scope.class1 === "active" && tipo=="2"){
	            $scope.class2 = "active";
	            $scope.class1 = "";
	            $scope.reservas = ReservasService.retrieveReservasAceptadas()
	    		.success(function(data){
	    			$scope.reservas = data.reserva;
	    		});
	        }
	         else if($scope.class2 == "active" && tipo=="1"){
	            $scope.class2 = "";
	            $scope.class1 = "active";
	            $scope.reservas = ReservasService.retrieveReservasPendientes()
	    		.success(function(data){
	    			$scope.reservas = data.reserva;
	    			$scope.pendientes = Object.keys(data.reserva).length;
	    		});
	         }
	    };
		
	    //Primera vez que carga la página
		$scope.reservas = ReservasService.retrieveReservasPendientes()
		.success(function(data) {
			$scope.reservas = data.reserva;
			$scope.pendientes = Object.keys(data.reserva).length;
		});
		
		self.retrieveReserva = function(dni, fechaReserva) { //TODO: implementar servidor
			ReservasService.retrieveReserva(dni, fechaReserva)
			.success(function(data) {
				$scope.currentReserva = data;
			});
		};
			
		self.retrieveReservaMonitor = function(dni, fechaReserva){
			ReservasService.retrieveReserva(dni, fechaReserva)
			.success(function(data) {
				$scope.currentReserva = data;
				ReservasService.retrieveMonitor(data.reserva.actividad.categoria)
				.success(function(data){
					$scope.monitores = data.usuario;
					});
		});
		};
		
		self.aceptarReserva = function(currentReserva, dniMonitor){
			ReservasService.updateReserva(currentReserva, dniMonitor).success(function (data){
				ReservasService.retrieveReservasPendientes()
				.success(function (data) {
					$scope.reservas = data.reserva;
					$scope.pendientes = Object.keys(data.reserva).length;
			});
		});
		};
		
		
		
		self.delete = function (currentReserva){
		ReservasService.delete(currentReserva.reserva.dni, currentReserva.reserva.fechaReserva) 
		.success(function (data) {
			ReservasService.retrieveReservasPendientes()
			.success(function (data) {
				$scope.reservas = data.reserva;
				$scope.pendientes = Object.keys(data.reserva).length;
				$scope.class1 = "active";
				$scope.class2 = "";
			});
		});
	};
	
	}]);
	
//	********* LOGIN CONTROLLER *********
	app.controller('LoginCtrl', ['$scope', '$rootScope', 'LoginService', 
	                               function ($scope, $rootScope, LoginService) { //Inyecta los atributos
		app.baseURI = 'http://localhost:8080/Natureadventure/login/';
		var self = this;
	
		self.login = function(username, password){
			LoginService.retrieveUser(username, password)
			.success(function (data) {
				//myService.set(username);
				if(data.usuario.rol == "G")
					window.location.href="http://localhost:8080/Natureadventure/html/gerente/dashboard.html#/user?id="+username;
				else if(data.usuario.rol == "M")
					window.location.href="http://localhost:8080/Natureadventure/html/monitor/dashboard.html#/user?id="+username;
			}).error(function(data){
				$scope.loginForm.password.$setValidity("password", false);
			});
		};

	}]);
	
//	***************PERFIL CONTROLLER**********************
	
	app.controller('PerfilCtrl', ['$scope', 'PerfilService',
                                       function ($scope, PerfilService) { 
		app.baseURI = 'http://localhost:8080/Natureadventure/usuario/perfil/';
		$scope.username = window.location.href.slice(window.location.href.indexOf('?') + 1).split('=')[1];;

		var self = this;
		
		$scope.telefono = { 
				word: /^([0-9-])*$/
		};

	
		
		$scope.currentUsuario =	PerfilService.retrieveUser()
			.success(function(data) {
				$scope.currentUsuario = data;
				$scope.exito = true;
				$scope.error = true;
			});
		
		self.editar = function(nombre, apellidos, email, telefono, password) {
			var bool = validator($scope, "perfil");
			if (bool){
				PerfilService.updateUser(nombre, apellidos, email, telefono, password).success(function(){
					$scope.exito = false;
					$scope.error = true;
				}
				);
			}else{
				$scope.exito = true;
				$scope.error = false;
			}		
		};
		
		
	}]);

/*##############################################################################################################
############################################SERVICIOS WEB#######################################################
################################################################################################################*/
	
//	SERVICIOS WEB ACTIVIDADES
	app.service('ActividadGService', ['$http', function($http) {

		this.create = function(nombre, duracion, horaInicio, fechaInicio, fechaFin,
				descripcion, nivel, precio, participantesMax, participantesMin, lugar, imagen, categoria) {
			dato = {'actividad': {'nombre': nombre, 'duracion': duracion, 'horaInicio': horaInicio, 
				'fechaInicio':fechaInicio, 'fechaFin':fechaFin, 'descripcion':descripcion,
				'nivel':nivel, 'precio':precio, 'participantesMax':participantesMax, 'participantesMin':participantesMin,
				'lugar':lugar, 'imagen':imagen, 'categoria':categoria}};
			var url = app.baseURI + nombre;
			return $http.put(url, dato);
		}

		this.retrieveAllActivas = function() {
			//var url = app.baseURI+"activas";
			return $http.get(app.baseURI);
		}
		
		this.retrieveAllArchivadas = function(){
			var url = app.baseURI+"archivadas";
			return $http.get(url);
		}

		this.retrieveContact = function(nombre) {
			var url = app.baseURI + nombre;
			return $http.get(url);
		}

		this.delete = function(nombre) {
			var url = app.baseURI + nombre;
			var dato = {'nombre': nombre};
			return $http.delete(url, dato);
		}

		this.update = function (actividad) {
			var url = app.baseURI + actividad.actividad.nombre;
			return $http.put(url, actividad);
		};
	}]);
	
//	SERVICIOS WEB RESERVAS
	app.service('ReservasService', ['$http', function($http) {
		this.retrieveReservasPendientes = function() {
			var url = app.baseURI+"pendientes";
			return $http.get(url);
		};
		
		this.retrieveReservasAceptadas = function() {
			var url = app.baseURI+"aceptadas";
			return $http.get(url);
		};
		
		this.retrieveReserva = function(dni, fechaReserva) {
			var url = app.baseURI + dni+"/"+fechaReserva;
			return $http.get(url);
		}
		
		this.retrieveMonitor = function(especialidad){
			var url = app.baseURI + especialidad;
			return $http.get(url);
		};
		
		this.updateReserva = function(currentReserva,dniMonitor){
			var url = app.baseURI+dniMonitor;
			return $http.put(url, currentReserva);
		};
		
		this.delete = function(dni, fechaReserva) {
			var url = app.baseURI + dni+"/"+fechaReserva;
			var dato = {'dni': dni, 'fechaReserva':fechaReserva};
			return $http.delete(url, dato);
		};
		
		
	}]);
	
//	SERVICIOS WEB LOGIN
	app.service('LoginService', ['$http', function($http) {
		this.retrieveUser = function(username, password) {
			var url = app.baseURI + username+"/"+password;
			return $http.get(url);
		};
	}]);
	
//	SERVICIOS WEB PERFIL
	app.service('PerfilService', ['$http', function($http) {
		this.retrieveUser = function() {
			var user = window.location.href.slice(window.location.href.indexOf('?') + 1).split('=')[1];
			var url = app.baseURI + user;
			return $http.get(url);
		};
		
		this.updateUser = function(currentUser){
			var url = app.baseURI + currentUser.usuario.username;
			return $http.put(url, currentUser);
		};

	}]);
