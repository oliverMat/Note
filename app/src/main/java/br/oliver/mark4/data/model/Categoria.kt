package br.oliver.mark4.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Categoria(@PrimaryKey val nome : String)
