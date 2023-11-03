package com.example.senakitchnew.ui.editar2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.senakitchnew.databinding.ActivityPruebasBinding
import com.example.senakitchnew.databinding.FragmentEditarPerfilBinding
import com.example.senakitchnew.databinding.FragmentGalleryBinding
import com.example.senakitchnew.editar_perfil
import com.example.senakitchnew.ui.gallery.GalleryViewModel

class EditperfilFragment2 : Fragment() {

    private var _binding: ActivityPruebasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = ActivityPruebasBinding.inflate(inflater, container, false)
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
