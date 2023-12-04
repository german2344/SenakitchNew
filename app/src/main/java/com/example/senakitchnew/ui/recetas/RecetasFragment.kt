package com.example.senakitchnew.ui.recetas

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
import com.example.senakitchnew.databinding.FragmentRecetasBinding
import com.example.senakitchnew.ui.home.HomeViewModel
import com.example.senakitchnew.ui.home.ProductAdapter


class RecetasFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    private var _binding: FragmentRecetasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentRecetasBinding.inflate(inflater, container, false)
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

        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        homeViewModel.contentData.observe(viewLifecycleOwner) { newData ->
           // adapter = ProductAdapter(newData)
            recyclerView.adapter = adapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





}
