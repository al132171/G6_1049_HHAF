/**
 * Created by oscar on 13/11/14.
 */

//Borra los datos de los campos del formulario
function borraCampos($scope) {
	document.getElementById("createForm").reset();
}

//Comprobar que no existen errores en los campos del formulario
function validator($scope) { 
    if ($scope.createForm.$valid) {
	      return true;
	    } else {
	      return false;
	    }
};




(function() {
	
	
    var actividadesG = angular.module('actividadesG', []); //Define el modulo de la aplicación (ng-app="actividadesG") para todo el fichero
    actividadesG.baseURI = 'http://localhost:8080/Natureadventure/actividades/';
    

    actividadesG.controller('ActividadesCtrl', ['$scope', 'ActividadGService', function ($scope, ActividadGService) { //Inyecta los atributos
    	
    	var self = this;
    	
//    	Limpiar el formulario para que si sales de la ventana modal se limpien los mensajes de error y formato

    	$scope.resetForm = function(user){ 
		 var defaultForm = {
			      nombre: "", duracion: "", horaInicio: "", fechaInicio: "", fechaFin: "",
        		descripcion: "", nivel: "", precio: "", participantesMax: "", participantesMin: "",lugar: "", imagen:""
			  };
    		 $scope.createForm.$setPristine();
		 $scope.user = defaultForm;
    	};
    	
//    	Comprueba en tiempo real si el nombre introducido ya existe y mostrar o quitar el error
    	$scope.comprobar = function(nombre){
    		ActividadGService.retrieveContact(nombre).success(function(){
				alert("esta" + nombre);
    			$scope.createForm.nombre.$setValidity("nombre", false);
    		}).error(function(){
    				alert("no está"+nombre);
    				$scope.createForm.nombre.$setValidity("nombre", true);
    		});
    	};
		
    	
//    	Comprueba si los datos introducidos son números o "-"
    	 $scope.fechaInicio1 = { 
    		        //word: /[^\d|\-+|\.+]/g
    			 word: /^([0-9-])*$/
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

	
//	Comprueba que la fecha inicio no sea mayor que la final
	$scope.compararFechas =  function(fechaInicio, fechaFin){
		var diaInicio = fechaInicio.substring(0, 2);
		var diaFin = fechaFin.substring(0, 2);
		
		var mesInicio = fechaInicio.substring(3, 5);
		var mesFin = fechaFin.substring(3, 5);
		
		var añoInicio = fechaInicio.substring(6);
		var añoFin = fechaFin.substring(6);


		if(añoInicio > añoFin){$scope.createForm.fechaFin.$setValidity("fechaFin", false);}
		else if(mesInicio > mesFin){$scope.createForm.fechaFin.$setValidity("fechaFin", false);}
		else if(diaInicio > diaFin){ alert("dia");$scope.createForm.fechaFin.$setValidity("fechaFin", false);}
		else{ $scope.createForm.fechaFin.$setValidity("fechaFin", true);}
	};
		
    	
        $scope.actividades = ActividadGService.retrieveAll()
            .success(function(data){
                $scope.actividades = data.actividad;
            });

        self.create = function (nombre, duracion, horaInicio, fechaInicio, fechaFin,
        		descripcion, nivel, precio, participantesMax, participantesMin, lugar, imagen) {
        	
        	var bool = validator($scope);
        	alert(bool);
        	
        	if(bool == true){
        		
	            ActividadGService.create(nombre, duracion, horaInicio, fechaInicio, fechaFin,
	            		descripcion, nivel, precio, participantesMax, participantesMin, lugar, imagen)
	                .success(function (data) {
	                	borraCampos($scope);
	                	alert("borraCampos");
	                    ActividadGService.retrieveAll()
	                        .success(function (data) {
	                            $scope.actividades = data.actividad;
				    $scope.createForm.$setPristine(); 
	                    });
	                });
              $('#create').modal('hide');
	        };
        };
        
        self.delete = function (nombre) {
            ActividadGService.delete(nombre)
                .success(function (data) {
                    ActividadGService.retrieveAll()
                        .success(function (data) {
                            $scope.actividades = data.actividad;
                    });
                });
        };

        self.retreiveContact = function(nombre) {
            ActividadGService.retrieveContact(nombre)
                .success(function(data) {
                    console.log(data);
                    $scope.currentActividad = data;
                });
        };

        self.update = function(nombre, duracion, horaInicio, fechaInicio, fechaFin,
        		descripcion, nivel, precio, participantesMax, participantesMin,lugar, imagen) {
							
            ActividadGService.update(nombre, duracion, horaInicio, fechaInicio, fechaFin,
            		descripcion, nivel, precio, participantesMax, participantesMin, lugar, imagen)
                .success(function(data) {
				
                    ActividadGService.retrieveAll()
                        .success(function (data) {
                            $scope.actividades = data.actividad;
                        });
                });
			 $('#actualizar').modal('hide');
        };

    }]);

    //FUNCION SERVICIOS WEB
    actividadesG.service('ActividadGService', ['$http', function($http) {
        
    	this.create = function(nombre, duracion, horaInicio, fechaInicio, fechaFin,
        		descripcion, nivel, precio, participantesMax, participantesMin, lugar, imagen) {
            dato = {'actividad': {'nombre': nombre, 'duracion': duracion, 'horaInicio': horaInicio, 
            	'fechaInicio':fechaInicio, 'fechaFin':fechaFin, 'descripcion':descripcion,
            	'nivel':nivel, 'precio':precio, 'participantesMax':participantesMax, 'participantesMin':participantesMin,
            	'lugar':lugar, 'imagen':imagen}};
            var url = actividadesG.baseURI + nombre;
            return $http.put(url, dato);
        }

        this.retrieveAll = function() {
            return $http.get(actividadesG.baseURI);
        }

        this.retrieveContact = function(nombre) {
            var url = actividadesG.baseURI + nombre;
            return $http.get(url);
        }

        this.delete = function(nombre) {
            var url = actividadesG.baseURI + nombre;
            var dato = {'nombre': nombre};
            return $http.delete(url, dato);
        }

        this.update = function (actividad) {
            var url = actividadesG.baseURI + actividad.actividad.nombre;
            return $http.put(url, actividad);
        };
    }]);

})();
