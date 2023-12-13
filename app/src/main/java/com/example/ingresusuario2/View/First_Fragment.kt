package com.example.ingresusuario2.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ingresusuario2.R
import com.example.ingresusuario2.databinding.FragmentFirstBinding


class First_Fragment : Fragment() {

    //Instanciamos el objeto viewBinding.
    private lateinit var mBinding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //Configuramos el objeto de la clase viewBinding.
        mBinding = FragmentFirstBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Configuramos el evento escuchador para que tome la decision del usuario y guardamos su decision en una variable.
        mBinding.button1.setOnClickListener {

            val bundle = Bundle().apply {
                putString("accion","Entrada")
            }
            findNavController().navigate(R.id.action_first_Fragment_to_second_Fragment,bundle)
        }

        //Configuramos el evento escuchador para que tome la decision del usuario y guardamos su decision en una variable.
        mBinding.button2.setOnClickListener {

            val bundle = Bundle().apply {
                putString("accion","Salida")
            }
            findNavController().navigate(R.id.action_first_Fragment_to_second_Fragment,bundle)
        }
    }
}