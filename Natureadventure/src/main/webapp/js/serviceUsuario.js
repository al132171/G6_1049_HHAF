(function () {
    
    var usuarioG = angular.module('usuarioG', []); //Define el modulo de la aplicación (ng-app="usuarioG") para todo el fichero
    
    usuarioG.controller('UsuarioCtrl', ['$scope', 'UsuarioService', function($scope, UsuarioService) { //Inyecta los atributos
        
        usuarioG.baseURI = 'http://localhost:8080/Natureadventure/cliente/actividades/';
    
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
        $scope.show.resultadosBusqueda = false;
        
        self.muestraCategorias = function() {
            $scope.show.categorias = true;
            $scope.show.resultadosBusqueda = false;
        };
        
        self.buscaActividades = function(palabraClave) {
                        
            if(palabraClave.length > 0) {
                 UsuarioService.retrieveActividadesPalabraClave(palabraClave)
                .success(function(data) {
                    $scope.feed.resultadosBusqueda = data.actividad;
                    $scope.show.categorias = false;
                    $scope.show.resultadosBusqueda = true;
                    document.getElementById("inputCategorias").blur();
                    document.getElementById("inputResultadosBusqueda").focus();
                    var tamanyoTexto = document.getElementById("inputResultadosBusqueda").value.length;
                    document.getElementById("inputResultadosBusqueda").setSelectionRange(tamanyoTexto, tamanyoTexto);
                });
                
            } else {
                $scope.show.categorias = true;
                $scope.show.resultadosBusqueda = false;
                document.getElementById("inputResultadosBusqueda").blur();
                document.getElementById("inputCategorias").focus();
            }
           
        };
        
        
        
    }]);
    
    // FUNCIO SERVICIOS WEB
    usuarioG.service('UsuarioService', ['$http', function($http) {
        
        this.retrieveActividadesPalabraClave = function(palabraClave) {
			var url = usuarioG.baseURI + "palabraClave/" + palabraClave;
			return $http.get(url);
		};
        
        
        
    }]);
    
    
    
})();