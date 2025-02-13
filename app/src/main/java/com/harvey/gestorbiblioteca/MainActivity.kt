package com.harvey.gestorbiblioteca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.harvey.gestorbiblioteca.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // Vinculació de vistes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Configura el contingut de la vista
        // Recupera el NavHostFragment i obté el seu NavController.
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController) // Configura la barra d'accions amb NavController

        // Configura el botó per mostrar la informació del creador.
        binding.btnCreatorInfo.setOnClickListener { showCreatorInfoDialog() }
    }

    private fun showCreatorInfoDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Fet per Harvey John Glover 2025")
            .setMessage("Nom: Harvey John Glover\n" +
                    "Correu: hglover@iesebre.com\n" +
                    "Mòdul: MP08\n" +
                    "Professor: Jordi Vega")
            .setPositiveButton("Tancar", null)
            .show() // Mostra un diàleg amb la informació del creador
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp() || super.onSupportNavigateUp() // Gestiona la navegació cap amunt
    }
}
