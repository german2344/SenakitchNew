package com.example.senakitchnew.ui.comentarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.senakitchnew.R
import com.example.senakitchnew.databinding.ActivityEditarperfilBinding
import com.example.senakitchnew.databinding.FragmentComentBinding
import com.example.senakitchnew.ui.editperfil.EditperfilModel
import com.example.senakitchnew.ui.home.HomeViewModel


class ComentariosFragment : Fragment(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ComentAdapter

    private var _binding: FragmentComentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val homeViewModel =
            ViewModelProvider(this)[ComentariosModel::class.java]

        _binding = FragmentComentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val homeViewModel = ViewModelProvider(this)[ComentariosModel::class.java]

        homeViewModel.contentData.observe(viewLifecycleOwner) { newData ->
            adapter = ComentAdapter(newData)
            recyclerView.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}