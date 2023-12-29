package com.example.ingresusuario2.Model.Remote

//Clase que crea el objeto para enviar los datos en el post.
data class UserSend (

    val spreadsheet_id: String,
    val sheet: String,
    val rows: List<List<String>>
)
