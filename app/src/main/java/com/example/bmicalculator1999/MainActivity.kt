package com.example.bmicalculator1999

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.bmicalculator1999.databinding.ActivityMainBinding
import java.text.DecimalFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnCalculatebmi.setOnClickListener(this)
        binding.btnReset.setOnClickListener(this)

    }

    override fun onClick(view: View?) {

        if(Validation()){

        if (view?.id == R.id.btn_calculatebmi) {
            val height: Double = binding.etHeight.text.toString().toDouble() / 100
            val weight: Double = binding.etWeight.text.toString().toDouble()


            val df: DecimalFormat = DecimalFormat("#.###")

            var calculate: Double = weight / (height * height)
            calculate = df.format(calculate).toDouble()
            binding.btnCalculatebmi.visibility = View.INVISIBLE
            binding.btnReset.visibility = View.VISIBLE

            binding.txtBmivalue.text = "Your bmi value is $calculate"

            if (calculate < 18.5) {
                binding.txtBmiresult.text = "You are UnderWeight"
            }
            if (calculate in 18.5..24.9) {
                binding.txtBmiresult.text = "You are Normal"
            }
            if (calculate in 25.0..29.9) {
                binding.txtBmiresult.text = "You are OverWeight"
            }
            if (calculate in 30.0..34.9) {
                binding.txtBmiresult.text = "You are Obese"
            }
            if (calculate >= 35)
                binding.txtBmiresult.text = "Call Doctor"
        }


        else if (view?.id == R.id.btn_reset){
                binding.etHeight.text?.clear()
                binding.etWeight.text?.clear()
                binding.txtBmivalue.text = ""
                binding.txtBmiresult.text = ""
                binding.rgGndr.clearCheck()

            binding.btnCalculatebmi.visibility = View.VISIBLE
            binding.btnReset.visibility = View.INVISIBLE

            }
        }
        }

    private fun Validation(): Boolean {
        if (binding.rgGndr.checkedRadioButtonId == -1){
            Toast.makeText(this, "Please Select a gender", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.etHeight.text?.isEmpty() ==true ){
            binding.etHeight.requestFocus()
            binding.etHeight.error = "Please select height"
            return false
        }
        if (binding.etWeight.text?.isEmpty() ==true){
            binding.etWeight.requestFocus()
            binding.etWeight.error = "Please select weight"
            return false
        }
        return true
    }

}

