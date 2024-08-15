package com.example.atividade01dm.api.response

data class UsuarioCadastrarResponseBody(
    var message: String,
    var novoUsuario: novoUsuario
)

data class novoUsuario(
    var _id: String = "",
    var email: String = "",
    var nome: String = "",
    var foto: String? = ""
)