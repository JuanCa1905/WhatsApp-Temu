package fragments

import android.content.Context
import android.content.Intent // ¡Importación necesaria!
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button // ¡Importación necesaria!
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.miprimeraapp.R
import com.example.miprimeraapp.activities.EditarPerfilActivity // ¡Importación necesaria!

class MiPerfilFragment: Fragment()  {
    private lateinit var  tvNombre: TextView
    private lateinit var tvApellido: TextView
    private lateinit var tvCorreo: TextView
    private lateinit var tvTelefono: TextView
    private lateinit var btnEditar: Button
    private lateinit var sharedPreferences: SharedPreferences


    companion object {
        private const val PREFS_NAME = "UserData"
        private const val KEY_NOMBRE = "nombres"
        private const val KEY_APELLIDO = "apellidos"
        private const val KEY_CORREO = "email"
        private const val KEY_TELEFONO = "telefono"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_perfil, container, false)

        initViews(view)
        setUpSharedPreferences()



        btnEditar.setOnClickListener {

            val intent = Intent(requireActivity(), EditarPerfilActivity::class.java)
            startActivity(intent)
        }

        return view
    }


    override fun onResume() {
        super.onResume()

        if (::sharedPreferences.isInitialized) {
            loadUserData()
        }
    }

    private fun loadUserData() {
        val nombre = sharedPreferences.getString(KEY_NOMBRE, "N/A")
        val apellido = sharedPreferences.getString(KEY_APELLIDO, "N/A")
        val correo = sharedPreferences.getString(KEY_CORREO, "N/A")
        val telefono = sharedPreferences.getString(KEY_TELEFONO, "N/A")

        tvNombre.text = nombre
        tvApellido.text = apellido
        tvCorreo.text = correo
        tvTelefono.text = telefono
    }

    private fun setUpSharedPreferences() {
        sharedPreferences = requireActivity().getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE)
    }

    private fun initViews(view: View) {
        tvNombre = view.findViewById(R.id.valor_nombre)
        tvApellido = view.findViewById(R.id.valor_apellido)
        tvCorreo = view.findViewById(R.id.valor_correo)
        tvTelefono = view.findViewById(R.id.valor_telefono)
        btnEditar = view.findViewById(R.id.btn_editar_perfil) // ENLAZAR EL BOTÓN
    }
}