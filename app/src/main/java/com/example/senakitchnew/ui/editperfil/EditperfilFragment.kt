package com.example.senakitchnew.ui.editperfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.senakitchnew.R
import com.example.senakitchnew.databinding.ActivityEditarperfilBinding
class EditperfilFragment : Fragment() {

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

            context?.let {
                Glide.with(it)
                    .load(newData.profile_photo_url)
                    .placeholder(R.drawable.logo) // Imagen de carga mientras se carga la imagen
                    .error(R.drawable.logo) // Imagen de error si no se puede cargar la imagen
                    .into(binding.profileImage)
            }

        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

