package com.example.orgs.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.orgs.dao.ProdutosDao
import com.example.orgs.databinding.ListaProdutosActivityBinding
import com.example.orgs.ui.recyclerview.adapter.ListaProdutosAdapter

class ListaProdutosActivity : AppCompatActivity() {
    private lateinit var binder: ListaProdutosActivityBinding
    private val dao = ProdutosDao()
    private val listaProdutosAdapter = ListaProdutosAdapter(this, dao.buscaTodos())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ListaProdutosActivityBinding.inflate(layoutInflater)
        setContentView(binder.root)
        configuraRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        listaProdutosAdapter.atualiza(dao.buscaTodos())
    }

    private fun configuraFab() {
        val fab = binder.listaProdutosFloatingActionButton
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val formProdutoIntent = Intent(this, FormProdutoActivity::class.java)
        startActivity(formProdutoIntent)
    }

    private fun configuraRecyclerView() {
        val productRecView = binder.listaProdutosProductRecyclerView
        productRecView.adapter = listaProdutosAdapter
    }
}