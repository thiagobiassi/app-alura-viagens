package br.com.alura.aluraviagens.model

import java.math.BigDecimal

class Pacote(
    val local: String = "Local não definido",
    var imagem: String = "Imagem não definida",
    val dias: Int = 0,
    val preco: BigDecimal = BigDecimal(0)) {

}