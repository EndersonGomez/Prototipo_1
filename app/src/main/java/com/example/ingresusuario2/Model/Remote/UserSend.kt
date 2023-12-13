package com.example.ingresusuario2.Model.Remote

data class UserSend (
    val spreadsheet_id: String,
    val sheet: String,
    val rows: List<List<String>>
)
