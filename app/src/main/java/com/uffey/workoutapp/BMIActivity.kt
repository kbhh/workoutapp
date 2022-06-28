package com.uffey.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.uffey.workoutapp.databinding.ActivityBmiactivityBinding
import kotlin.math.pow
import kotlin.math.round

class BMIActivity : AppCompatActivity() {
    private var binding: ActivityBmiactivityBinding?  = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater);
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarBmi);
        if(supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
            supportActionBar?.title = "BMI"
        }
        binding?.toolbarBmi?.setNavigationOnClickListener{
            onBackPressed()
        }

       binding?.calculateBtn?.setOnClickListener{
           bmiParser()
       }
    }


    private fun bmiParser() {
       var height =  binding?.bmiHeight?.editText?.text
       var weight = binding?.bmiWeight?.editText?.text

       if(height?.isNotEmpty() == true && weight?.isNotEmpty() == true)  {
         val result: Double = calculateBMI(height!!.toString().toDouble(), weight!!.toString().toDouble())
         binding?.bmiResultTx?.text = round(result).toString();
         var msg = ""
         var label = ""
         when {
            result < 18.5 -> {
                 msg = "Oops you really need to take care of your self! Maybe eat more and workout."
                 label = "Under Weight"
            }
            result > 25 && result < 29.9 -> {
                msg = "Oops you really need to take care of your self! Maybe workout."
                label = "Over Weight"
            }
           result > 30 -> {
               msg = "Oops you really need to take care of your self! Maybe go to a doctor."
               label = "Obesity"
           }
           else -> {
               msg = "You are i normal condition maybe workout."
               label = "Obesity"
           }
         }
        binding?.bmiLabelTx?.text = label
        binding?.bmiMessageTx?.text = msg
       } else {
           Toast.makeText(this, "Pleas input valid data", Toast.LENGTH_LONG).show();
       }
      binding?.resultMsgs?.visibility = View.VISIBLE
    }
    private fun calculateBMI(height: Double, weight: Double): Double {
        var doHeight = height.pow(2)
        return weight / doHeight
    }
}

