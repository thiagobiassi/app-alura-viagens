package br.com.alura.aluraviagens

import br.com.alura.aluraviagens.extension.formataBrasileiro
import br.com.alura.aluraviagens.model.Pacote

class MoedaUtil {

    fun formatarParaBrasileiro(
        pacote: Pacote
    ): String? {
        return pacote.preco.formataBrasileiro()
    }
}