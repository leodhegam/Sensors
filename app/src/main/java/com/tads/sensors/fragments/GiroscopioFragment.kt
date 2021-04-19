package com.tads.sensors.fragments

import android.content.Context
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
import androidx.databinding.DataBindingUtil
import com.tads.sensors.R
import com.tads.sensors.databinding.FragmentGiroscopioBinding
import com.tads.sensors.databinding.FragmentProximidadeBinding

class GiroscopioFragment : Fragment(),SensorEventListener {
    lateinit var binding: FragmentGiroscopioBinding
    private lateinit var sensorManager: SensorManager
    lateinit var giroscopioSensor: Sensor
    private lateinit var text: TextView
    private var isGyroscopeSensorManager: Boolean? = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_giroscopio, container, false)

        binding.apply {
            text = textGiroscopio.findViewById(R.id.textGiroscopio)
        }
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null) {
            giroscopioSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            isGyroscopeSensorManager = true;
        } else {
            isGyroscopeSensorManager = false;
        }
        return binding.root
    }
    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            if(event.sensor.type == Sensor.TYPE_GYROSCOPE){
                text.setText(event.values[0].toFloat().toString());
            }else {
                text.setText("Away")
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        if(isGyroscopeSensorManager == true){
            sensorManager.registerListener(this,giroscopioSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    override fun onPause() {
        super.onPause()
        if(isGyroscopeSensorManager == true){
            sensorManager.unregisterListener(this)
        }
    }

}