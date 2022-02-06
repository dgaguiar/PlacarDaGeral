package com.example.placardageral.Player

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.placardageral.*
import com.example.placardageral.Basket.BasketActivity
import com.example.placardageral.Football.FootballActivity
import com.example.placardageral.Player.GAME.*
import com.example.placardageral.Truco.TrucoScoreActivity
import com.example.placardageral.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding
    private lateinit var playerViewModel: PlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        initViewModel()
        initObserver()
        setupListeners()
        setContentView(binding.root)
    }

    private fun initObserver() {
        playerViewModel.selectedGame.observe(this, Observer { selectedGame ->
            if (selectedGame == TRUCO) {
                binding.btPlayTruco.isSelected = true
            } else if (selectedGame == BASKET) {
                binding.btPlayBasket.isSelected = true
            } else if (selectedGame == FOOTBALL) {
                binding.btPlayFootball.isSelected = true
            } else {
                binding.btPlayTruco.isSelected = false
                binding.btPlayBasket.isSelected = false
                binding.btPlayFootball.isSelected = false
            }
        })
    }

    private val previewRequest =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val lastResult = getString(
                    R.string.message_to_share,
                    it.data?.getStringExtra(KEY_RESULT_EXTRA_PLAYER_ONE_NAME),
                    it.data?.getStringExtra(KEY_RESULT_EXTRA_PLAYER_TWO_NAME),
                    it.data?.getIntExtra(KEY_RESULT_EXTRA_PLAYER_ONE_SCORE, 0),
                    it.data?.getIntExtra(KEY_RESULT_EXTRA_PLAYER_TWO_SCORE, 0)
                )
//                binding.tvLastGame.text = lastResult
            }
        }

    private fun initViewModel() {
        playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)
    }

    private fun setupListeners() {
        binding.btStart.setOnClickListener {
            playerViewModel.selectedGame.observe(this, Observer { selectedGame ->
                if (selectedGame == TRUCO) {
                    val nextScreen = Intent(this, TrucoScoreActivity::class.java)

                    nextScreen.putExtra("PLAYER1", binding.etPlayer1.text.toString())
                    nextScreen.putExtra("PLAYER2", binding.etPlayer2.text.toString())

                    startActivity(nextScreen)

                    previewRequest.launch(nextScreen)
                } else if (selectedGame == BASKET) {
                    val nextScreen = Intent(this, BasketActivity::class.java)

                    nextScreen.putExtra("PLAYER1", binding.etPlayer1.text.toString())
                    nextScreen.putExtra("PLAYER2", binding.etPlayer2.text.toString())

                    startActivity(nextScreen)

                    previewRequest.launch(nextScreen)
                } else if (selectedGame == FOOTBALL) {
                    val nextScreen = Intent(this, FootballActivity::class.java)

                    nextScreen.putExtra("PLAYER1", binding.etPlayer1.text.toString())
                    nextScreen.putExtra("PLAYER2", binding.etPlayer2.text.toString())

                    startActivity(nextScreen)

                    previewRequest.launch(nextScreen)
                } else {
//                    binding.btPlayTruco.isSelected = false
//                    binding.btPlayBasket.isSelected = false
//                    binding.btPlayFootball.isSelected = false
                }
            })
        }

        binding.btPlayBasket.setOnClickListener {
            playerViewModel.selectGame(BASKET)
        }

        binding.btPlayFootball.setOnClickListener {
            playerViewModel.selectGame(FOOTBALL)
        }

        binding.btPlayTruco.setOnClickListener {
            binding.btPlayTruco.isSelected = !binding.btPlayTruco.isSelected
            playerViewModel.selectGame(TRUCO)
        }
    }
}