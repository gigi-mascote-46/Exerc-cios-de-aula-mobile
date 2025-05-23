package com.example.exercicio12

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicio12.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEfetuarPedido.setOnClickListener {
            if (!binding.cbBurger.isChecked && !binding.cbPizza.isChecked && !binding.cbSushi.isChecked) {
                Toast.makeText(this, "Seleciona pelo menos um produto!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            fun isValidQty(checked: Boolean, qtyStr: String): Boolean {
                return !checked || (qtyStr.toIntOrNull()?.let { it > 0 } == true)
            }

            if (!isValidQty(binding.cbBurger.isChecked, binding.etQtyBurger.text.toString()) ||
                !isValidQty(binding.cbPizza.isChecked, binding.etQtyPizza.text.toString()) ||
                !isValidQty(binding.cbSushi.isChecked, binding.etQtySushi.text.toString())
            ) {
                Toast.makeText(this, "Quantidade inválida!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, SplashActivity::class.java).apply {
                putExtra("burgerChecked", binding.cbBurger.isChecked)
                putExtra("pizzaChecked", binding.cbPizza.isChecked)
                putExtra("sushiChecked", binding.cbSushi.isChecked)

                putExtra("qtyBurger", binding.etQtyBurger.text.toString())
                putExtra("qtyPizza", binding.etQtyPizza.text.toString())
                putExtra("qtySushi", binding.etQtySushi.text.toString())
            }
            startActivity(intent)
        }
    }
}