package com.example.ingresusuario2.Model.Remote

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Clase que crea la instancia de Retrofit.
class RetrofitClient {

    // Código que instancia el objeto Retrofit.
    companion object {

        // Variable constante que tiene la URL base.
        private const val BASE_URL =
            "https://script.google.com/macros/s/"

        // Función que ejecuta el código Retrofit.
        fun retrofitInstance(): ApiCall {
            // Se crea un intenceptor de registro para intenceptar las solicitudes y respuestas http y saber claramente que esta sucediendo.
            val loggingInterceptor = HttpLoggingInterceptor { message ->
                Log.d(
                    "RETROFIT DEPURADOR",
                    message
                )
            }
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            //Se crea un cliente para agregarle el inteceptor.
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client) // Usar el cliente con el interceptor
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiCall::class.java)
        }
    }
}
