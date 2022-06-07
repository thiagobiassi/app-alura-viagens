package br.com.alura.aluraviagens.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.aluraviagens.R
import br.com.alura.aluraviagens.model.Pacote
import br.com.alura.aluraviagens.ui.adapter.ListaPacotesAdapter
import java.math.BigDecimal

class ListaPacotesActivity : AppCompatActivity() {
    private val TITULO_APPBAR = "Pacotes"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pacotes)
        title = TITULO_APPBAR

        val (listaDePacotes, listaPacotes: List<Pacote>) = criarAdapter()

        enviarParaActivityResumo(listaDePacotes, listaPacotes)
    }

    private fun enviarParaActivityResumo(
        listaDePacotes: ListView,
        listaPacotes: List<Pacote>
    ) {
        listaDePacotes.setOnItemClickListener { parent, view, position, id ->
            val enviarParaResumoDoPacote = Intent(this, ResumoPacoteActivity::class.java)
            val pacoteSelecionado = listaPacotes[position]
            enviarParaResumoDoPacote.putExtra("local", pacoteSelecionado.local)
            enviarParaResumoDoPacote.putExtra("dias", pacoteSelecionado.dias.toString())
            enviarParaResumoDoPacote.putExtra("imagem", pacoteSelecionado.imagem)
            enviarParaResumoDoPacote.putExtra("preco", pacoteSelecionado.preco.toString())

            Pacote(
                pacoteSelecionado.local,
                pacoteSelecionado.imagem,
                pacoteSelecionado.dias,
                pacoteSelecionado.preco
            )

            startActivity(enviarParaResumoDoPacote)
        }
    }

    private fun criarAdapter(): Pair<ListView, List<Pacote>> {
        val listaDePacotes = findViewById<ListView>(R.id.lista_pacotes_listview)
        val listaPacotes: List<Pacote> =
            listOf(
                Pacote("São Paulo", "sao_paulo_sp", 2, BigDecimal(299.99)),
                Pacote("Belo Horizonte", "belo_horizonte_mg", 3, BigDecimal(239.99)),
                Pacote("Foz do Iguaçu", "foz_do_iguacu_pr", 3, BigDecimal(449.99)),
                Pacote("Recife", "recife_pe", 1, BigDecimal(499.99)),
                Pacote("Rio de Janeiro", "rio_de_janeiro_rj", 4, BigDecimal(229.99)),
                Pacote("Salvador", "salvador_ba", 1, BigDecimal(399.99))
            )

        listaDePacotes.adapter = ListaPacotesAdapter(listaPacotes, this)
        return Pair(listaDePacotes, listaPacotes)
    }
}