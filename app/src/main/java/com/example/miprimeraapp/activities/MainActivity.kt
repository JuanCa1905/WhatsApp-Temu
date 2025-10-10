package com.example.miprimeraapp.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.miprimeraapp.R
import com.google.android.material.navigation.NavigationView
import androidx.activity.OnBackPressedCallback


import fragments.MiPerfilFragment



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var menuIcon: ImageView // Declaración del icono del menú

    companion object {
        private const val PREFS_NAME = "UserData"
        private const val FRAGMENT_TAG_HOME = "home"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializarVistas()
        setupSharedPreferences()
        setupNavigationView()

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {

                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                    isEnabled = true
                }
            }
        }


        onBackPressedDispatcher.addCallback(this, callback)

        if (savedInstanceState == null) {
            // Asumo que tienes una función para cargar el fragmento de inicio (ej: HomeFragment)
            // loadFragment(HomeFragment(), "home")
            // navigationView.setCheckedItem(R.id.menu_item_home)
        }
    }



    private fun setupNavigationView(){
        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun setupSharedPreferences() {
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
    }

    private fun inicializarVistas(){
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        menuIcon = findViewById(R.id.menu_icon) // Inicializa el icono del menú


        menuIcon.setOnClickListener {

            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Nota: El header_layout es un LinearLayout, no un Toolbar, por eso la corrección.
        // Si quieres que al hacer clic en todo el header también se abra, usa este código:
        /*
        findViewById<LinearLayout>(R.id.header_layout).setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        */
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean{
        when(item.itemId){
            R.id.menu_item_perfil -> {
                loadFragment(MiPerfilFragment(), "perfil")
            }

            // Agrega más ítems de menú aquí (ej: R.id.menu_item_logout)
            // R.id.menu_item_logout -> cerrarSesion()
        }

        // Cierra el drawer después de la selección
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadFragment(fragment: Fragment, tag: String){
        // Sintaxis corregida para FragmentTransaction
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        // Reemplaza el fragmento existente
        transaction.replace(R.id.fragment_container, fragment, tag)

        // Añade la transacción a la pila de regreso para que el botón "Atrás" funcione
        transaction.addToBackStack(tag)

        transaction.commit()
    }



}