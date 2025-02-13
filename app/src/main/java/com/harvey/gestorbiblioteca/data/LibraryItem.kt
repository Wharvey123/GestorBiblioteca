package com.harvey.gestorbiblioteca.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "library_items") // Defineix una entitat de la base de dades amb el nom de taula "library_items"
data class LibraryItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // Clau primària autogenerada
    val title: String,       // Títol de l'article de la biblioteca
    val description: String, // Descripció de l'article
    val author: String,      // Autor de l'article
    val year: Int,           // Any de publicació
    val genre: String        // Gènere de l'article
)
