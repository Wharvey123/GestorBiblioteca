package com.harvey.gestorbiblioteca.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.harvey.gestorbiblioteca.R
import com.harvey.gestorbiblioteca.databinding.FragmentMainBinding
import com.harvey.gestorbiblioteca.viewmodel.LibraryViewModel

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding // Vinculació de vistes
    private val viewModel: LibraryViewModel by viewModels() // Instància del ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        // Infla el layout per a aquest fragment
        // Configura l'adaptador de la RecyclerView
        val adapter = LibraryAdapter { item ->
            val bundle = Bundle().apply {
                putInt("itemId", item.id)
            }
            findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        // Navega cap a DetailFragment passant l'itemId
        }
        binding.recyclerView.adapter = adapter

        // Configura el LayoutManager perquè la RecyclerView pugui disposar els seus elements
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observa els canvis i actualitza la llista de l'adaptador
        viewModel.allItems.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }

        // Navega a la pantalla d'afegir/editar quan es fa clic al FAB
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_editFragment)
        }

        return binding.root // Retorna la vista arrel
    }
}
