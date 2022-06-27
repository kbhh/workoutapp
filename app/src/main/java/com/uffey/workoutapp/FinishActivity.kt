package com.uffey.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.uffey.workoutapp.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {
    private var binding: ActivityFinishBinding? = null
    private var finishBtn: Button ? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater);
        setContentView(binding?.root);
        binding?.fnBtn?.setOnClickListener{
            var intent = Intent(this@FinishActivity, MainActivity::class.java)
            startActivity(intent);
        }
    }
}