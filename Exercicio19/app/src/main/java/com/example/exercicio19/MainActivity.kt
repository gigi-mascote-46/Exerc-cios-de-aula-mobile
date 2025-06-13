package com.example.exercicio19

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicio19.databinding.ActivityMainBinding
import com.example.exercicio19.databinding.ListItemBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val destinations = listOf(
        Destination("Lisboa", "Portugal", "€200", R.drawable.lisboa, "Capital de Portugal, cheia de história e cultura."),
        Destination("Paris", "França", "€350", R.drawable.paris, "Cidade do amor, famosa pela Torre Eiffel."),
        Destination("Tóquio", "Japão", "€800", R.drawable.tokyo, "Moderna e tradicional, uma cidade única.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = object : ArrayAdapter<Destination>(this, R.layout.list_item, destinations) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val itemBinding: ListItemBinding = if (convertView == null) {
                    ListItemBinding.inflate(layoutInflater, parent, false)
                } else {
                    ListItemBinding.bind(convertView)
                }

                val dest = destinations[position]
                itemBinding.imageView.setImageResource(dest.imageResId)
                itemBinding.nameText.text = dest.name
                itemBinding.locationText.text = dest.location
                itemBinding.priceText.text = dest.price

                return itemBinding.root
            }
        }

        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val selected = destinations[position]
            val intent = Intent(this, MainActivity2::class.java).apply {
                putExtra("name", selected.name)
                putExtra("location", selected.location)
                putExtra("price", selected.price)
                putExtra("imageResId", selected.imageResId)
                putExtra("description", selected.description)
            }
            startActivity(intent)
        }
    }
}
