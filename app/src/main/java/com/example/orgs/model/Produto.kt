package com.example.orgs.model

import java.math.BigDecimal

data class Produto(val nome: String, val descricao: String, val price: BigDecimal, val url: String? = null)
