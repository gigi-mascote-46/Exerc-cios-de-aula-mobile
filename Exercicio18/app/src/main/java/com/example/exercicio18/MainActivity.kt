package com.example.exercicio18

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicio18.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAudio1.setOnClickListener {
            playAudio(R.raw.som1)
        }

        binding.buttonAudio2.setOnClickListener {
            playAudio(R.raw.som2)
        }

        // Se tiveres mais botões, adiciona aqui:
        // binding.buttonAudio3.setOnClickListener {
        //     playAudio(R.raw.som3)
        // }
    }

    private fun playAudio(audioResId: Int) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(this, audioResId)
        mediaPlayer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}
