package com.tads.sensors.fragments

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.tads.sensors.MainActivity
import com.tads.sensors.R
import com.tads.sensors.databinding.FragmentAcelerometroBinding

class AcelerometroFragment : Fragment(), SensorEventListener {

    lateinit var binding: FragmentAcelerometroBinding
    private lateinit var sensorManager: SensorManager
    lateinit var sensorAcelerometro: Sensor

    private lateinit var xText : TextView
    private lateinit var yText : TextView
    private lateinit var zText : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_acelerometro, container, false)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        xText = binding.xText.findViewById(R.id.xText)
        yText = binding.yText.findViewById(R.id.yText)
        zText = binding.zText.findViewById(R.id.zText)

        setUpSensor()

        return binding.root
    }

    private fun setUpSensor() {
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sensorAcelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager.registerListener(
            this,
            sensorAcelerometro,
            SensorManager.SENSOR_DELAY_FASTEST,
            SensorManager.SENSOR_DELAY_FASTEST
        )
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            xText.setText("X" + event.values[0].toFloat().toString() )
            yText.setText("Y" + event.values[1].toFloat().toString())
            zText.setText("Z" + event.values[2].toFloat().toString())

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
    }
}