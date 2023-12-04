package com.example.senakitchnew.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.senakitchnew.Crud.CrudPostActivity
import com.example.senakitchnew.Crud.CrudUpdateActivity

import com.example.senakitchnew.Crud.MyCrudsActivity.MyCrudsActivity
import com.example.senakitchnew.R
import com.example.senakitchnew.databinding.FragmentPlatosBinding
import com.example.senakitchnew.send.ProductSend

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    private var _binding: FragmentPlatosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentPlatosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
        //    textView.text = it
        }

        val buttonCrear: Button = binding.Crear
        buttonCrear.setOnClickListener {
            // Crear un Intent para el nuevo Activity
            val intent = Intent(requireContext(), CrudPostActivity::class.java)

            // Iniciar la nueva Activity
            startActivity(intent)
        }

        return root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val platosViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        // Observar cambios en los datos y actualizar el adaptador
        platosViewModel.contentData.observe(viewLifecycleOwner) { tipoAsignaturaResponse ->
            tipoAsignaturaResponse?.let {
                // Inicializar el adaptador con los datos del ViewModel
                adapter = ProductAdapter(it) { seleccionarProduct ->
                    Log.e("SHIT", "${seleccionarProduct.id}")
                    onItemSelected(seleccionarProduct)
                }

                // Configurar el adaptador en el RecyclerView
                recyclerView.adapter = adapter
            }
        }
    }

    fun onItemSelected(tipoproductoResponse: ProductSend) {
        Log.d("API_RESPONSEGGG", "Selected Product ID: ${tipoproductoResponse.id}")
        // voy a ver lo que esta pasando esto aqui
        val intent = Intent(requireContext(), CrudUpdateActivity::class.java)
        intent.putExtra("TIPOASIGNATURA_ID", tipoproductoResponse.id)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
