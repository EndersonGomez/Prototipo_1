package com.example.ingresusuario2.Model.Remote


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiCall {

    //Anotacion de retrofit que ejecuta el metodo http.
    @GET("")
    //Funcion que guarda la respuesta de la api.
    suspend fun fetchUserList(): Response<UserDataApi>

    //Anotacion de retrofit que ejecuta el post a la direccion url otorgada.
    @POST("")
    //Funcion que recibe un objeto y recibe un objeto unit de vuelta para saber que fue exitoso.
    suspend fun postUser(@Body body: UserSend): Response<Unit>
}