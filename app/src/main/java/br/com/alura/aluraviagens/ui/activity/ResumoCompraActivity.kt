package br.com.alura.aluraviagens.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.aluraviagens.DataUtil
import br.com.alura.aluraviagens.R
import br.com.alura.aluraviagens.ResourceUtil
import br.com.alura.aluraviagens.extension.formataBrasileiro
import br.com.alura.aluraviagens.model.Pacote
import java.util.*

class ResumoCompraActivity : AppCompatActivity() {
    private val TITLE = "Resumo da Compra"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumo_compra)
        title = TITLE
        val receberExtras = intent.extras

        receberLocal(receberExtras)
        receberImagem(receberExtras)
        receberDias(receberExtras)
        receberValor(receberExtras)
    }

    private fun receberLocal(receberExtras: Bundle?) {
        val local = receberExtras!!.getString("local")
        val localPacote = findViewById<TextView>(R.id.resumo_compra_tv_local)
        localPacote.text = local
    }

    private fun receberImagem(receberExtras: Bundle?) {
        val imagem = receberExtras!!.getString("imagem")
        val imagemPacote = findViewById<ImageView>(R.id.resumo_compra_iv_imagem_pacote)
        val devolverDrawable =
            ResourceUtil().devolverDrawable(this, Pacote(imagem = imagem.toString()))
        imagemPacote.setImageDrawable(devolverDrawable)
    }

    private fun receberDias(receberExtras: Bundle?) {
        val dias = receberExtras!!.getString("dias")
        val diasPacote = findViewById<TextView>(R.id.resumo_compra_tv_dias)
        val (dataVolta, dataFormatadaIda, dataFormatadaVolta) = DataUtil().periodoEmTexto(dias)
        diasPacote.text =
            "$dataFormatadaIda - $dataFormatadaVolta de ${dataVolta.get(Calendar.YEAR)}"
    }

    private fun receberValor(receberExtras: Bundle?) {
        val preco = receberExtras!!.getString("preco")
        val valorPacote = findViewById<TextView>(R.id.resumo_compra_tv_valor_do_pacote)
        val valorFormatado = preco!!.toBigDecimal().formataBrasileiro()
        valorPacote.text = valorFormatado
    }
}