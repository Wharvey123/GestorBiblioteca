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
import com.harvey.gestorbiblioteca.databinding.FragmentDetailBinding
import com.harvey.gestorbiblioteca.viewmodel.LibraryViewModel

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!! // Vinculació de vistes
    private val viewModel: LibraryViewModel by viewModels() // Instància del ViewModel
    private val args: DetailFragmentArgs by navArgs() // Arguments de navegació

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        // Infla el layout per a aquest fragment
        return binding.root // Retorna la vista arrel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemId = args.itemId // Obté l'ID de l'element des dels arguments

        viewModel.getItemById(itemId).observe(viewLifecycleOwner) { item ->
            item?.let {
                binding.textViewTitle.text = it.title
                binding.textViewAuthor.text = it.author
                binding.textViewDescription.text = it.description
                binding.textViewYear.text = it.year.toString()
                binding.textViewGenre.text = it.genre
            }
        }

        binding.buttonDelete.setOnClickListener {
            viewModel.getItemById(itemId).observe(viewLifecycleOwner) { item ->
                item?.let {
                    viewModel.delete(it)
                    Toast.makeText(requireContext(), "Llibre eliminat", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp() // Navega cap amunt (retorna a l'anterior)
                }
            }
        }

        binding.buttonEdit.setOnClickListener {
            // Navega cap a EditFragment passant l'itemId per editar-lo
            val action = DetailFragmentDirections.actionDetailFragmentToEditFragment(itemId = itemId)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Netegem la vinculació quan es destrueix la vista
    }
}
