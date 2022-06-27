package com.uffey.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.uffey.workoutapp.databinding.ActivityExcerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private var binding: ActivityExcerciseBinding? = null;
    private var resetTimer: CountDownTimer? = null;
    private var resetProgress = 0;
    private var resetExTimer: CountDownTimer? = null;
    private var resetExProgress = 0;
    private var exList: ArrayList<ExerciseModel>? = null;
    private var currentPosition: Int = 0;
    private var exStatusAdapter: ExerciseStatusAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExcerciseBinding.inflate(layoutInflater);
        setContentView(binding?.root);

        setSupportActionBar(binding?.toolbarEx);
        if(supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
        }
        binding?.toolbarEx?.setNavigationOnClickListener{
            onBackPressed()
        }
        exList = Constants.defaultExerciseList();
        SetupExStatusRecyclerView();
        setupResetView();
    }

    private fun SetupExStatusRecyclerView() {
        binding?.exStatus?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        exStatusAdapter = ExerciseStatusAdapter(exList!!);
        binding?.exStatus?.adapter = exStatusAdapter;

    }
    private fun setResetProgressBar() {
        binding?.exProgressBar?.progress = resetProgress;
        binding?.titleEx?.text = "GET READY FOR"
        resetTimer = object : CountDownTimer(1000, 1000) {
            override fun onTick(p0: Long) {
                resetProgress++;
                binding?.exProgressBar?.progress = 10-resetProgress;
                binding?.exTimer?.text= (10 - resetProgress).toString();
            }
            override fun onFinish() {
                currentPosition++;
                setupExResetView()
            }
        }.start();
    }
    private fun setResetExProgressBar() {
        binding?.exTimerProgressBar?.progress = resetExProgress;
        resetExTimer = object : CountDownTimer(1000, 1000) {
            override fun onTick(p0: Long) {
                resetExProgress++;
                binding?.exTimerProgressBar?.progress = 30-resetExProgress;
                binding?.exTimerCounter?.text= (30 - resetExProgress).toString();
            }
            override fun onFinish() {

                if(currentPosition < exList!!.size-1) {
                    exList!![currentPosition].setIsSelected(false);
                    exList!![currentPosition].setIsCompleted(true);
                    exStatusAdapter!!.notifyItemChanged(currentPosition);
                    setupResetView();
                } else {
                    finish();
                    val intent = Intent(this@ExerciseActivity, FinishActivity::class.java);
                    startActivity(intent);
                }
            }
        }.start();
    }
    private fun setupResetView() {
        binding?.exProgress?.visibility = View.VISIBLE
        binding?.titleEx?.visibility = View.VISIBLE
        binding?.exProgressEx?.visibility = View.INVISIBLE
        binding?.exName?.visibility = View.INVISIBLE
        binding?.exImg?.visibility = View.INVISIBLE
        if(resetTimer != null) {
            resetTimer?.cancel()
            resetProgress = 0
        }
        setResetProgressBar();
    }
    private fun setupExResetView() {
        exList!![currentPosition].setIsSelected(true);
        exStatusAdapter!!.notifyItemChanged(currentPosition)
        binding?.exProgress?.visibility = View.INVISIBLE
        binding?.titleEx?.visibility = View.INVISIBLE
        binding?.exProgressEx?.visibility = View.VISIBLE
        binding?.exName?.visibility = View.VISIBLE
        binding?.exImg?.visibility = View.VISIBLE
        if(resetExTimer != null) {
            resetExTimer?.cancel()
            resetExProgress = 0
        }
        binding?.exName?.text = exList!![currentPosition].getName();
        binding?.exImg?.setImageResource(exList!![currentPosition].getImage());
        setResetExProgressBar();
    }
    override fun onDestroy() {
        super.onDestroy()
        if(resetTimer != null) {
            resetTimer?.cancel()
            resetProgress = 0
        }
        binding = null;

    }
}