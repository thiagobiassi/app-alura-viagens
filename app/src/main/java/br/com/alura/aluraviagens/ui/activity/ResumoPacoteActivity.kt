package br.com.alura.aluraviagens.ui.activity

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.aluraviagens.DataUtil
import br.com.alura.aluraviagens.R
import br.com.alura.aluraviagens.ResourceUtil
import br.com.alura.aluraviagens.extension.formataBrasileiro
import br.com.alura.aluraviagens.model.Pacote
import java.util.*

class ResumoPacoteActivity : AppCompatActivity() {
    private val TITLE = "Resumo do pacote"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumo_pacote)
        title = TITLE

        val (receberExtras, local) = recebeExtras()
        val imagem = receberExtras!!.getString("imagem").toString()

        alterarLocal(local)
        val dias = alterarDias(receberExtras)
        alterarImagem(imagem)
        alterarPreco(receberExtras)
        alterarData(dias)

        botaoPagamento(receberExtras, local, imagem, dias)

    }

    private fun botaoPagamento(
        receberExtras: Bundle?,
        local: String?,
        imagem: String,
        dias: String?
    ) {
        val preco = receberExtras!!.getString("preco")
        val botaoPagamento = findViewById<Button>(R.id.resumo_pacote_button_pagamento)
        botaoPagamento.setOnClickListener {
            val vaiParaPagamento = Intent(this, PagamentoActivity::class.java)
            vaiParaPagamento.putExtra("local", local.toString())
            vaiParaPagamento.putExtra("imagem", imagem)
            vaiParaPagamento.putExtra("dias", dias.toString())
            vaiParaPagamento.putExtra("preco", preco.toString())
            startActivity(vaiParaPagamento)
        }
    }

    private fun alterarImagem(imagem: String) {
        val ivLocal = findViewById<ImageView>(R.id.resumo_pacote_imageview)
        val imagemPacoteSelecionado = Pacote(imagem = imagem)
        val drawable: Drawable = ResourceUtil().devolverDrawable(this, imagemPacoteSelecionado)
        ivLocal.setImageDrawable(drawable)
    }

    private fun alterarData(dias: String?) {
        val tvData = findViewById<TextView>(R.id.resumo_pacote_tv_data)
        val (dataVolta, dataFormatadaIda, dataFormatadaVolta) = DataUtil().periodoEmTexto(dias)
        tvData.text = "$dataFormatadaIda - $dataFormatadaVolta de ${dataVolta.get(Calendar.YEAR)}"
    }

    private fun alterarPreco(receberExtras: Bundle?) {
        val preco = receberExtras!!.getString("preco")
        val tvPreco = findViewById<TextView>(R.id.resumo_pacote_tv_preco)
        tvPreco.text = preco!!.toBigDecimal().formataBrasileiro()
    }

    private fun alterarDias(receberExtras: Bundle?): String? {
        val dias = receberExtras!!.getString("dias")
        val tvDias = findViewById<TextView>(R.id.resumo_pacote_tv_dias)
        if (dias!!.toInt() > 1) {
            tvDias.text = "$dias dias"
        } else {
            tvDias.text = "$dias dia"
        }
        return dias
    }

    private fun alterarLocal(local: String?) {
        val tvLocal = findViewById<TextView>(R.id.resumo_pacote_tv_local)
        tvLocal.text = local
    }

    private fun recebeExtras(): Pair<Bundle?, String?> {
        val receberExtras = intent.extras
        val local = receberExtras!!.getString("local")
        return Pair(receberExtras, local)
    }
}