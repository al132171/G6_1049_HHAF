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
	
	
	// Filtro para la paginación
    app.filter('offset', function () {
        return function (input, start) {
            start = parseInt(start, 10);
            return input.slice(start);
        };
    });
    
	
	
	app.controller('ActividadesCtrl', ['$scope', 'ActividadGService',
	                                            function ($scope, ActividadGService) { //Inyecta los atributos
		app.baseURI = 'http://localhost:8080/Natureadventure/gerente/actividades/';
		

		var self = this;
		
	    $scope.class1 = "active";
	    //$scope.add = false;
	    
		$scope.changeClass = function(tipo){
	        if ($scope.class1 === "active" && tipo=="2"){
	            $scope.class2 = "active";
	            $scope.class1 = "";
	            //$scope.add = true; //esconde el botón de añadir
	            $scope.actividades = ActividadGService.retrieveAllArchivadas()
	    		.success(function(data){
	    			$scope.actividades = data.actividad;
	    			$scope.pagActividades.actividades = data.actividad;
	    		});
	        }
	         else if($scope.class2 == "active" && tipo=="1"){
	            $scope.class2 = "";
	            $scope.class1 = "active";
	            //$scope.add = false; // muestra el botón de añadir
	            $scope.actividades = ActividadGService.retrieveAllActivas()
	    		.success(function(data){
	    			$scope.actividades = data.actividad;
	    			$scope.pagActividades.actividades = data.actividad;
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
	               return Math.ceil($scope.pagActividades.actividades.length / $scope.paginacion.itemsPerPage) - 1;
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
       $scope.pagActividades = {};

		//Para la primera vez que carge la página en activas
		$scope.actividades = ActividadGService.retrieveAllActivas()
		.success(function(data){
			$scope.pagActividades.actividades = data.actividad;
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
								$scope.pagActividades.actividades = data.actividad;
								$scope.actividades = data.actividad;
								$scope.createForm.$setPristine(); 
							});
						});
				$('#create').modal('hide');
			};
		};

		self.cambiaEstado = function (nombre) {
			ActividadGService.cambiaEstado(nombre.actividad.nombre)
			.success(function (data) {
				ActividadGService.retrieveAllActivas()
				.success(function (data) {
					$scope.pagActividades.actividades = data.actividad;
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
								$scope.pagActividades.actividades = data.actividad;

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
	    			$scope.pagReservas.reservas = data.reserva;
	    		});
	        }
	         else if($scope.class2 == "active" && tipo=="1"){
	            $scope.class2 = "";
	            $scope.class1 = "active";
	            $scope.reservas = ReservasService.retrieveReservasPendientes()
	    		.success(function(data){
	    			$scope.reservas = data.reserva;
	    			$scope.pagReservas.reservas = data.reserva;
	    			$scope.pendientes = Object.keys(data.reserva).length;
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
	    
		
	    //Primera vez que carga la página
		$scope.reservas = ReservasService.retrieveReservasPendientes()
		.success(function(data) {
			$scope.reservas = data.reserva;
			$scope.pagReservas.reservas = data.reserva;
			$scope.pendientes = Object.keys(data.reserva).length;
		});
		
		self.retrieveReserva = function(dni, fechaReserva) { 
			ReservasService.retrieveReserva(dni, fechaReserva)
			.success(function(data) {
				$scope.currentReserva = data;
			});
		};
			
		self.retrieveReservaMonitor = function(dni, fechaReserva){
			ReservasService.retrieveReserva(dni, fechaReserva)
			.success(function(data) {
				$scope.currentReserva = data;
				ReservasService.retrieveMonitor(data.reserva.actividad.categoria, data.reserva.fechaActividad)
				.success(function(data){
					if(Object.keys(data.usuario).length != 0){
						$scope.selectMonitor = false;
						$scope.errorMonitor = true;
						$scope.monitores = data.usuario;
					}else{
						$scope.errorMonitor = false;
						$scope.selectMonitor = true;
					}
					});
		});
		};
		
		self.aceptarReserva = function(currentReserva, dniMonitor){
			ReservasService.updateReserva(currentReserva, dniMonitor).success(function (data){
				ReservasService.retrieveReservasPendientes()
				.success(function (data) {
					$scope.reservas = data.reserva;
					$scope.pagReservas.reservas = data.reserva;
					$scope.pendientes = Object.keys(data.reserva).length;
			});
				$('#aceptar').modal('hide');
		});
		};
		
		
		
		self.cambiaEstado = function (currentReserva){
		ReservasService.cambiaEstado(currentReserva.reserva.dni, currentReserva.reserva.fechaReserva) 
		.success(function (data) {
			ReservasService.retrieveReservasPendientes()
			.success(function (data) {
				$scope.reservas = data.reserva;
				$scope.pagReservas.reservas = data.reserva;
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

// **************** NOTICIAS CONTROLLER ******************

	app.controller('NoticiasCtrl', ['$scope', 'NoticiaGService', function ($scope, NoticiasGService) {
		
	app.baseURI = 'http://localhost:8080/Natureadventure/gerente/noticias/';
	
	var self = this;
	
	$scope.class1 = "active";
	
	$scope.username =  window.location.href.slice(window.location.href.indexOf('?') + 1).split('=')[1];
	// Limpiar el formulario para que si sales de la ventana modal se limpien los mensajes de error y formato
	$scope.resetForm = function(user) { 
		var defaultForm = {
				fecha : "", titulo : "", subtitulo : "", descripcion : ""
		};
		$scope.createForm.$setPristine();
		$scope.user = defaultForm;
	};
	
	//Comprueba si los datos introducidos son números o "-"
	$scope.fechaInicio1 = { 
			//word: /[^\d|\-+|\.+]/g
			word: /^([0-9-])*$/
	};
	
	//***************************************
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
	          return Math.ceil($scope.pagActividades.actividades.length / $scope.paginacion.itemsPerPage) - 1;
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
	
	
	//***************************************
	$scope.pagNoticias = {};
	
	// Para la primera vez que carge la página en activas
	$scope.noticias = NoticiaGService.retrieveAll()
	.success(function(data) {
		$scope.pagNoticias.noticias = data.noticia;
		$scope.noticias = data.noticia;
	});
	
	self.create = function (fecha, titulo, subtitulo, descripcion) {
	
		var bool = validator($scope, "create");
	
		if(bool == true) {
			NoticiaGService.create($scope.username, fecha, titulo, subtitulo, descripcion)
					.success(function (data) {
						borraCampos($scope);
						NoticiaGService.retrieveAll()
						.success(function (data) {
							$scope.pagNoticias.noticias = data.noticia;
							$scope.noticias = data.noticia;
							$scope.createForm.$setPristine(); 
						});
					});
			$('#create').modal('hide');
		};
	};
	
	self.update = function (fecha, titulo, subtitulo, descripcion) {
	
		var bool = validator($scope, "update");
	
		if(bool == true) {
			NoticiaGService.update(fecha, titulo, subtitulo, descripcion)
					.success(function(data) {
	
						NoticiaGService.retrieveAll()
						.success(function (data) {
							$scope.noticias = data.noticia;
							$scope.pagNoticias.noticias = data.noticia;
	
						});
						$('#actualizar').modal('hide');
					});
		};
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

		this.cambiaEstado = function(nombre) {
			var url = app.baseURI +"estado/"+ nombre;
			var dato = {'nombre': nombre};
			return $http.put(url, dato);
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
			var url = app.baseURI +"reserva/"+dni+"/"+fechaReserva;
			return $http.get(url);
		}
		
		this.retrieveMonitor = function(especialidad, fechaActividad){
			var url = app.baseURI + especialidad+"/"+fechaActividad;
			return $http.get(url);
		};
		
		this.updateReserva = function(currentReserva,dniMonitor){
			var url = app.baseURI+dniMonitor;
			return $http.put(url, currentReserva);
		};
		
		this.cambiaEstado = function(dni, fechaReserva) {
			var url = app.baseURI + dni+"/"+fechaReserva;
			var dato = {'dni': dni, 'fechaReserva':fechaReserva};
			return $http.put(url, dato);
		};
		
		
	}]);
	
//	SERVICIOS WEB LOGIN
	app.service('LoginService', ['$http', function($http) {
		this.retrieveUser = function(username, password) {
			var url = app.baseURI + username+"/"+password;
			return $http.get(url);
		};
	}]);

//	SERVICIOS WEB NOTICIAS
	app.service('NoticiaGService', ['$http', function($http) {

		this.create = function(user, fecha, titulo, subtitulo, descripcion) {
			dato = {'noticia': {'user': username, 'fecha': fecha, 'titulo': titulo,
				'subtitulo': subtitulo, 'descripcion': descripcion}};
			var url = app.baseURI + user;
			return $http.put(url, dato);
		}

		this.retrieveAll = function() {
			return $http.get(app.baseURI);
		}

		this.update = function (noticia) {
			var url = app.baseURI + noticia.noticia.user;
			return $http.put(url, noticia);
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
