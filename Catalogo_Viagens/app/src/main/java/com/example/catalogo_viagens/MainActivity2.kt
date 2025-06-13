package com.example.catalogo_viagens

// Importações necessárias para usar componentes da interface, binding e passar objetos entre activities
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.catalogo_viagens.databinding.ActivityMain2Binding
import com.example.catalogo_viagens.model.Destino

class MainActivity2 : AppCompatActivity() {

    // Declaro o binding para aceder aos elementos da interface da activity_main2 de forma segura
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializo o binding com o layout da segunda activity
        binding = ActivityMain2Binding.inflate(layoutInflater)
        // Defino o layout da activity como sendo o root do binding (ou seja, o layout principal)
        setContentView(binding.root)

        // Configuro a toolbar da segunda activity
        setSupportActionBar(binding.toolbar)
        // Ativo o botão de voltar (seta para trás) na toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Ativo a exibição do título na toolbar
        supportActionBar?.setDisplayShowTitleEnabled(true)

        // Defino a ação do botão de voltar na toolbar — fecha a activity atual e volta à anterior
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // Recupero o objeto 'Destino' que foi passado pela intent (com a chave "DESTINO")
        val destino = intent.getParcelableExtra<Destino>("DESTINO")

        // Se o destino não for nulo, preencho os elementos da interface com os dados recebidos
        destino?.let {
            binding.textNome.text = it.nome // Nome do destino
            binding.textLocalizacao.text = getString(R.string.localizacao, it.localizacao) // Localização formatada
            binding.textPreco.text = getString(R.string.preco, it.preco) // Preço formatado
            binding.textDescricao.text = it.descricao // Descrição completa
            binding.imageView.setImageResource(it.imagem) // Imagem associada ao destino
        }

        // Defino o comportamento do botão "Voltar" na interface — também fecha a activity
        binding.buttonVoltar.setOnClickListener {
            finish()
        }

        // Adiciono um efeito de elevação (sombra) à imagem para dar mais destaque visual
        binding.imageView.elevation = 8f
    }
}
