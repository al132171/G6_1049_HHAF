(function () {

    var actividadCliente = angular.module('actividadCliente', ['angular-input-stars']); //Define el modulo de la aplicación (ng-app="actividadCliente") para todo el fichero


    // Filtro para la paginación
    actividadCliente.filter('offset', function () {
        return function (input, start) {
            start = parseInt(start, 10);
            return input.slice(start);
        };
    });


    actividadCliente.controller('UsuarioCtrl', ['$scope', 'UsuarioService', '$rootScope', 'ComentariosService' , function ($scope, UsuarioService,$rootScope,ComentariosService) { //Inyecta los atributos

        actividadCliente.baseURI = 'http://localhost:8080/Natureadventure/cliente/actividades/';
        actividadCliente.baseURIC = 'http://localhost:8080/Natureadventure/comentarios/';
        $scope.username =  window.location.href.slice(window.location.href.indexOf('?') + 1).split('=')[1];

        var self = this;


        $scope.feed = {};
        $scope.feed.categorias = [
            {
                'name': 'Bicicleta',
                'value': 'Bicicleta'
            },
            {
                'name': 'Running',
                'value': 'Running'
            },
            {
                'name': 'Extremo',
                'value': 'Extremo'
            },
            {
                'name': 'Acuaticos',
                'value': 'Acuaticos'
            },
            {
                'name': 'Motor',
                'value': 'Motor'
            },
            {
                'name': 'Montaña',
                'value': 'Montaña'
            },
            {
                'name': 'Otros',
                'value': 'Otros'
            }
                                  ];


        //Datos para la paginación
        $scope.paginacion = {};
        $scope.paginacion.itemsPerPage = 6;
        $scope.paginacion.currentPage = 0;
        $scope.paginacion.range = {};
        //Fin datos para la paginación


        $scope.show = {};
        $scope.show.categorias = true;
        $scope.show.resultadosBusqueda = false;
        $scope.show.resultadosCategoria = false;
        $scope.show.informacionActividad = false;
        $scope.show.cantidadResultados = 0;
        

        
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
            return Math.ceil($scope.feed.datosActuales.length / $scope.paginacion.itemsPerPage) - 1;
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
        

        self.buscaActividades = function (palabraClave) {

            if (palabraClave.length > 0) {
                UsuarioService.retrieveActividadesPalabraClave(palabraClave)
                    .success(function (data) {
                        $scope.feed.resultadosBusqueda = data.actividad;
                        $scope.feed.datosActuales = data.actividad;
                        $scope.show.categorias = false;
                        $scope.show.resultadosCategoria = false;
                        $scope.show.resultadosBusqueda = true;
                        $scope.show.informacionActividad = false;
                        $scope.show.cantidadResultados = data.actividad.length;
                        document.getElementById("inputCategorias").blur();
                        document.getElementById("inputResultadosBusqueda").focus();
                        var tamanyoTexto = document.getElementById("inputResultadosBusqueda").value.length;
                        document.getElementById("inputResultadosBusqueda").setSelectionRange(tamanyoTexto, tamanyoTexto);
                    });

            } else {
                $scope.show.categorias = true;
                $scope.show.resultadosBusqueda = false;
                $scope.show.resultadosCategoria = false;
                $scope.show.informacionActividad = false;
                document.getElementById("inputResultadosBusqueda").blur();
                document.getElementById("inputCategorias").focus();
            }

        };

        self.muestraCategorias = function () {
            $scope.show.categorias = true;
            $scope.show.resultadosBusqueda = false;
            $scope.show.resultadosCategoria = false;
            $scope.show.informacionActividad = false;
            document.getElementById("inputCategorias").value = "";
            document.getElementById("inputResultadosBusqueda").value = "";
        };
        
        
        $scope.muestraActividadesCategoria = function (categoria) {
            
            UsuarioService.retrieveActividadesCategoria(categoria)
                .success(function (data) {
                
                    $scope.feed.resultadosCategoria = data.actividad;
                    $scope.feed.datosActuales = data.actividad;
                
                    $scope.show.categoria = categoria;
                    $scope.show.categorias = false;
                    $scope.show.resultadosBusqueda = false;
                    $scope.show.informacionActividad = false;
                    $scope.show.resultadosCategoria = true;
                    $scope.show.cantidadResultados = data.actividad.length;
            });
        };
        
        $scope.informacionActividad = function (nombre) {
            
            UsuarioService.retrieveActividad(nombre)
                .success(function (data) {
                	$scope.idActividad=data.actividad.id;
                    $scope.feed.informacionActividad = data.actividad;
                    $scope.feed.datosActuales = data.actividad;
                
                    $scope.show.actividad = nombre;
                    $scope.show.informacionActividad = true;
                    $scope.show.categorias = false;
                    $scope.show.resultadosBusqueda = false;
                    $scope.show.resultadosCategoria = false;
                
                
                
                var latlng = data.actividad.lugar.split(',');
                var myCenter = new google.maps.LatLng(parseFloat(latlng[0]), parseFloat(latlng[1]));
                var mapProp = {
    center: myCenter,
    zoom:16,
                    streetViewControl : true,
                    
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };
  var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
                var marker=new google.maps.Marker({
  position:myCenter,
  });

marker.setMap(map);

  var script = document.createElement("script");
  script.type = "text/javascript";
  script.src = "http://maps.googleapis.com/maps/api/js?key=&sensor=false&callback=initialize";
  document.body.appendChild(script);
                
//COMENTARIOS


$scope.comentarios = ComentariosService.retrieveAll($scope.idActividad).success(function(data){
	$scope.comentarios=data.comentario;
	
		
		
		CalcularMedia = function(){
		       var total = 0;
		       for (var i = 0; i < $scope.comentarios.length; i++) {
		              total = total + $scope.comentarios[i].puntuacion;
		            }
		            $scope.pMedia = total/i;
		};
		CalcularMedia();
		
});

self.createComent = function (nombreU,contenido,puntuacion) {

    if (nombreU === undefined || nombreU==="") {
    	nombreU="Anonimo";
    }

	ComentariosService.create($scope.idActividad,nombreU,contenido,puntuacion)
			.success(function (data) {
				ComentariosService.retrieveAll($scope.idActividad)
				.success(function (data) {
					$scope.comentarios = data.comentario;
					$scope.comentarForm.$setPristine(); 
					document.getElementById("comentarCont").value="";

					
				});
			});
	};

self.deleteComent = function (id) {
	ComentariosService.delete(id)
	.success(function (data) {
		ComentariosService.retrieveAll()
		.success(function (data) {
			$scope.comentarios = data.comentario;
		});
	});
};
/// fin comentarios              
                
                
                
                
            });
        };

        


 }]);


    // FUNCIO SERVICIOS WEB
    actividadCliente.service('UsuarioService', ['$http', function ($http) {

        this.retrieveActividadesPalabraClave = function (palabraClave) {
            var url = actividadCliente.baseURI + "palabraClave/" + palabraClave;
            return $http.get(url);
        };
        
        this.retrieveActividadesCategoria = function (categoria) {
            var url = actividadCliente.baseURI + "categoria/" + categoria;
            return $http.get(url);
        };
        
        this.retrieveActividad = function (nombre) {
            var url = actividadCliente.baseURI + nombre;
            return $http.get(url);
        };


 }]);
    
    

//FUNCION SERVICIOS WEB Comentarios


actividadCliente.service('ComentariosService', ['$http', function($http) {
	
	this.retrieveAll = function(idActividad) {
		var url = actividadCliente.baseURIC + "A/"+idActividad;
		return $http.get(url);
	};
	

	this.create = function(idActividad,nombreU,contenido,puntuacion) {
		dato = {'comentario': {'idActividad': idActividad, 'nombreU': nombreU, 'contenido': contenido, 
			'puntuacion':puntuacion}};
		
		var url = actividadCliente.baseURIC + idActividad;
		return $http.put(url, dato);
	}
	
	this.delete = function(id) {
			var url = actividadCliente.baseURIC + id;
			return $http.delete(url);
		}
	
	
}]);

})();