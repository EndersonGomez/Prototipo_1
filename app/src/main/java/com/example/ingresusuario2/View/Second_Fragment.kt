package com.example.ingresusuario2.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ingresusuario2.ModelView.UserViewModel
import com.example.ingresusuario2.R
import com.example.ingresusuario2.databinding.FragmentSecondBinding


class Second_Fragment : Fragment() {

    //Instanciamos el objeto viewBinding.
    private lateinit var mBinding: FragmentSecondBinding

    //Instanciamos el viewModel.
    private val mViewModel: UserViewModel by viewModels()

    //Instanciamos una variable para recibir el objeto bundle.

    private var accion: String? = null

    //Creamos la variable que va ir guardando la eleccion inmediata del usuario.
    private var seleccion: String = ""
    //Variable que va a ir seteando el textview que mostramos en la pantalla.
    private lateinit var textViewSeteo: TextView

    //Verificamos que el objeto bundle trae un dato y se lo pasamos a la variable.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { bundle ->
            accion = bundle.getString("accion")
        }

        mViewModel.inicializandoViewmodel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Configuramos el objeto viewBinding para enlazar la vista y el codigo.
        mBinding = FragmentSecondBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    //Escribimos el codigo que se ejecutara cuando se cree la vista.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Configuramos el objeto textviewSeteo
        textViewSeteo = mBinding.tvRutMostrar

        /*Tomamos la eleccion del usuario dependiendo de que boton presione y llamamos
         a la funcion que setea el textview */
        mBinding.ivNumero1.setOnClickListener { seleccionar("1") }
        mBinding.ivNumero2.setOnClickListener { seleccionar("2") }
        mBinding.ivNumero3.setOnClickListener { seleccionar("3") }
        mBinding.ivNumero4.setOnClickListener { seleccionar("4") }
        mBinding.ivNumero5.setOnClickListener { seleccionar("5") }
        mBinding.ivNumero6.setOnClickListener { seleccionar("6") }
        mBinding.ivNumero7.setOnClickListener { seleccionar("7") }
        mBinding.ivNumero8.setOnClickListener { seleccionar("8") }
        mBinding.ivNumero9.setOnClickListener { seleccionar("9") }
        mBinding.ivNumero0.setOnClickListener { seleccionar("0") }
        mBinding.ivGuion.setOnClickListener { seleccionar("-") }
        mBinding.ivK.setOnClickListener { seleccionar("k") }

        //Seteamos el valor del textview a vacio para volver a comenzar.
        mBinding.tvReiniciar.setOnClickListener {
            mBinding.tvRutMostrar.text = " "
            //Tengo que formatear seleccion igual para borrar los datos anteriores.
            seleccion = ""
        }

        //Tomamos el valor seleccionado del usuario y pasamos a la siguiente vista.
        mBinding.tvConfirmar.setOnClickListener {

            /* Utilizo la funcion para traer un arraylist de usuarios y asi comparar con el escogido
            por el usuario y pasar al siguiente fragmento de ser correcto.*/
            mViewModel.getUserList().observe(viewLifecycleOwner) { userList ->

                userList.forEach { user ->
                    Log.d("Comparación", "seleccion: $seleccion, RUT: ${user.RUT}")
                    if (user.RUT.equals(seleccion)) {

                        val bundle = Bundle().apply {
                            putString("accion", accion)
                            putString("ID",user.ID)
                            putString("nombre", user.Nombre)
                            putString("rut", user.RUT)
                            putString("cargo",user.Cargo)
                            putString("horario",user.Horario)
                            putString("foto",user.Foto)
                        }
                        findNavController().navigate(
                            R.id.action_second_Fragment_to_third_Fragment,
                            bundle
                        )
                    } else {
                        Log.e("Error", "No se encontró el usuario")
                    }
                }
            }
        }
    }

    //Funcion que toma la cadena de string y la va sumando.
    private fun seleccionar(valor: String) {
        //Variable que guarda la seleccion del usuario.
        seleccion = seleccion + valor

        //Seteamos el textview.
        textViewSeteo.text = seleccion
    }


}

