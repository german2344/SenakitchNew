import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.senakitchnew.R
import com.example.senakitchnew.ui.Crud.ItemAdapter
import com.example.senakitchnew.ui.Crud.ListViewModel
import com.example.senakitchnew.databinding.ItemContentBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.senakitchnew.send.ProductSend

class ActivityFragment : Fragment() {

    private val _allItems = MutableLiveData<List<ProductSend>>()
    val allItems: LiveData<List<ProductSend>> get() = _allItems
    private var _binding: ItemContentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: ItemAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.your_fragment_layout, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        adapter = ItemAdapter(emptyList()) // Pass an initial empty list

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        _allItems.observe(viewLifecycleOwner, Observer { items ->
            items?.let { adapter.setItems(it) }
        })


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
