package com.harvey.gestorbiblioteca.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.harvey.gestorbiblioteca.data.LibraryDatabase
import com.harvey.gestorbiblioteca.data.LibraryItem
import kotlinx.coroutines.launch

class LibraryViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = LibraryDatabase.getDatabase(application).libraryDao() // Obté l'objecte DAO de la base de dades
    val allItems: LiveData<List<LibraryItem>> = dao.getAllItems() // LiveData amb tots els elements de la biblioteca

    fun getItemById(itemId: Int): LiveData<LibraryItem> {
        return dao.getItemById(itemId) // Obté un element per ID en temps real
    }

    fun insert(item: LibraryItem) = viewModelScope.launch {
        dao.insertItem(item) // Insereix un nou element
    }

    fun update(item: LibraryItem) = viewModelScope.launch {
        dao.updateItem(item) // Actualitza un element existent
    }

    fun delete(item: LibraryItem) = viewModelScope.launch {
        dao.deleteItem(item) // Elimina un element existent
    }
}
