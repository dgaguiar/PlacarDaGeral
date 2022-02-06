package com.example.placardageral.Basket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.placardageral.databinding.ActivityBasketBinding

class BasketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBasketBinding
    private lateinit var basketViewModel: BasketViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBasketBinding.inflate(layoutInflater)
        initViewModel()
        initObserver()
        setupListeners()
        setContentView(binding.root)
    }

    private fun initObserver(){
        basketViewModel.scoreHomePoint.observe(this, Observer { point ->
            binding.tvScorePlayerOne.text = point.toString()
        })

        basketViewModel.scoreVisitPoint.observe(this, Observer { point ->
            binding.tvScorePlayerTwo.text = point.toString()
        })
    }

    private fun initViewModel() {
        basketViewModel = ViewModelProvider(this).get(BasketViewModel::class.java)
    }

    private fun setupListeners() {
        binding.btFinish.setOnClickListener{
            super.finish()
        }

        binding.btRevange.setOnClickListener{
            basketViewModel.start()
        }

        binding.btAddOnePointPlayerOne.setOnClickListener{
            basketViewModel.goalHome(1)
        }

        binding.btAddThreePointPlayerOne.setOnClickListener{
            basketViewModel.goalHome(3)
        }

        binding.btOnePointPlayerTwo.setOnClickListener{
            basketViewModel.goalVisit(1)
        }

        binding.btThreePointPlayerTwo.setOnClickListener{
            basketViewModel.goalVisit(3)
        }
    }
}