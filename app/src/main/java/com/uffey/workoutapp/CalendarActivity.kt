package com.uffey.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uffey.workoutapp.databinding.ActivityCalendarBinding

class CalendarActivity : AppCompatActivity() {
    private var binding : ActivityCalendarBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater);
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarHis)
        if(supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
            supportActionBar?.title = "History"
        }
        binding?.toolbarHis?.setNavigationOnClickListener{
            onBackPressed()
        }
    }
}