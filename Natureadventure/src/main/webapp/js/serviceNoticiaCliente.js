var app = angular.module("noticiaCliente", []);

app.controller('UsuarioCtrl', ['$scope', 'NoticiaUService', function ($scope, NoticiaUService) {

	app.baseURI = 'http://localhost:8080/Natureadventure/gerente/noticias/';
	app.baseSuscripcionURI = 'http://localhost:8080/Natureadventure/usuarioSuscritoNoticias/'

	var self = this;

	var meses = {
		"01": "Enero",
		"02": "Febrero",
		"03": "Marzo",
		"04": "Abril",
		"05": "Mayo",
		"06": "Junio",
		"07": "Julio",
		"08": "Agosto",
		"09": "Septiembre",
		"10": "Octubre",
		"12": "Noviembre",
		"12": "Diciembre",
	};
	
    $scope.noticias = NoticiaUService.retrieveUltimasNoticias().success(function(data){
        $scope.noticias = data.noticia;
    });

    self.create = function(nombre, email) {

        var expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        if ( !expr.test(email) ) {
            alert("correo");
            $('#modalCorreoInvalido').modal('show');
        } else {

            NoticiaUService.buscaSuscripcion(email)
                .success(function (data) {
                $('#modalSuscripcionFallida').modal('show');
            }).error(function(){
                NoticiaUService.createSuscripcion(nombre, email)
                    .success(function(data) {    			
                    var defaultForm = {
                        nombre:"", email: ""
                    };
                    $scope.formSuscripcion.$setPristine();
                    $scope.suscripcion = defaultForm;
                    $('#modalSuscripcionCorrecta').modal('show');
                });
            });

        }

    };
    
	// Cargar las noticias
	NoticiaUService.retrieveUltimasNoticias()
	.success(function(data) {
		$scope.noticia1I = data.noticia[0];
		$scope.noticia2I = data.noticia[1];
		$scope.noticia3I = data.noticia[2];
		
		$scope.dia1I = $scope.noticia1I.fecha.split("-")[0];
		$scope.dia2I = $scope.noticia2I.fecha.split("-")[0];
		$scope.dia3I = $scope.noticia3I.fecha.split("-")[0];

		$scope.mes1I = meses[$scope.noticia1I.fecha.split("-")[1]];
		$scope.mes2I = meses[$scope.noticia2I.fecha.split("-")[1]];
		$scope.mes3I = meses[$scope.noticia2I.fecha.split("-")[1]];

		$scope.titulo1I = $scope.noticia1I.titulo;
		$scope.titulo2I = $scope.noticia2I.titulo;
		$scope.titulo3I = $scope.noticia3I.titulo;
		
		$scope.subtitulo1I = $scope.noticia1I.subtitulo;
		$scope.subtitulo2I = $scope.noticia2I.subtitulo;
		$scope.subtitulo3I = $scope.noticia3I.subtitulo;
		
		$scope.descripcion1I = $scope.noticia1I.descripcion;
		$scope.descripcion2I = $scope.noticia2I.descripcion;
		$scope.descripcion3I = $scope.noticia3I.descripcion;
		

		$scope.noticia1D = data.noticia[3];
		$scope.noticia2D = data.noticia[4];
		$scope.noticia3D = data.noticia[5];
		
		$scope.dia1D = $scope.noticia1D.fecha.split("-")[0];
		$scope.dia2D = $scope.noticia2D.fecha.split("-")[0];
		$scope.dia3D = $scope.noticia3D.fecha.split("-")[0];

		$scope.mes1D = meses[$scope.noticia1D.fecha.split("-")[1]];
		$scope.mes2D = meses[$scope.noticia2D.fecha.split("-")[1]];
		$scope.mes3D = meses[$scope.noticia2D.fecha.split("-")[1]];
		
		$scope.titulo1D = $scope.noticia1D.titulo;
		$scope.titulo2D = $scope.noticia2D.titulo;
		$scope.titulo3D = $scope.noticia3D.titulo;
		
		$scope.subtitulo1D = $scope.noticia1D.subtitulo;
		$scope.subtitulo2D = $scope.noticia2D.subtitulo;
		$scope.subtitulo3D = $scope.noticia3D.subtitulo;
		
		$scope.descripcion1D = $scope.noticia1D.descripcion;
		$scope.descripcion2D = $scope.noticia2D.descripcion;
		$scope.descripcion3D = $scope.noticia3D.descripcion;
	})
	.error(function(data) {
		alert("ERROR - No se han cargado las noticias");
	});

}]);

// SERVICIOS WEB NOTICIAS

app.service('NoticiaUService', ['$http', function ($http) {

	this.retrieveUltimasNoticias = function() {
		var url = app.baseURI + "ultimas"
		return $http.get(url);
	}
	
	this.createSuscripcion = function(nombre, email){
		dato = {'usuarioSuscritoNoticias':{'nombre': nombre, 'email': email}};
		var url = app.baseSuscripcionURI + email;
		return $http.put(url, dato);
	}
	
	this.buscaSuscripcion = function(email){
		var url = app.baseSuscripcionURI + email;
		return $http.get(url);
	}

}]);