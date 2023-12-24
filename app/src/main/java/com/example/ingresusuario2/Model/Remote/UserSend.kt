package com.example.ingresusuario2.Model.Remote

//Clase que envia los datos al google shet con su formato y parametros.
data class UserSend (
    //Id de la hoja de calculo.
    val spreadsheet_id: String,
    //nombre de la hoja.
    val sheet: String,
    //Formato del json que espera nuestra respuesta.
    val rows: List<List<String>>
)
