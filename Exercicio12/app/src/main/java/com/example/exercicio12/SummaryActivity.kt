package com.example.exercicio12

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicio12.databinding.ActivitySummaryBinding
import java.util.Locale

class SummaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySummaryBinding
    private val prices = mapOf("burger" to 5.0, "pizza" to 8.0, "sushi" to 12.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val resultText = StringBuilder()
        var total = 0.0

        total += processItem(
            bundle?.getBoolean("burgerChecked"),
            bundle?.getString("qtyBurger"),
            getString(R.string.hamburguer),
            prices["burger"] ?: 0.0,
            resultText
        )
        total += processItem(
            bundle?.getBoolean("pizzaChecked"),
            bundle?.getString("qtyPizza"),
            getString(R.string.pizza),
            prices["pizza"] ?: 0.0,
            resultText
        )
        total += processItem(
            bundle?.getBoolean("sushiChecked"),
            bundle?.getString("qtySushi"),
            getString(R.string.sushi),
            prices["sushi"] ?: 0.0,
            resultText
        )

        binding.textResult.text = resultText.toString()
        binding.textTotal.text = String.format(Locale.getDefault(), getString(R.string.total_pedido), total)
    }

    private fun processItem(
        isChecked: Boolean?,
        qtyStr: String?,
        name: String,
        price: Double,
        resultText: StringBuilder
    ): Double {
        if (isChecked == true) {
            val qty = qtyStr?.toIntOrNull() ?: 0
            if (qty > 0) {
                val subtotal = qty * price
                val line = String.format(Locale.getDefault(), getString(R.string.pedido_item), name, qty, subtotal)
                resultText.append(line).append("\n")
                return subtotal
            }
        }
        return 0.0
    }
}
