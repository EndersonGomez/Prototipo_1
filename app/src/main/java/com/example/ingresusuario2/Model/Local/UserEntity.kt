package com.example.ingresusuario2.Model.Local

import androidx.room.Entity
import androidx.room.PrimaryKey

//Clase que representa una tabla dentro nuestra base de datos.
@Entity(tableName = "TABLE_USUARIO")
data class UserEntity (

    val  Nombre: String,
    @PrimaryKey
    val  RUT: String,
    val  Cargo: String,
    val  Horario: String,
    val Foto: String

)