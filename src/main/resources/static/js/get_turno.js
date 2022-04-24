$(document).ready(function(){
  (function(){
    //PEDIR LISTA DE TURNOS PARA turnos.html
  })
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
  }) 
    
    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/pacientes.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});