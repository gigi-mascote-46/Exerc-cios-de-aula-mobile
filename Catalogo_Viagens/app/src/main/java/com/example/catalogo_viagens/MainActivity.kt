package com.example.catalogo_viagens

// Importações necessárias para usar componentes da interface, RecyclerView e binding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catalogo_viagens.adapter.DestinoAdapter
import com.example.catalogo_viagens.databinding.ActivityMainBinding
import com.example.catalogo_viagens.model.Destino

class MainActivity : AppCompatActivity() {

    // Declaro o binding que me vai permitir aceder aos elementos da interface (XML) de forma segura
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializo o binding com o layout da activity (uso o inflate para "ligar" o layout ao código)
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Defino o conteúdo da activity como sendo a root do layout (ou seja, o layout principal)
        setContentView(binding.root)

        // Configuro a toolbar da aplicação
        setSupportActionBar(binding.toolbar)
        // Ativo a exibição do título na toolbar
        supportActionBar?.setDisplayShowTitleEnabled(true)

        // Crio uma lista de destinos com dados de exemplo (nome, país, preço, imagem e descrição)
        val listaDestinos = listOf(
            Destino(
                "Paris",
                "França",
                1200.0,
                R.drawable.destino1,
                "Paris, a capital da França, é uma importante cidade europeia e um centro mundial de arte, moda, gastronomia e cultura. Sua paisagem urbana do século XIX é cortada por avenidas largas e pelo rio Sena. Pontos de destaque incluem a Torre Eiffel, a Catedral de Notre-Dame, um estilo gótico construída no século XII, e o Museu do Louvre, que abriga a Mona Lisa."
            ),
            Destino(
                "Roma",
                "Itália",
                1100.0,
                R.drawable.destino2,
                "Roma é a capital da Itália, uma cidade cosmopolita, com quase 3.000 anos de arte, arquitetura e cultura influentes no mundo todo. Ruínas antigas como o Fórum e o Coliseu evocam o poder do antigo Império Romano. A Cidade do Vaticano, sede da Igreja Católica Romana, tem a Basílica de São Pedro e os museus do Vaticano, que abrigam obras-primas como os afrescos da Capela Sistina de Michelângelo."
            ),
            Destino(
                "Nova Iorque",
                "EUA",
                1500.0,
                R.drawable.destino3,
                "Nova Iorque compreende 5 distritos situados no encontro do rio Hudson com o Oceano Atlântico. No centro da cidade fica Manhattan, um distrito com alta densidade demográfica que está entre os principais centros comerciais, financeiros e culturais do mundo. Entre seus pontos emblemáticos, destacam-se arranha-céus, como o Empire State Building, e o enorme Central Park. O teatro da Broadway fica em meio às luzes de neon da Times Square."
            )
        )

        // Configuro a RecyclerView para mostrar a lista de destinos
        binding.recyclerViewDestinos.apply {
            // Indico que quero um layout vertical (lista em coluna)
            layoutManager = LinearLayoutManager(this@MainActivity)
            // Associo o adaptador que vai ligar os dados à RecyclerView
            adapter = DestinoAdapter(listaDestinos)
            // Otimizo o desempenho da RecyclerView ao indicar que o tamanho dos itens é fixo
            setHasFixedSize(true)
        }
    }
}
