(function () {
    
    var usuarioG = angular.module('usuarioG', []); //Define el modulo de la aplicación (ng-app="usuarioG") para todo el fichero
    usuarioG.baseURI = 'http://localhost:8080/Natureadventure/usuario/actividades/';
    
    usuarioG.controller('UsuarioCtrl', ['$scope', 'UsuarioService', function($scope, UsuarioService) { //Inyecta los atributos
        
        var self = this;
        
        $scope.feed = {};
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
                
        $scope.show = {};
        $scope.show.categorias = true;
        
        
        
    }]);
    
    // FUNCIO SERVICIOS WEB
    usuarioG.service('UsuarioService', ['$http', function($http) {
        
        
        
        
        
    }]);
    
    
    
})();