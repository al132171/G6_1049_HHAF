(function () {

    var actividadCliente = angular.module('actividadCliente', ['angular-input-stars']); //Define el modulo de la aplicación (ng-app="actividadCliente") para todo el fichero


    // Filtro para la paginación
    actividadCliente.filter('offset', function () {
        return function (input, start) {
            start = parseInt(start, 10);
            return input.slice(start);
        };
    });



    actividadCliente.controller('UsuarioCtrl', ['$scope', 'UsuarioService', '$rootScope', 'ComentariosService', function ($scope, UsuarioService,$rootScope,ComentariosService) { //Inyecta los atributos

        actividadCliente.baseURI = 'http://localhost:8080/Natureadventure/cliente/actividades/';
        actividadCliente.baseURIC = 'http://localhost:8080/Natureadventure/comentarios/';
        $scope.nameActividad =  window.location.href.slice(window.location.href.indexOf('?') + 1).split('=')[1];


        var self = this;

        //		Comprueba si los datos introducidos son números o "-"
        $scope.telefono = { 
            word: /^([0-9-])*$/
        };
        $scope.fechaActividad = { 
            word: /^([0-9-])*$/
        };

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

        $scope.feed.novedades = UsuarioService.retrieveNovedades().success(function(data){
            $scope.feed.novedades = data.actividad;
        });
        
        $scope.feed.ofertas = UsuarioService.retrieveOfertas().success(function(data){
            $scope.feed.ofertas = data.actividad;
        });


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
        $scope.show.precio = 0;
        $scope.show.formatoFechaValido = true;



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
                    zoom:15,
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

                //				COMENTARIOS


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
					ComentariosService.remove(id)
					.success(function (data) {
						ComentariosService.retrieveAll()
						.success(function (data) {
							$scope.comentarios = data.comentario;
						});
					});
				};
                //				/ fin comentarios              


                $scope.show.actividad = nombre;
                $scope.show.informacionActividad = true;
                $scope.show.categorias = false;
                $scope.show.resultadosBusqueda = false;
                $scope.show.resultadosCategoria = false;

            });
        };

        if($scope.nameActividad !== undefined) {
            var nombre = $scope.nameActividad.split("%20").join(" ");
            $scope.show.actividad = nombre;
            $scope.informacionActividad(nombre);

        }
        
        //  Calcula el precio de la reserva en función de el numero de participantes y le añade el 21% de IVA
        $scope.calculaPrecio = function (numeroAsistentes){
            
            var precio = $scope.feed.datosActuales.precio * numeroAsistentes * 1.21;
            $scope.show.precio = precio.toFixed(2);
        };

        // Valida el formato de la fecha de reserva
        $scope.validaFechaDDMMAAAA = function (fecha){
            var dtCh= "-";
            var minYear=1900;
            var maxYear=2100;
            function isInteger(s){
                var i;
                for (i = 0; i < s.length; i++){
                    var c = s.charAt(i);
                    if (((c < "0") || (c > "9"))) return false;
                }
                return true;
            }
            function stripCharsInBag(s, bag){
                var i;
                var returnString = "";
                for (i = 0; i < s.length; i++){
                    var c = s.charAt(i);
                    if (bag.indexOf(c) == -1) returnString += c;
                }
                return returnString;
            }
            function daysInFebruary (year){
                return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
            }
            function DaysArray(n) {
                for (var i = 1; i <= n; i++) {
                    this[i] = 31
                    if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
                    if (i==2) {this[i] = 29}
                }
                return this
            }
            function isDate(dtStr){
                var daysInMonth = DaysArray(12)
                var pos1=dtStr.indexOf(dtCh)
                var pos2=dtStr.indexOf(dtCh,pos1+1)
                var strDay=dtStr.substring(0,pos1)
                var strMonth=dtStr.substring(pos1+1,pos2)
                var strYear=dtStr.substring(pos2+1)
                strYr=strYear

                for (var i = 1; i <= 3; i++) {
                    if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
                        }
                month=parseInt(strMonth)
                day=parseInt(strDay)
                year=parseInt(strYr)
                if (pos1==-1 || pos2==-1){
                    return false
                }
                if (strMonth.length!=2 || month<1 || month>12){
                    return false
                }
                if (strDay.length!=2 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
                    return false
                }
                if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
                    return false
                }
                if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
                    return false
                }
                return true
            }
            if(isDate(fecha)){
                $scope.show.formatoFechaValido = true;
            }else{
                $scope.show.formatoFechaValido = false;
                $scope.formularioReserva.fechaActividad.$invalid = true;
            }
        };

        
        //		Comprueba que la fecha de realización de la actividad que se ha escogido está dentro del periodo de realización de la actividad
		$scope.validarRangoFecha =  function(fechaReserva){
            
            var diaReserva = fechaReserva.substring(0, 2);
            var diaInicio = $scope.feed.datosActuales.fechaInicio.substring(0, 2);
            var diaFin = $scope.feed.datosActuales.fechaFin.substring(0, 2);

            var mesReserva = fechaReserva.substring(3, 5);
            var mesInicio = $scope.feed.datosActuales.fechaInicio.substring(3, 5);
            var mesFin = $scope.feed.datosActuales.fechaFin.substring(3, 5);

            var añoReserva = fechaReserva.substring(6,10);
            var añoInicio = $scope.feed.datosActuales.fechaInicio.substring(6);
            var añoFin = $scope.feed.datosActuales.fechaFin.substring(6);

            var FechaFinalReserva = mesFin + "/" + diaFin + "/" + añoFin;
            var FechaInicialReserva = mesInicio + "/" + diaInicio + "/" + añoInicio;
            var FechaReservaActividad = mesReserva + "/" + diaReserva + "/" + añoReserva;


            if(Date.parse(FechaReservaActividad) > Date.parse(FechaFinalReserva)){
                $scope.show.formatoFechaValido = false;
                $scope.formularioReserva.fechaActividad.$invalid = true;
            }
            else {
                if(Date.parse(FechaReservaActividad) < Date.parse(FechaInicialReserva)){
                    $scope.show.formatoFechaValido = false;
                    $scope.formularioReserva.fechaActividad.$invalid = true;
                }
                else {
                    $scope.show.formatoFechaValido = true;

                }

            }
		};

        $scope.escondeFormularioReserva = function() {
            if($scope.formularioReserva.$valid){
                $('#modalReservar').modal('hide');
                $('#modalPagar').modal('show');
            }
        }
        
        $scope.muestraFormularioReserva = function() {
            if($scope.formularioReserva.$valid){
                $('#modalPagar').modal('hide');
                $('#modalReservar').modal('show');
            }
        }
        

        //		Limpiar el formulario para que si sales de la ventana modal se limpien los mensajes de error y formato
        $scope.resetFormReserva = function(reserva){

            var month = new Array(12);
            month[0] = "01";
            month[1] = "02";
            month[2] = "03";
            month[3] = "04";
            month[4] = "05";
            month[5] = "06";
            month[6] = "07";
            month[7] = "08";
            month[8] = "09";
            month[9] = "10";
            month[10] = "11";
            month[11] = "12";

            var day = new Array(31);
            day[1] = "01";
            day[2] = "02";
            day[3] = "03";
            day[4] = "04";
            day[5] = "05";
            day[6] = "06";
            day[7] = "07";
            day[8] = "08";
            day[9] = "09";
            day[10] = "10";
            day[11] = "11";
            day[12] = "12";
            day[13] = "13";
            day[14] = "14";
            day[15] = "15";
            day[16] = "16";
            day[17] = "17";
            day[18] = "18";
            day[19] = "19";
            day[20] = "20";
            day[21] = "21";
            day[22] = "22";
            day[23] = "23";
            day[24] = "24";
            day[25] = "25";
            day[26] = "26";
            day[27] = "27";
            day[28] = "28";
            day[29] = "29";
            day[30] = "30";
            day[31] = "31";

            var d = new Date();
            var dia = day[d.getUTCDate()];
            var mes = month[d.getUTCMonth()];
            var anyo = d.getFullYear();
            var hora = d.getHours();
            var minutos = d.getMinutes();
            var segundos = d.getSeconds();
            var milisegundos = d.getMilliseconds();


            var fechaActual = dia + "-" + mes + "-" + anyo + ", " + hora + ":" + minutos + ":" + segundos + "." + milisegundos;

            var defaultForm = {
                apellidos: "", cantidadPersonas:"", contrato: "", correo: "", dni: "", estado: "P", fechaActividad: "", fechaReserva: fechaActual, nombre: "", precio: $scope.feed.datosActuales.precio , telefono: "", actividad_id: $scope.feed.datosActuales.id, usuario_id: ""};
            $scope.formularioReserva.$setPristine();
            $scope.reserva = defaultForm;
        };

        self.create = function (nombre, apellidos, correo, telefono, dni, fechaActividad, cantidadPersonas) {

            var precioTotal = $scope.reserva.precio * cantidadPersonas * 1.21;

            if($scope.formularioReserva.$valid && $scope.formularioPago.$valid){

                UsuarioService.create(apellidos, cantidadPersonas, $scope.reserva.contrato, correo, dni, $scope.reserva.estado, fechaActividad, $scope.reserva.fechaReserva, nombre, precioTotal, telefono, $scope.show.actividad, $scope.reserva.usuario_id)
                    .success(function (data) {
                });
                $('#modalPagar').modal('hide');
                $('#modalConfirmación').modal('show');
            };
        };


        
        
    }]);

    // FUNCIONES SERVICIOS WEB

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

        this.create = function(apellidos, cantidadPersonas, contrato, correo, dni, estado, fechaActividad, fechaReserva, nombre, precio, telefono, actividad_id, usuario_id) {

            dato = {'reserva': {'fechaActividad': fechaActividad, 'fechaReserva': fechaReserva, 'cantidadPersonas': cantidadPersonas, 'precio': precio, 'nombre': nombre, 'apellidos': apellidos, 'dni': dni, 'correo': correo, 'telefono': telefono, 'estado': estado, 'contrato': contrato, 'actividad': actividad_id, 'usuario': usuario_id}};

            var url = actividadCliente.baseURI + "reserva/" + actividad_id;

            return $http.put(url, dato);
        }


        this.retrieveNovedades = function() {
            var url = actividadCliente.baseURI + "novedades";
            return $http.get(url);
        };

        this.retrieveOfertas = function() {
            var url = actividadCliente.baseURI + "ofertas";
            return $http.get(url);
        };



    }]);



    //	FUNCION SERVICIOS WEB Comentarios


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
		};

		this.remove = function(id) {
			var url = actividadCliente.baseURIC + id;
			return $http["delete"](url);
		};


	}]);

})();