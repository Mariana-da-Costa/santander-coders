package com.example.sorteio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sorteio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var random = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        raffle()
    }

    fun raffle() {
        val n1 = 1
        val n2 = 10
        random = (n1..n2).random()
        binding.button.setOnClickListener {
            var inputed = binding.input.text.toString().toInt()
            validar(inputed, random)
        }
    }

    fun validar(inputado: Int, sorteado: Int): String {
        if (inputado < sorteado) {
            binding.tv.text = "O número sorteado é maior"
        } else if (inputado > sorteado) {
            binding.tv.text = "O número sorteado é menor"
        } else {
            binding.tv.text = "Você acertou!"
        }
        return binding.tv.text.toString()
    }
}