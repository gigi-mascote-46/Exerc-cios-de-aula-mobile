package com.example.recycleviewexemplo.data

import com.example.recycleviewexemplo.model.Carro

class CarroMock {

    val listaCarros = ArrayList<Carro>()

    init {
        for (i in 1..100) {
            listaCarros.add(
                Carro(
                    modelo = "Modelo $i",
                    ano = 2000 + (i % 20),
                    cor = if (i % 2 == 0) "Preto" else "Branco",
                    matricula = "00-$i-AB",
                    preco = 10000.0 + (i * 150)
                )
            )
        }
    }
}
