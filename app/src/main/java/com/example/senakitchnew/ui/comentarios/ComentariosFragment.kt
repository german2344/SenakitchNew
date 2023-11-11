package com.example.senakitchnew.ui.comentarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.senakitchnew.databinding.ActivityComentariosBinding
import com.example.senakitchnew.databinding.ActivityEditarperfilBinding
import com.example.senakitchnew.ui.editperfil.EditperfilModel

class ComentariosFragment : Fragment(){
    private var _binding: ActivityComentariosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val EditperfilModel =
            ViewModelProvider(this).get(EditperfilModel::class.java)

        _binding = ActivityComentariosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /* val textView: TextView = binding.textGallery
         galleryViewModel.text.observe(viewLifecycleOwner) {
             textView.text = it
         }*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}