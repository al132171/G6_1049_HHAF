
var app = angular.module("app", ['angular-input-stars']); 

	
	

	
	app.controller('ComentariosCtrl', ['$scope', '$rootScope', 'ComentariosService', 
    function ($scope, $rootScope, ComentariosService) { //Inyecta los atributos
app.baseURI = 'http://localhost:8080/Natureadventure/comentarios/';
var self = this;

	$scope.comentarios = ComentariosService.retrieveAll()
	.success(function(data){
		$scope.comentarios=data.comentario;
		
	});
	self.create = function (idActividad,idUsuario,contenido,puntuacion) {

	ComentariosService.create(idActividad,idUsuario,contenido,puntuacion)
			.success(function (data) {
				
				ComentariosService.retrieveAll()
				.success(function (data) {
					$scope.comentarios = data.comentario;
				});
			});
	};
	
	self.delete = function (id) {
		ComentariosService.delete(id)
		.success(function (data) {
			ComentariosService.retrieveAll()
			.success(function (data) {
				$scope.comentarios = data.comentario;
			});
		});
	};

	$scope.puntuacion=0;
	$scope.pmedia=2;

}]);
	
	//FUNCION SERVICIOS WEB Comentarios

	
	app.service('ComentariosService', ['$http', function($http) {
		
		this.retrieveAll = function() {
			var url = app.baseURI + "A/1";
			return $http.get(url);
		};
		

		this.create = function(idActividad,idUsuario,contenido,puntuacion) {
			dato = {'comentario': {'idActividad': idActividad, 'idUsuario': idUsuario, 'contenido': contenido, 
				'puntuacion':puntuacion}};
			
			var url = app.baseURI + idActividad;
			return $http.put(url, dato);
		}
		
		this.delete = function(id) {
				var url = app.baseURI + id;
				return $http.delete(url);
			}
		
		
	}]);
	