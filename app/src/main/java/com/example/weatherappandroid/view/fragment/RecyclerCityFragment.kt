package com.example.weatherappandroid.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherappandroid.R
import com.example.weatherappandroid.databinding.FragmentRecyclerCityBinding
import com.example.weatherappandroid.view.recycler_view.CityListAdapter
import com.example.weatherappandroid.view.recycler_view.MyItemDecoration
import com.example.weatherappandroid.viewModel.CityListViewModel
import kotlinx.android.synthetic.main.fragment_recycler_city.*

class RecyclerCityFragment : Fragment() {

    private lateinit var binding: FragmentRecyclerCityBinding
    private val viewModel: CityListViewModel by lazy {
        ViewModelProviders.of(this).get(CityListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_recycler_city, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.fetchCityData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.updateFilter()
                viewModel.updateTestText()
                city_recycler_view.adapter?.notifyDataSetChanged()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        createRecyclerView()
    }

    private fun createRecyclerView() {
        val recyclerView = city_recycler_view
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(MyItemDecoration(3))
        recyclerView.adapter = CityListAdapter(viewModel, viewLifecycleOwner)
    }

//    companion object {
//
//
//        fun newInstance(param1: String, param2: String) =
//            RecyclerCityFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}

