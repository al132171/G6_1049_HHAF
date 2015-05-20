angular.module('mwl.calendar', []);

var app = angular.module('app', ['mwl.calendar', 'ui.bootstrap'])

// Definición del controlador del Calendario
app.controller('MainCtrl', ['$scope', '$modal', 'CalendarioService', 'moment',
                            function ($scope, $modal, CalendarioService, moment) {
	
	app.baseURI = 'http://localhost:8080/Natureadventure/monitor/reservas/';
	var self = this;

    var currentYear = moment().year();
    var currentMonth = moment().month();
	
	$scope.calendarView = 'month';
    $scope.calendarDay = new Date();
    $scope.events = [
	
	];
    
    /*
	//Datos de reservas de prueba
 
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
	*/

	// Obtener el nombre del monitor que ha iniciado sesión
	$scope.username =  window.location.href.slice(window.location.href.indexOf('?') + 1).split('=')[1];
	
    // Obtener las reservas a supervisar por el monitor que ha iniciado sesión
    CalendarioService.retrieveAllSupervisar()
	.success(function(data) {

		// Rellenar el vector de eventos a mostrar en el calendario
		for (var i = 0; i < data.reserva.length; i++) {
			var tmpEvent = {
				title: data.reserva[i].actividad.nombre,
				type: "important",
				starts_at: new Date(data.reserva[i].fechaActividad.split("-")[2],
									data.reserva[i].fechaActividad.split("-")[1]-1,
									data.reserva[i].fechaActividad.split("-")[0],
									data.reserva[i].horaInicio.split("-")[0],
									data.reserva[i].horaInicio.split("-")[0]),
				ends_at: new Date(data.reserva[i].fechaActividad.split("-")[2],
								  data.reserva[i].fechaActividad.split("-")[1]-1,
								  data.reserva[i].fechaActividad.split("-")[0],
								  data.reserva[i].horaInicio.split("-")[0],
								  data.reserva[i].horaInicio.split("-")[0])
			}
			$scope.events.push(tmpEvent);
		}
	});

    $scope.setCalendarToToday = function() {
      $scope.calendarDay = new Date();
    };

    $scope.toggle = function($event, field, event) {
      $event.preventDefault();
      $event.stopPropagation();

      event[field] = !event[field];
    };

  }]);


// Servicios web Calendario

app.service('CalendarioService', ['$http', function($http) {
	
	this.retrieveAllSupervisar = function() {
		var user = window.location.href.slice(window.location.href.indexOf('?') + 1).split('=')[1];
		var url = app.baseURI+"supervisar/"+user;
		return $http.get(url);
	};
	
	this.retrieveReservasAceptadas = function() {
		var url = app.baseURI;
		return $http.get(url);
	};

}]);
