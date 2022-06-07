package br.com.alura.aluraviagens

import java.text.SimpleDateFormat
import java.util.*

class DataUtil {

    private val DIA_E_MES = "dd/MM"

    fun periodoEmTexto(dias: String?): Triple<Calendar, String, String> {
        val dataIda = Calendar.getInstance()
        val dataVolta = Calendar.getInstance()
        dataVolta.add(Calendar.DATE, dias!!.toInt())
        val formatoBrasileiro = SimpleDateFormat(DIA_E_MES)
        val dataFormatadaIda = formatoBrasileiro.format(dataIda.time)
        val dataFormatadaVolta = formatoBrasileiro.format(dataVolta.time)
        return Triple(dataVolta, dataFormatadaIda, dataFormatadaVolta)
    }
}