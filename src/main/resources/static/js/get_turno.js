$(document).ready(function(){
  (function(){
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/turnos",
      success: function(response){
        $.each(response, (i, turno) => {
          let get_More_Info_Btn = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                                      ' type="button" class="btn btn-info btn_id">' + 
                                      turno.id +
                                      '</button>';
          
          let tr_id = 'tr_' + turno.id;
          let turnoRow = '<tr id=\"' + tr_id + "\"" + '>' +
                    '<td>' + get_More_Info_Btn + '</td>' +
                    '<td class=\"td_first_name\">' + turno.odontologo.nombre + " " + turno.odontologo.apellido + '</td>' +
                    '<td class=\"td_last_name\">' + turno.paciente.nombre + " " + turno.paciente.apellido + '</td>' +
                    '<td class=\"td_matricula\">' + turno.fecha + '</td>' +
                    '</tr>';                
          $('#turnoTable tbody').append(turnoRow);
        });
      },
      error: function(e){
        alert("ERROR: ", e);
        console.log("ERROR: ", e);
      }
    })
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
});