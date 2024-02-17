package com.example.ingresusuario2.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.ingresusuario2.Model.Remote.UserSend
import com.example.ingresusuario2.ModelView.UserViewModel
import com.example.ingresusuario2.R
import com.example.ingresusuario2.databinding.FragmentSecondBinding
import java.util.Date


class Second_Fragment : Fragment() {

    //Instanciamos el objeto viewBinding.
    private lateinit var mBinding: FragmentSecondBinding
    //Instanciamos el viewModel.
    private val mViewModel: UserViewModel by viewModels()

    //Instanciamos una variable para recibir los objeto bundle.
    private var accion: String? = null
    private var id: String? = null
    private var nombre: String? = null
    private var rut: String? = null
    private var cargo: String? = null
    private var horario: String? = null
    private var foto: String? = null
    private var fechaHora: Date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->

            //Guardamos los datos obtenidos del usuario en las variables.
            accion = bundle.getString("accion")
            id = bundle.getString("ID")
            nombre = bundle.getString("nombre")
            rut = bundle.getString("rut")
            cargo = bundle.getString("cargo")
            horario = bundle.getString("horario")
            foto = bundle.getString("foto")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSecondBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Cargamos los datos en la vista.
        Glide.with(requireContext()).load(foto).centerCrop().error(R.drawable.ic_launcher_foreground) .into(mBinding.ivImagen)
        mBinding.tvNombre.text = nombre
        mBinding.tvRut.text = rut
        mBinding.tvCargo.text = cargo
        mBinding.tvHorario.text = horario

        //Guardamos los datos obtenidos en un objeto para pasarlos como parametro al post.
        val envioData = UserSend(
            spreadsheet_id = "",
            sheet = "Control",
            rows = listOf(
                listOf(
                    "${id}",
                    "${accion}",
                    "${nombre}",
                    "${rut}",
                    "${cargo}",
                    "${fechaHora}",
                    "trabajo",
                    "${foto}"
                )
            )
        )

        //Configuramos el evento escuchador para llamar al metodo
        mBinding.btConfirmar.setOnClickListener {
            mViewModel.enviarDatosAlServidor(envioData)

            val bundle = Bundle().apply {
                putString("accion",accion)
            }

            if (accion== "Entrada"){
                findNavController().navigate(R.id.action_second_Fragment_to_third_fragment,bundle)
            }else{
                findNavController().navigate(R.id.action_second_Fragment_to_first_Fragment)
            }
        }
    }
}

