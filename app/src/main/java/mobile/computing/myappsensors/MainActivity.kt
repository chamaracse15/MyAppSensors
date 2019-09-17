package mobile.computing.myappsensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.hardware.SensorManager


class MainActivity : AppCompatActivity(), SensorEventListener {

    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null
    private var mStep: Sensor? = null
    private var mLinearAcceleration: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mStep = mSensorManager!!.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        mLinearAcceleration = mSensorManager!!.getDefaultSensor((Sensor.TYPE_LINEAR_ACCELERATION))

        startButton.setOnClickListener {
            onResume()
        }

        endButton.setOnClickListener {
            onPause()
        }

    }

    override fun onResume() {
        super.onResume()
        mSensorManager!!.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        mSensorManager!!.registerListener(this, mStep, SensorManager.SENSOR_DELAY_NORMAL)
        mSensorManager!!.registerListener(this, mLinearAcceleration, SensorManager.SENSOR_DELAY_NORMAL)
    }


    override fun onSensorChanged(event: SensorEvent?) {
        when(event?.sensor?.type){
            Sensor.TYPE_ACCELEROMETER ->{
                xValue.text = event.values[0].toString()
                yValue.text = event.values[1].toString()
                zValue.text = event.values[2].toString()
            }

            Sensor.TYPE_STEP_DETECTOR -> {
                stepValue.text = event.values[0].toString()
            }

            Sensor.TYPE_LINEAR_ACCELERATION -> {
                xAcceleration.text = event.values[0].toString()
                yAcceleration.text = event.values[1].toString()
                zAcceleration.text = event.values[2].toString()
            }


        }
    }

    override fun onPause() {
        super.onPause()
        mSensorManager!!.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}
