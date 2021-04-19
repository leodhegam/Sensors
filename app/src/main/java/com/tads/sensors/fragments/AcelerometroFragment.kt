package com.tads.sensors.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tads.sensors.R
import com.tads.sensors.databinding.FragmentAcelerometroBinding

class AcelerometroFragment : Fragment() {

lateinit var binding: FragmentAcelerometroBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_acelerometro,container,false)

        return binding.root
    }


}