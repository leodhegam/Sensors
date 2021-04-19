package com.tads.sensors.fragments

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.tads.sensors.R
import com.tads.sensors.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        binding.apply {
            buttonAcelerometro.setOnClickListener{
                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_acelerometroFragment)
            }
            buttonGiroscopio.setOnClickListener{
                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_giroscopioFragment)
            }
            buttonLuz.setOnClickListener{
                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_luzFragment)
            }
            buttonProximidade.setOnClickListener{
                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_proximidadeFragment)
            }
        }

        return binding.root
    }


}