
 $('#create').on('shown.bs.modal', function() {
 	document.getElementById("createForm").reset();
 });

    



$( document ).on(
    'DOMMouseScroll mousewheel scroll',
    '#create', 
    function(){  
	var t ;
        window.clearTimeout( t );
        t = window.setTimeout( function(){            
            $('.form_date, .form_time').datetimepicker('place')
        }, 100 );
        
    }
);

