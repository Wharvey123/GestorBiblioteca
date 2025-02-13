package com.harvey.gestorbiblioteca.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.harvey.gestorbiblioteca.data.LibraryItem
import com.harvey.gestorbiblioteca.databinding.FragmentEditBinding
import com.harvey.gestorbiblioteca.viewmodel.LibraryViewModel

class EditFragment : Fragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!! // Vinculació de vistes
    private val viewModel: LibraryViewModel by viewModels() // Instància del ViewModel
    private val args: EditFragmentArgs by navArgs() // s'utilitzen els safe args
    private var currentItem: LibraryItem? = null // Element actual en edició

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        // Infla el layout per a aquest fragment
        return binding.root // Retorna la vista arrel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemId = args.itemId // Obté l'ID de l'element des dels arguments
        if (itemId != -1) {
            // Mode edició: carregar l'element existent
            viewModel.getItemById(itemId).observe(viewLifecycleOwner) { item ->
                item?.let {
                    currentItem = it
                    binding.editTextTitle.setText(it.title)
                    binding.editTextAuthor.setText(it.author)
                    binding.editTextDescription.setText(it.description)
                    binding.editTextYear.setText(it.year.toString())
                    binding.editTextGenre.setText(it.genre)
                }
            }
        }

        binding.buttonSave.setOnClickListener {
            val title = binding.editTextTitle.text.toString().trim()
            val author = binding.editTextAuthor.text.toString().trim()
            val description = binding.editTextDescription.text.toString().trim()
            val year = binding.editTextYear.text.toString().toIntOrNull() ?: 0
            val genre = binding.editTextGenre.text.toString().trim()

            // Validació bàsica
            if (title.isEmpty() || author.isEmpty()) {
                Toast.makeText(requireContext(), "El títol i l'autor són obligatoris", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (itemId != -1 && currentItem != null) {
                // Actualització de l'element existent
                val updatedItem = currentItem!!.copy(
                    title = title,
                    author = author,
                    description = description,
                    year = year,
                    genre = genre
                )
                viewModel.update(updatedItem)
                Toast.makeText(requireContext(), "Llibre actualitzat", Toast.LENGTH_SHORT).show()
            } else {
                // Inserció d'un nou element
                val newItem = LibraryItem(
                    title = title,
                    author = author,
                    description = description,
                    year = year,
                    genre = genre
                )
                viewModel.insert(newItem)
                Toast.makeText(requireContext(), "Llibre desat", Toast.LENGTH_SHORT).show()
            }

            findNavController().popBackStack() // Torna enrere a la pila de navegació
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Netegem la vinculació quan es destrueix la vista
    }
}
