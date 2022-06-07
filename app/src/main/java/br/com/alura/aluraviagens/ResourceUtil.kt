package br.com.alura.aluraviagens

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import br.com.alura.aluraviagens.model.Pacote

class ResourceUtil {

    fun devolverDrawable(
        context : Context,
        pacote: Pacote
    ): Drawable {
        val resources = context.resources
        val identifier = resources.getIdentifier(pacote.imagem, "drawable", context.packageName)
        return resources.getDrawable(identifier, null)
    }

}