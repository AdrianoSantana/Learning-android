package com.example.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import coil.load
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDao
import com.example.orgs.databinding.ActivityFormProdutoBinding
import com.example.orgs.databinding.FormularioImageBinding
import com.example.orgs.model.Produto
import java.math.BigDecimal

class FormProdutoActivity : AppCompatActivity(R.layout.activity_form_produto) {
    private lateinit var binder: ActivityFormProdutoBinding
    private val produtosDao = ProdutosDao()
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binder = ActivityFormProdutoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binder.root)
        configurarBotaoSalvar()
        binder.activityFormProdutoImage.setOnClickListener {
            val formularioImageBinding = FormularioImageBinding.inflate(layoutInflater)
            val carregarButton = formularioImageBinding.formularioImageBotaoCarregar
            carregarButton.setOnClickListener {
                val temporaryUrl = formularioImageBinding.formularioImagemTextinputUrl.text.toString()
                formularioImageBinding.formularioImageImageview.load(temporaryUrl)
            }
            AlertDialog.Builder(this)
                .setView(formularioImageBinding.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    this.url = formularioImageBinding.formularioImagemTextinputUrl.text.toString()
                    binder.activityFormProdutoImage.load(this.url)
                }
                .setNegativeButton("Cancelar") { _, _ ->
                    {

                    }
                }
                .show()
        }
    }

    private fun configurarBotaoSalvar() {
        val botaoSalvar = binder.activityFormSalvarBtn
        botaoSalvar.setOnClickListener {
            produtosDao.adiciona(criarNovoProduto())
            finish()
        }
    }

    private fun criarNovoProduto(): Produto {
        val campoNome = binder.activityFormNome
        val campoDescricao = binder.activityFormDescricao
        val campoValor = binder.activityFormValor
        val nomeProduto = campoNome.text.toString()
        val descricaoProduto = campoDescricao.text.toString()
        val valorProduto = campoValor.text.toString()

        val valor = if (valorProduto.isBlank()) BigDecimal.ZERO else BigDecimal(valorProduto)
        return Produto(nomeProduto, descricaoProduto, valor, this.url)
    }

}