package br.com.alura.aluraviagens

import android.view.View
import android.widget.TextView
import br.com.alura.aluraviagens.R
import br.com.alura.aluraviagens.model.Pacote

class DiasUtil {

    fun formataDiasEmTexto(
        pacote: Pacote
    ): String {

        if (pacote.dias == 1) {
            return "${pacote.dias} dia"
        } else {
            return "${pacote.dias} dias"
        }
    }

}