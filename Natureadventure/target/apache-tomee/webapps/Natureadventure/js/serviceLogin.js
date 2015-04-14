/**
 * 
 */

(function() {

	var login = angular.module('login', []); 
	login.baseURI = 'http://localhost:8080/Natureadventure/login/';


	login.controller('LoginCtrl', ['$scope', 'LoginService', function ($scope, LoginService) { //Inyecta los atributos

		var self = this;

		self.login = function(username, password){
			LoginService.retrieveUser(username, password).success(function (data) {
				if(data.usuario.rol == "G")
					window.location.href="http://localhost:8080/Natureadventure/html/gerente/index.html";
			}).error(function(data){
				$scope.loginForm.password.$setValidity("password", false);
			});
		};

	}]);

	//FUNCION SERVICIOS WEB
	login.service('LoginService', ['$http', function($http) {
		this.retrieveUser = function(username, password) {
			var url = login.baseURI + username+"/"+password;
			return $http.get(url);
		};

	}]);

})();