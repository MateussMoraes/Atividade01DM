package com.example.atividade01dm.api.response

data class UsuarioEditarResponseBody (
    var message: String,
    var usuarioAtualizado: usuarioAtualizado
)

data class usuarioAtualizado(
    var _id: String = "",
    var nome: String = "",
    var email: String = "",
    var foto: String? = ""
)