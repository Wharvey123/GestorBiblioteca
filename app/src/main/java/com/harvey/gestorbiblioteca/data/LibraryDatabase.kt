package com.harvey.gestorbiblioteca.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LibraryItem::class], version = 1, exportSchema = false)
// Defineix la base de dades Room amb l'entitat LibraryItem
abstract class LibraryDatabase : RoomDatabase() {
    abstract fun libraryDao(): LibraryDao // Declaració de la funció abstracta per obtenir l'objecte DAO

    companion object {
        @Volatile
        private var INSTANCE: LibraryDatabase? = null // Instància única de la base de dades

        fun getDatabase(context: Context): LibraryDatabase {
            return INSTANCE ?: synchronized(this) { // Obté la instància de la base de dades de manera sincronitzada
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LibraryDatabase::class.java,
                    "library_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
