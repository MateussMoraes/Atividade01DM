package com.example.atividade01dm.api.request

data class UsuarioCadastrarRequestBody(
    var nome: String = "",
    var email: String = "",
    var senha: String = ""
)
