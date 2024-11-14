package br.com.analuisa936_juliocesar94543.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.analuisa936_juliocesar94543.R
import br.com.analuisa936_juliocesar94543.model.DicaModel

class DicasAdapter(private val onDicaRemoved: (DicaModel) -> Unit) : RecyclerView.Adapter<DicasAdapter.DicaViewHolder>() {

    private var dicas = listOf<DicaModel>()

    inner class DicaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.titulo)
        val desc: TextView = view.findViewById(R.id.descricao)

        fun bind(dica: DicaModel) {
            titulo.text = dica.titulo
            desc.text = dica.descricao
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DicaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dica_layout, parent, false)
        return DicaViewHolder(view)
    }

    override fun getItemCount(): Int = dicas.size

    override fun onBindViewHolder(holder: DicaViewHolder, position: Int) {
        val dica = dicas[position]
        holder.bind(dica)


        holder.itemView.setOnClickListener {
            onDicaRemoved(dica)
        }
    }

    fun updateDicas(newDicas: List<DicaModel>) {
        dicas = newDicas
        notifyDataSetChanged()
    }
}