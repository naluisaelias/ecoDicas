package br.com.analuisa936_juliocesar94543

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import br.com.analuisa936_juliocesar94543.viewModel.DicasAdapter
import br.com.analuisa936_juliocesar94543.viewModel.DicasViewModel
import br.com.analuisa936_juliocesar94543.viewModel.DicasViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: DicasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Eco Dicas"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val dicasAdapter = DicasAdapter { dica -> viewModel.removeDica(dica) }
        recyclerView.adapter = dicasAdapter

        val tituloInput = findViewById<EditText>(R.id.tituloInput)
        val descInput = findViewById<EditText>(R.id.descInput)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val titulo = tituloInput.text.toString()
            val descricao = descInput.text.toString()

            if (titulo.isEmpty()) {
                tituloInput.error = "Preencha o título"
                return@setOnClickListener
            }

            if (descricao.isEmpty()) {
                descInput.error = "Preencha a descrição"
                return@setOnClickListener
            }

            viewModel.addDica(titulo, descricao)

            tituloInput.text.clear()
            descInput.text.clear()
        }

        val viewModelFactory = DicasViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DicasViewModel::class.java)

        viewModel.dicasLiveData.observe(this) { dicas ->
            dicasAdapter.updateDicas(dicas)
        }
    }
}
