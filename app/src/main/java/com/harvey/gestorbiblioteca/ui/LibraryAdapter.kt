package com.harvey.gestorbiblioteca.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harvey.gestorbiblioteca.data.LibraryItem
import com.harvey.gestorbiblioteca.databinding.ItemLibraryBinding

class LibraryAdapter(private val onItemClick: (LibraryItem) -> Unit) :
    RecyclerView.Adapter<LibraryAdapter.LibraryViewHolder>() {

    private var items: List<LibraryItem> = emptyList() // Llista d'elements de la biblioteca

    fun submitList(newItems: List<LibraryItem>) {
        items = newItems
        notifyDataSetChanged() // Notifica que la llista ha canviat
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val binding = ItemLibraryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibraryViewHolder(binding) // Crea un nou ViewHolder
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        holder.bind(items[position]) // Lliga l'element a la vista
    }

    override fun getItemCount(): Int = items.size // Retorna el nombre d'elements

    inner class LibraryViewHolder(private val binding: ItemLibraryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LibraryItem) {
            binding.textViewTitle.text = item.title
            binding.textViewAuthor.text = item.author
            itemView.setOnClickListener { onItemClick(item) } // Gestiona el clic de l'element
        }
    }
}
