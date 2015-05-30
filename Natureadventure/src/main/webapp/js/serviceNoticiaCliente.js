var app = angular.module("noticiaCliente", []);

app.controller('UsuarioCtrl', ['$scope', 'NoticiaUService', function ($scope, NoticiaUService) {

	app.baseURI = 'http://localhost:8080/Natureadventure/gerente/noticias/';

	var self = this;
	
	// Cargar las noticias
	NoticiaUService.retrieveUltimasNoticias()
	.success(function(data) {
		console.log(data.noticia);
		$scope.noticias = data.noticia;
	});

	self.retrieveNoticia = function(titulo) {
		alert("entro");
		NoticiaUService.retrieveNoticia(titulo)
		.success(function(data) {
			console.log(data);
			$scope.currentNoticia = data;
		});
	};

		
	
}]);

//SERVICIOS WEB NOTICIAS

app.service('NoticiaUService', ['$http', function ($http) {

	this.retrieveUltimasNoticias = function() {
		var url = app.baseURI + "ultimas";
		return $http.get(url);
	};

}]);