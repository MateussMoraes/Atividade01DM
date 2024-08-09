package com.example.atividade01dm.api.response

import com.example.atividade01dm.api.model.Usuario

data class UsuarioIdResponseBody(
    var _id: String = "",
    var nome: String = "",
    var email: String = "",
    var foto: String? = null
)