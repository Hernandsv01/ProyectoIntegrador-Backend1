$(document).ready(function(){
    (function(){
        $.ajax({
            type : "GET",
            url : "http://localhost:8080/pacientes/listar",
            success: function(response){
              $.each(response, (i, paciente) => {  


                let get_More_Info_Btn = '<button' +
                                            ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                                            ' type="button" class="btn btn-info btn_id">' + 
                                            paciente.id +
                                            '</button>';
                
                let tr_id = 'tr_' + paciente.id;
                let pacienteRow = '<tr id=\"' + tr_id + "\"" + '>' +
                          '<td>' + get_More_Info_Btn + '</td>' +
                          '<td class=\"td_first_name\">' + paciente.nombre + '</td>' +
                          '<td class=\"td_last_name\">' + paciente.apellido + '</td>' +
                          '<td class=\"td_email\">' + paciente.email + '</td>' +
                          '<td class=\"td_dni\">' + paciente.dni + '</td>' +
                          '<td class=\"td_fecha_ingreso\">' + paciente.fechaIngreso + '</td>' +
                          '<td class=\"td_domicilio_calle\">' + paciente.domicilio.calle + '</td>' +
                          '<td class=\"td_domicilio_numero\">' + paciente.domicilio.numero + '</td>' +
                          '<td class=\"td_domicilio_localidad\">' + paciente.domicilio.localidad + '</td>' +
                          '<td class=\"td_domicilio_provincia\">' + paciente.domicilio.provincia + '</td>' +
                          '</tr>';                
                $('#pacienteTable tbody').append(pacienteRow);
              });
            },
            error: function(e) {
              alert("ERROR: No tiene acceso a esta pestaña");
              console.log("ERROR: ", e);
              window.location = "/";
            }
        });
    })();        
    
    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/pacientes.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});