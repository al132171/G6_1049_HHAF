/**
 * Calendario de actividades reservadas
 * 
 * app.js
 */

// Definición de los módulos de aplicación

angular.module('mwl.calendar', []);

var app = angular.module('app', ['mwl.calendar', 'ui.bootstrap'])


// Controlador del calendario

app.controller('MainCtrl', ['$scope', '$modal', 'CalendarioService', 'moment',
                            function ($scope, $modal, CalendarioService, moment) {
	
	app.baseURI = 'http://localhost:8080/Natureadventure/gerente/reservas/aceptadas';
	var self = this;

    var currentYear = moment().year();
    var currentMonth = moment().month();
    
    // Datos de reservas de prueba
    
    var reservas = {"reserva":
    				[{"fechaActividad": "8-05-2015", "fechaReserva": "8-05-2015", "cantidadPersonas": 4,
    				"precio": 35, "nombre": "Monitor1","apellidos": "ApellidosMonitor1", "dni": "20875643S",
    				"correo": "correo@monitor1.com", "telefono": 654321987, "estado": "aceptada",
    				"actividad": 1, "usuario": 1, "contrato": "contrato"},
    				
    				{"fechaActividad": "15-05-2015", "fechaReserva": "15-05-2015", "cantidadPersonas": 4,
        				"precio": 35, "nombre": "Monitor1","apellidos": "ApellidosMonitor1", "dni": "20875643S",
        				"correo": "correo@monitor1.com", "telefono": 654321987, "estado": "aceptada",
        				"actividad": 1, "usuario": 1, "contrato": "contrato"},
        				
    				{"fechaActividad": "12-05-2015", "fechaReserva": "12-05-2015", "cantidadPersonas": 4,
        				"precio": 35, "nombre": "Monitor2","apellidos": "ApellidosMonitor2", "dni": "20875643S",
        				"correo": "correo@monitor2.com", "telefono": 654321987, "estado": "aceptada",
        				"actividad": 1, "usuario": 1, "contrato": "contrato"},
        				
    				{"fechaActividad": "18-05-2015", "fechaReserva": "18-05-2015", "cantidadPersonas": 4,
        				"precio": 35, "nombre": "Monitor2","apellidos": "ApellidosMonitor2", "dni": "20875643S",
        				"correo": "correo@monitor2.com", "telefono": 654321987, "estado": "aceptada",
        				"actividad": 1, "usuario": 1, "contrato": "contrato"},
        				
    				{"fechaActividad": "22-05-2015", "fechaReserva": "22-05-2015", "cantidadPersonas": 4,
        				"precio": 35, "nombre": "Monitor2","apellidos": "ApellidosMonitor2", "dni": "20875643S",
        				"correo": "correo@monitor2.com", "telefono": 654321987, "estado": "aceptada",
        				"actividad": 1, "usuario": 1, "contrato": "contrato"}]}

    /*
    // Obtención de las reservas aceptadas
    
    var reservas = CalendarioService.retrieveReservasAceptadas()
    .success(function(data) {
    	 alert("Éxito en el GET. Datos: "+data);
		this.reservas = data.reserva;
	})
	.error(function(data, status, headers, config) {
        alert("Ha fallado la petición GET. Estado HTTP: "+status);
	});
	*/
    
    // Configuración inicial del calendario
    
    $scope.calendarView = 'month';
    $scope.calendarDay = new Date();
    $scope.events = [
	
	];

	var tmpCurrentMonitor = window.location.href.slice(window.location.href.indexOf('?') + 1).split('=')[1];

	if (tmpCurrentMonitor == undefined) {
		window.location.href="http://localhost:8080/Natureadventure/calendario/index.html#/user?id=Monitor1";
	}
	var currentMonitor = window.location.href.slice(window.location.href.indexOf('?') + 1).split('=')[1];

	for (var i = 0; i < reservas.reserva.length; i++) {
		if (reservas.reserva[i].nombre == currentMonitor) {
			var tmpEvent = {
				title: "Reserva para "+currentMonitor,
				type: "important",
				starts_at: new Date(reservas.reserva[i].fechaActividad.split("-")[2],
									reservas.reserva[i].fechaActividad.split("-")[1]-1,
									reservas.reserva[i].fechaActividad.split("-")[0],
									8, 30),
				ends_at: new Date(reservas.reserva[i].fechaActividad.split("-")[2],
									reservas.reserva[i].fechaActividad.split("-")[1]-1,
									reservas.reserva[i].fechaActividad.split("-")[0],
									9,30)
			}
			$scope.events.push(tmpEvent);
		}
	}

    $scope.setCalendarToToday = function() {
      $scope.calendarDay = new Date();
    };

    $scope.toggle = function($event, field, event) {
      $event.preventDefault();
      $event.stopPropagation();

      event[field] = !event[field];
    };

}]);

// Servicio web del calendario

app.service('CalendarioService', ['$http', function($http) {
	
	this.retrieveReservasAceptadas = function() {
		var url = app.baseURI;
		return $http.get(url);
	};

}]);
