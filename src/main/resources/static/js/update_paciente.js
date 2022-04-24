$(document).ready(function(){
    $("#update_paciente_form").submit(function(evt) {
        evt.preventDefault();
        try {
            let pacienteId = $("#paciente_id").val();
            
        let formData = {
            id: $("#paciente_id").val(),
            nombre : $("#paciente_nombre").val(),
            apellido :  $("#paciente_apellido").val(),
            email: $("#paciente_email").val(),
            dni: $("#paciente_dni").val(),
            fechaIngreso: $("#paciente_fecha_alta").val(),
            domicilio: {
                calle: $("#paciente__domicilio_calle").val(),
                numero: $("#paciente__domicilio_numero").val(),
                localidad: $("#paciente__domicilio_localidad").val(),
                provincia: $("#paciente__domicilio_provincia").val(),
            }
        }
            
            $.ajax({
                url: 'http://localhost:8080/odontologos/',
                type: 'PUT',
                contentType : "application/json",
                data: JSON.stringify(formData),
                dataType : 'json',
                async: false,
                cache: false,
                success: function (response) {
                    let paciente = response;
        
                    let successAlert = '<div class="alert alert-success alert-dismissible">' + 
                                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                        '<strong> paciente actualizado </strong></div>'

                 
                    $("#tr_" + pacienteId + " td.td_paciente_first_name").text(paciente.nombre);
                    $("#tr_" + pacienteId + " td.td_paciente_last_name").text(paciente.apellido);
                    $("#tr_" + pacienteId + " td.td_paciente_email").text(paciente.email);
                    $("#tr_" + pacienteId + " td.td_paciente_dni").text(paciente.dni);
                    $("#tr_" + pacienteId + " td.td_paciente_fecha_alta").text(paciente.fechaIngreso);
                    $("#tr_" + pacienteId + " td.td_paciente__domicilio_calle").text(paciente.domicilio.calle);
                    $("#tr_" + pacienteId + " td.td_paciente__domicilio_numero").text(paciente.domicilio.numero);
                    $("#tr_" + pacienteId + " td.td_paciente__domicilio_localidad").text(paciente.domicilio.localidad);
                    $("#tr_" + pacienteId + " td.td_paciente__domicilio_provincia").text(paciente.domicilio.provincia);

                    $("#response").empty();
                    $("#response").append(successAlert);
                    $("#response").css({"display": "block"});
                },

                error: function (response) {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' + 
                                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                    '<strong> Error </strong></div>';

                    $("#response").empty();                                    
                    $("#response").append(errorAlert);
                    $("#response").css({"display": "block"});
                }
            });
        } catch(error){
            console.log(error);
            alert(error);
        }
    });

    $(document).on("click", "table button.btn_id", function(){
        let id_of_button = (event.srcElement.id);
        let pacienteId = id_of_button.split("_")[2];
  
        $.ajax({
            url: 'http://localhost:8080/pacientes/' + pacienteId,
            type: 'GET',
            success: function(response) {
                let paciente = response;                
                $("#paciente_id").val(paciente.id);
                $("#paciente_nombre").val(paciente.nombre);
                $("#paciente_apellido").val(paciente.apellido);
                $("#paciente_email").val(paciente.email);
                $("#paciente_dni").val(paciente.dni);
                $("#paciente_fecha_alta").val(paciente.fechaIngreso);
                $("#paciente_domicilio_calle").val(paciente.domicilio.calle);
                $("#paciente_domicilio_numero").val(paciente.domicilio.numero);
                $("#paciente_domicilio_localidad").val(paciente.domicilio.localidad);
                $("#paciente_domicilio_provincia").val(paciente.domicilio.provincia);
                $("#div_paciente_updating").css({"display": "block"});
            },
            error: function(error){
                console.log(error);
                alert("Error -> " + error);
            }
        });        
    });
});