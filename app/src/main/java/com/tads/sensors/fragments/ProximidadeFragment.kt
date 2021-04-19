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
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.tads.sensors.R
import com.tads.sensors.databinding.FragmentProximidadeBinding

class ProximidadeFragment : Fragment(), SensorEventListener {

    lateinit var binding: FragmentProximidadeBinding
    private lateinit var sensorManager: SensorManager
    lateinit var proximitySensor: Sensor
    private lateinit var text: TextView
    private var isProximitySensorManager: Boolean? = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_proximidade, container, false)

        binding.apply {
            text = textProximity.findViewById(R.id.textProximity)
        }
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
            proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            isProximitySensorManager = true;
        } else {
            isProximitySensorManager = false;
        }
        return binding.root
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            if(event.sensor.type == Sensor.TYPE_PROXIMITY){
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
        if(isProximitySensorManager == true){
            sensorManager.registerListener(this,proximitySensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    override fun onPause() {
        super.onPause()
        if(isProximitySensorManager == true){
            sensorManager.unregisterListener(this)
        }
    }

}

