package com.david.squid_mail.model

enum class FolderType(val displayName: String){
    INBOX("Caixa de entrada"),
    SENT("Enviados"),
    ARCHIVED("Arquivados"),
    FAVORITES("Favoritos"),
    SPAM("Spam"),
    DRAFTS("Rascunhos"),
    TRASH("Lixeira"),
    OTHER("Outros")
}