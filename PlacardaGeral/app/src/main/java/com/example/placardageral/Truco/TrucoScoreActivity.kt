package com.example.placardageral.Truco

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.placardageral.R
import com.example.placardageral.databinding.ActivityTrucoScoreBinding

class TrucoScoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrucoScoreBinding
    private lateinit var trucoViewModel: TrucoViewModel

    private var playerOneScore = 0
    private var playerTwoScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrucoScoreBinding.inflate(layoutInflater)
        initViewModel()
        initObserver()
        setupListeners()
        setContentView(binding.root)

        if(savedInstanceState != null){
            playerOneScore =  savedInstanceState.getInt("PLAYER_ONE_SCORE")
            playerTwoScore = savedInstanceState.getInt("PLAYER_TWO_SCORE")

            binding.tvPlayerOneScore.text = playerOneScore.toString()
            binding.tvPlayerTwoScore.text = playerTwoScore.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("PLAYER_ONE_SCORE", playerOneScore)
        outState.putInt("PLAYER_TWO_SCORE", playerTwoScore)
    }

    private fun initObserver() {
        trucoViewModel.scoreHomePoint.observe(this, Observer { point ->
            binding.tvPlayerOneScore.text = point.toString()
        })

        trucoViewModel.scoreVisitPoint.observe(this, Observer { point ->
            binding.tvPlayerTwoScore.text = point.toString()
        })
    }

    private fun initViewModel() {
        trucoViewModel = ViewModelProvider(this).get(TrucoViewModel::class.java)
    }

    private fun setupListeners() {
        binding.btFinishMatch.setOnClickListener{
            super.finish()
        }

        binding.btRevenge.setOnClickListener{
            trucoViewModel.start()
        }

        binding.btShareWhatsapp.setOnClickListener{
            shareWhatsApp()
        }

        binding.btMinusOnePlayerOne.setOnClickListener{
            trucoViewModel.goalVisit(-1)
        }
        binding.btMinusOnePlayerTwo.setOnClickListener{
            trucoViewModel.goalHome(-1)
        }
        binding.btPlusOnePlayerOne.setOnClickListener{
            trucoViewModel.goalHome(1)
        }
        binding.btPlusOnePlayerTwo.setOnClickListener{
            trucoViewModel.goalVisit(1)
        }
        binding.btPlusThreePlayerOne.setOnClickListener{
            trucoViewModel.goalHome(3)
        }
        binding.btPlusThreePlayerTwo.setOnClickListener{
            trucoViewModel.goalVisit(3)
        }
        binding.btPlusSixPlayerOne.setOnClickListener{
            trucoViewModel.goalHome(6)
        }
        binding.btPlusSixPlayerTwo.setOnClickListener{
            trucoViewModel.goalVisit(6)
        }
        binding.btPlusNinePlayerOne.setOnClickListener{
            trucoViewModel.goalHome(9)
        }
        binding.btPlusNinePlayerTwo.setOnClickListener{
            trucoViewModel.goalVisit(9)
        }
        binding.btPlusTwelvePlayerOne.setOnClickListener{
            trucoViewModel.goalHome(12)
        }
        binding.btPlusTwelvePlayerTwo.setOnClickListener{
            trucoViewModel.goalVisit(12)
        }
    }

    private fun shareWhatsApp() {
        try{
            val whatsAppIntent = Intent(Intent.ACTION_SEND)
            whatsAppIntent.type = "text/plain"
            whatsAppIntent.setPackage("com.whatsapp")

            val message = getString(
                R.string.message_to_share,
                binding.tvPlayerOneName.text,
                binding.tvPlayerTwoName.text,
                binding.tvPlayerOneScore.text.toString().toInt(),
                binding.tvPlayerTwoScore.text.toString().toInt())

            whatsAppIntent.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(whatsAppIntent)
        }catch (e: ActivityNotFoundException) {
            //Toast.makeText(this, "WhatsApp não instalado", Toast.LENGTH_LONG).show()
            val appPackageName = "com.whatsapp"
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            } catch (anfe: android.content.ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
            }
        }
    }
}

