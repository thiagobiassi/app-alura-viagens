package br.com.alura.aluraviagens.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import br.com.alura.aluraviagens.MoedaUtil
import br.com.alura.aluraviagens.R
import br.com.alura.aluraviagens.model.Pacote
import br.com.alura.aluraviagens.DiasUtil
import br.com.alura.aluraviagens.ResourceUtil

class ListaPacotesAdapter(
    private val pacotes: List<Pacote>,
    private val context: Context
) : BaseAdapter() {

    override fun getCount(): Int {
        return pacotes.size
    }

    override fun getItem(position: Int): Pacote {
        return pacotes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(context).inflate(R.layout.item_pacote, parent, false)

        val pacote = pacotes[position]

        alterarLocal(viewCriada, pacote)
        alterarImagem(viewCriada, pacote, ResourceUtil())
        alterarDia(viewCriada, pacote, DiasUtil())
        alterarPreco(viewCriada, pacote, MoedaUtil())

        return viewCriada
    }

    private fun alterarPreco(
        viewCriada: View,
        pacote: Pacote,
        moedaUtil: MoedaUtil
    ) {
        val preco = viewCriada.findViewById<TextView>(R.id.item_pacote_preco)
        preco.text = moedaUtil.formatarParaBrasileiro(pacote)

    }

    private fun alterarDia(
        viewCriada: View,
        pacote: Pacote,
        diasUtil: DiasUtil
    ) {
        val dias = viewCriada.findViewById<TextView>(R.id.item_pacote_dias)
        dias.text = diasUtil.formataDiasEmTexto(pacote)
    }

    private fun alterarImagem(
        viewCriada: View,
        pacote: Pacote,
        resourceUtil: ResourceUtil
    ) {
        val imagem = viewCriada.findViewById<ImageView>(R.id.item_pacote_imageView)
        val drawable = resourceUtil.devolverDrawable(context, pacote)
        imagem.setImageDrawable(drawable)
    }

    private fun alterarLocal(
        viewCriada: View,
        pacote: Pacote
    ) {
        val local = viewCriada.findViewById<TextView>(R.id.item_pacote_local)
        local.text = pacote.local
    }
}