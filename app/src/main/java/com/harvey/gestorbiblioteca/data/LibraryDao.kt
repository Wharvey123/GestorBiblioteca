package com.harvey.gestorbiblioteca.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LibraryDao {
    @Query("SELECT * FROM library_items ORDER BY title ASC")
    fun getAllItems(): LiveData<List<LibraryItem>> // Obtenir tots els elements ordenats per títol en ordre ascendent

    @Query("SELECT * FROM library_items WHERE id = :itemId")
    fun getItemById(itemId: Int): LiveData<LibraryItem> // Obtenir un element per ID en temps real

    @Query("SELECT * FROM library_items WHERE id = :itemId")
    suspend fun getItemByIdSync(itemId: Int): LibraryItem? // Obtenir un element per ID de manera síncrona

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: LibraryItem) // Inserir un element, reemplaçant-lo si ja existeix

    @Update
    suspend fun updateItem(item: LibraryItem) // Actualitzar un element existent

    @Delete
    suspend fun deleteItem(item: LibraryItem) // Eliminar un element existent
}
