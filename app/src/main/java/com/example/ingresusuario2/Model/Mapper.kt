package com.example.ingresusuario2.Model

import com.example.ingresusuario2.Model.Local.UserEntity
import com.example.ingresusuario2.Model.Remote.UserDataApi

//Funcion que lleva los datos de la llamada api a la base de datos para guardarlo.
fun fromInternetToUserEntity(userlist: UserDataApi) : List<UserEntity> {
    return userlist.persona.map {
        UserEntity(
            ID = it.ID ?: "",
            Nombre = it.Nombre ?: "",
            RUT = it.RUT ?: "",
            Cargo = it.Cargo ?: "",
            Horario = it.Horario ?: "",
            Foto = it.Foto ?: "",
        )
    }
}