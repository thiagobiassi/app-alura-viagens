package br.com.alura.aluraviagens.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.aluraviagens.R
import br.com.alura.aluraviagens.extension.formataBrasileiro

class PagamentoActivity : AppCompatActivity() {
    private val TITLE = "Pagamento"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagamento)
        title = TITLE
        val receberExtras = intent.extras
        val (local, imagem, dias) = receberVariaveisPacote(receberExtras)
        val preco = alterarValorPacote(receberExtras)
        botaoFinalizarCompra(local, imagem, dias, preco)
    }

    private fun receberVariaveisPacote(receberExtras: Bundle?): Triple<String?, String?, String?> {
        val local = receberExtras!!.getString("local")
        val imagem = receberExtras!!.getString("imagem")
        val dias = receberExtras!!.getString("dias")
        return Triple(local, imagem, dias)
    }

    private fun botaoFinalizarCompra(
        local: String?,
        imagem: String?,
        dias: String?,
        preco: String?
    ) {
        val botaoFinalizarCompra = findViewById<Button>(R.id.pagamento_botao_finaliza_compra)
        botaoFinalizarCompra.setOnClickListener {
            val vaiParaResumoCompra = Intent(this, ResumoCompraActivity::class.java)
            vaiParaResumoCompra.putExtra("local", local.toString())
            vaiParaResumoCompra.putExtra("imagem", imagem)
            vaiParaResumoCompra.putExtra("dias", dias.toString())
            vaiParaResumoCompra.putExtra("preco", preco.toString())
            startActivity(vaiParaResumoCompra)
        }
    }

    private fun alterarValorPacote(receberExtras: Bundle?): String? {
        val precoDoPacote = findViewById<TextView>(R.id.pagamento_tv_preco_pacote)
        val preco = receberExtras!!.getString("preco")
        precoDoPacote.text = preco!!.toBigDecimal().formataBrasileiro()
        return preco
    }
}