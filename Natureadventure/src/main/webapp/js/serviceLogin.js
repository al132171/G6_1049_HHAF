/**
 * 
 */

(function() {

	var login = angular.module('login', []); 
	login.baseURI = 'http://localhost:8080/Natureadventure/login/';

	

	login.controller('LoginCtrl', ['$scope', "$location", 'LoginService', function ($scope, $location, LoginService) { //Inyecta los atributos

		var self = this;

		self.login = function(username, password){
			LoginService.retrieveUser(username, password).success(function (data) {
				alert("Entro");
				if(data.usuario.rol == "G")
//					$location.absUrl() == 'http://localhost:8080/Natureadventure/html/gerente/gestionarActividades.html';
					window.location.href="http://localhost:8080/Natureadventure/html/gerente/gestionarActividades.html";
				else(data.usuario.rol =="M")
					//window.location.href="http://localhost:8080/Natureadventure/html/monitor/index.html";
			}).error(function(data){
				$scope.loginForm.password.$setValidity("password", false);
			});
		};

	}]);

	//FUNCION SERVICIOS WEB
	login.service('LoginService', ['$http', function($http) {
		this.retrieveUser = function(username, password) {
			var url = login.baseURI + username+"/"+password;
//			var req = {
//					 method: 'GET',
//					 url: url,
//					 headers: {
//					   'Content-Type': undefined,
//					   usuario: username
//					 }
					 
				return $http.get(url);
					};

	}]);

})();