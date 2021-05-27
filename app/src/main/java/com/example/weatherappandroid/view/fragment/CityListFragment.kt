package com.example.weatherappandroid.view.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherappandroid.R
import com.example.weatherappandroid.databinding.FragmentRecyclerCityBinding
import com.example.weatherappandroid.model.City
import com.example.weatherappandroid.model.Constants
import com.example.weatherappandroid.view.activity.WeatherInfoActivity
import com.example.weatherappandroid.view.recycler_view.CityListAdapter
import com.example.weatherappandroid.view.recycler_view.MyItemDecoration
import com.example.weatherappandroid.viewModel.CityListViewModel
import com.example.weatherappandroid.viewModel.ClickItemListener
import kotlinx.android.synthetic.main.fragment_recycler_city.*

class CityListFragment : Fragment() {

    private lateinit var binding: FragmentRecyclerCityBinding
    private val viewModel: CityListViewModel by lazy {
        ViewModelProviders.of(this).get(CityListViewModel::class.java)
    }
    private lateinit var recyclerCityAdapter: CityListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO duplicate!①
        recyclerCityAdapter = CityListAdapter(viewModel, viewLifecycleOwner)
        substituteBinding(inflater, container)
        return binding.root
    }

    private fun substituteBinding(inflater: LayoutInflater, container: ViewGroup?) {
        //TODO duplicate!②
        recyclerCityAdapter = CityListAdapter(viewModel, viewLifecycleOwner)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_recycler_city, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.clickLister = createClickListener() // like Delegate in Swift
        edit_text.addTextChangedListener(createTextChangeListener())
        createRecyclerView()

        /**
         * Instead of RecyclerViewAdapter, if ListAdapter is used, this process might be unnecessary,,,,
         * (however, other function may have to be used ??? I don't know a lot,,,,,)
         * Detail: https://qiita.com/chohas/items/acbf3787cd80b5277af7
         *
         * Anyway, ListAdapter sounds nice, so let's use it in the next opportunity!
         * (DiffUtil.Callback will be also used)
         */
        viewModel.getFilteredCityList().observe(viewLifecycleOwner) {
            recyclerCityAdapter.notifyDataSetChanged()
        }
    }

    private fun createRecyclerView() {
        val recyclerView = city_recycler_view
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(MyItemDecoration(3))
        recyclerView.adapter = recyclerCityAdapter
    }

    private fun createClickListener(): ClickItemListener {
        return object : ClickItemListener {
            override fun onClickCityItem(city: City) {
                moveToWeatherInfo(city.id)
            }
        }
    }

    //TODO should it be created in VM? or instead of it, can the textValue in VM  be observed(using observer)? ref: https://speakerdeck.com/yanzm/lifecycle-viewmodel-livedata-falsefu-xi?slide=30
    private fun createTextChangeListener(): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.updateFilter()
                viewModel.updateTestText()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }

    //TODO should have City as an arg
    private fun moveToWeatherInfo(cityId: String) {
        val intent = Intent(activity, WeatherInfoActivity::class.java)
        intent.putExtra(Constants.CITY_ID, cityId)
        activity?.startActivity(intent)
    }
}

