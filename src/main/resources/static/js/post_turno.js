$(document).ready(function(){
    (function(){
      $.ajax({
        type: "GET",
        url: "http://localhost:8080/odontologos/listar",
        success: function(response){
          $.each(response, (i, odontologo) => {
            let listaOdontologos = `<option value="${odontologo.id}">${odontologo.nombre} ${odontologo.apellido}</option>`;
            $('#turno_odontologo').append(listaOdontologos);
          });
        },
        error: function(e){
          alert("ERROR: ", e);
          console.log("ERROR: ", e);
        }
      })
      $.ajax({
        type: "GET",
        url: "http://localhost:8080/pacientes/listar",
        success: function(response){
          $.each(response, (i, paciente) => {
            let listaPacientes = `<option value="${paciente.id}">${paciente.nombre} ${paciente.apellido}</option>`;
            $('#turno_paciente').append(listaPacientes);
          });
        },
        error: function(e){
          alert("ERROR: ", e);
          console.log("ERROR: ", e);
        }
      })
    })(); 
    $("#add_new_turno").submit(function(evt) {
        evt.preventDefault();
        //AGARRAR EL OPTION VALUE
        //HACER BUSCAR ODONTOLOGO Y PACIENTE
        //SUBIR TURNO COMPLETITO

        console.log($("#turno_odontologo").val());
        console.log($("#turno_paciente").val());
        
        let error = false;
        let formData = {
            paciente: "",
            odontologo: "",
            fecha: $("#fecha").val(),
        }

        //Conseguir el odontologo completo
        $.ajax({
            type: 'GET',
            url: `http://localhost:8080/odontologos/${$("#turno_odontologo").val()}`,
            success: function (response) {
                formData.odontologo = response;
                console.log("Odontologo obtenido:");
                console.log(response);
                $.ajax({
                    type: 'GET',
                    url: `http://localhost:8080/pacientes/${$("#turno_paciente").val()}`,
                    success: function (response) {
                        formData.paciente = response;
                        console.log("Paciente obtenido:");
                        console.log(response);
                        $.ajax({
                            url: 'http://localhost:8080/turnos/',
                            type: 'POST',
                            contentType : "application/json",
                            data: JSON.stringify(formData),
                            dataType : 'json',
                            async: false,
                            cache: false,
                            success: function (response) {
                               console.log(response)
                                let successAlert  = '<div class="alert alert-success alert-dismissible">' +
                                                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                                    '<strong></strong> Turno agregado </div>'
                                $("#response").append(successAlert);
                                $("#response").css({"display": "block"});
                            },
                            error: function (response) {
                                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                                 '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                                 '<strong> Error intentando cargar el turno </strong> </div>'
                                $("#response").append(errorAlert);
                                $("#response").css({"display": "block"});
                            }
                        });
                    },
                    error: function (response) {
                        let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                         '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                         '<strong> Error intentando conseguir los datos del paciente </strong> </div>'
                        $("#response").append(errorAlert);
                        $("#response").css({"display": "block"});
                        error = true;
        
                        resetUploadForm();
                    }
                });
            },
            error: function (response) {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                 '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                 '<strong> Error intentando conseguir los datos del odontologo </strong> </div>'
                $("#response").append(errorAlert);
                $("#response").css({"display": "block"});
                error = true;

                resetUploadForm();
            }
        });
    });
    function resetUploadForm(){
        $("#turno_odontologo").selectedIndex = 0;
        $("#turno_paciente").selectedIndex = 0;
        $("#fecha").val("");
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            $(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/turnos.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});