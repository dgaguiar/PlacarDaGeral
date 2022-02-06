package com.example.placardageral.Football

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.placardageral.Basket.BasketViewModel
import com.example.placardageral.databinding.ActivityFootballBinding

class FootballActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFootballBinding
    private lateinit var footballViewModel: FootballViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFootballBinding.inflate(layoutInflater)
        initViewModel()
        initObserver()
        setupListeners()
        setContentView(binding.root)
    }

    private fun initObserver(){
        footballViewModel.scoreHomePoint.observe(this, Observer { point ->
            binding.tvFScorePlayerOne.text = point.toString()
        })

        footballViewModel.scoreVisitPoint.observe(this, Observer { point ->
            binding.tvScoreFPlayerTwo.text = point.toString()
        })
    }

    private fun initViewModel() {
        footballViewModel = ViewModelProvider(this).get(FootballViewModel::class.java)
    }

    private fun setupListeners() {
        binding.btFFinish.setOnClickListener{
            super.finish()
        }

        binding.btFRevange.setOnClickListener{
            footballViewModel.start()
        }

        binding.btHome.setOnClickListener{
            footballViewModel.goalHome(1)
        }

        binding.btVisit.setOnClickListener{
            footballViewModel.goalVisit(1)
        }
    }
}