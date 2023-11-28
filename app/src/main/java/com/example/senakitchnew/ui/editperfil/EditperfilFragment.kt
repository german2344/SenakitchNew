package com.example.senakitchnew.ui.editperfil

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.senakitchnew.Connection.ApiConnection
import com.example.senakitchnew.ImportClasses.popupalert
import com.example.senakitchnew.R
import com.example.senakitchnew.bring.UserBring
import com.example.senakitchnew.databinding.ActivityEditarperfilBinding
import com.example.senakitchnew.databinding.ActivityLoginBinding
import com.example.senakitchnew.send.User
import com.example.senakitchnew.send.UserAdmin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EditperfilFragment : Fragment() {

    private val toast = popupalert()
    private var _binding: ActivityEditarperfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityEditarperfilBinding.inflate(inflater, container, false)



        val root: View = binding.root

        val editPerfilModel = ViewModelProvider(this)[EditperfilModel::class.java]

        // Data of user
        editPerfilModel.user.observe(viewLifecycleOwner) { newData ->
            binding.name.text = newData.name
            binding.email.text = newData.email
            binding.descripcion.text=newData.descripcion
            binding.telefono.text=newData.telefono
            binding.ubicacion.text=newData.ubicacion

            context?.let {
                Glide.with(it)
                    .load(newData.profile_photo_url)
                    .placeholder(R.drawable.logofinal2023) // Imagen de carga mientras se carga la imagen
                    .error(R.drawable.logofinal2023) // Imagen de error si no se puede cargar la imagen
                    .into(binding.profileImage)
            }
            binding.btnEditarPerfil.setOnClickListener {
                mostraDialogo()
            }

        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Asociar el ViewModel
        val editPerfilModel = ViewModelProvider(this)[EditperfilModel::class.java]

        // Guardar referencia al contexto
        val context = requireContext()

        // Observar el resultado de la eliminación
        editPerfilModel.deleteUserResult.observe(viewLifecycleOwner) { result ->
            if (result) {
                // La eliminación fue exitosa
                toast.toastSuccess(context, "Senakitch", "Usuario eliminado con éxito")

                // Agregar una animación o cualquier acción que desees antes de cerrar
                // por ejemplo, desvanecer la vista actual
                binding.root.animate().alpha(0f).setDuration(1000).withEndAction {
                    // Usar startActivity con el contexto
                    startActivity(Intent(context, ActivityLoginBinding::class.java))

                    // Finalizar la actividad o fragmento actual después de la animación
                    requireActivity().finish()
                }
            } else {
                // La eliminación falló
                toast.toastSuccess(context, "Senakitch", "Error al eliminar el usuario")
            }
        }

        // Configurar el clic del botón para iniciar la eliminación
        binding.btnDeleteUser.setOnClickListener {
            val userId = UserAdmin.getUserId()
            editPerfilModel.deleteUser(userId.toString())
        }
    }





    private val _updateProfileResult = MutableLiveData<Boolean>()
    val updateProfileResult: LiveData<Boolean> get() = _updateProfileResult

    fun updateProfile(userRequest: UserBring, userId: String) {
        val apiService = ApiConnection.getApiService()

        val userProfileCall: Call<User> = apiService.updateProfile(userRequest, userId)
        userProfileCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    _updateProfileResult.value = true
                    // Mostrar tostada de éxito
                    toast.toastSuccess(context,"Senakitch", "Perfil actualizado exitosamente")
                } else {
                    _updateProfileResult.value = false
                    // Mostrar tostada de error
                    toast.toastSuccess(context,"Senakitch", "Error al actualizar el perfil")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                _updateProfileResult.value = false
                // Mostrar tostada de error

                toast.toastSuccess(context,"Senakitch", "Error al actualizar el perfil")
            }
        })
    }


    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    private fun mostraDialogo() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = LayoutInflater.from(requireContext())
        val dialogoView = inflater.inflate(R.layout.dialog_input, null)
        builder.setView(dialogoView)

        val editText1 = dialogoView.findViewById<EditText>(R.id.name)
        val editText2 = dialogoView.findViewById<EditText>(R.id.descripcion)
        val editText3 = dialogoView.findViewById<EditText>(R.id.telefono)
        val editText4 = dialogoView.findViewById<EditText>(R.id.ubicacion)
        val editText5 = dialogoView.findViewById<EditText>(R.id.email)

        // Obtén el modelo actual del usuario desde el ViewModel
        val editPerfilModel = ViewModelProvider(this)[EditperfilModel::class.java]
        val usuarioActual = editPerfilModel.user.value

        // Si el usuario actual no es nulo, establece los valores en los campos del diálogo
        usuarioActual?.let {
            editText1.setText(it.name)
            editText2.setText(it.descripcion)
            editText3.setText(it.telefono)
            editText4.setText(it.ubicacion)
            editText5.setText(it.email)
        }

        builder.setTitle("SenaKitch")
            .setPositiveButton("Aceptar") { dialog, which ->
                val input1 = editText1.text.toString()
                val input2 = editText2.text.toString()
                val input3 = editText3.text.toString()
                val input4 = editText4.text.toString()
                val input5 = editText5.text.toString()

                // Obtén el ID del usuario de alguna manera (dependiendo de tu implementación)
                val userId = UserAdmin.getUserId().toString() // Convierte a String

                // Llamas a la función para actualizar el perfil con el ID del usuario convertido a String
                updateProfile(UserBring(input1, input2, input3, input4, input5), userId)

                // Después de actualizar el perfil, llama a la función para volver a obtener el perfil actualizado
                editPerfilModel.fetchUserProfile()
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                // Muestra una alerta de error o un Toast indicando que la operación fue cancelada
                toast.toastError(requireContext(), "Senakitch", "Operación cancelada")
            }

        val alertDialog = builder.create()
        alertDialog.show()
    }






}
