package com.ryanair.hosewski.presentation.scene.search

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ryanair.hosewski.R
import com.ryanair.hosewski.databinding.FragmentSearchBinding
import com.ryanair.hosewski.presentation.base.BaseDataBindingFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SearchFragment: BaseDataBindingFragment<FragmentSearchBinding>(R.layout.fragment_search), DatePickerDialog.OnDateSetListener {

    private val viewModel by viewModels<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchViewModel = viewModel
        viewModel.airports.observe(viewLifecycleOwner) {
            binding.originInput.setAdapter(ArrayAdapter(requireContext(),
                android.R.layout.select_dialog_item, it.map { item-> item.toString() }))
            binding.departureInput.setAdapter(ArrayAdapter(requireContext(),
                android.R.layout.select_dialog_item, it.map { item-> item.toString() }))
        }
        viewModel.navigateToList.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToListFragment(
                        it.origin,
                        it.destination, it.date, it.adults, it.teens, it.children
                    )
                )
            }
        }
        binding.dateButton.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        viewModel.setDate("$p1-${String.format("%02d", p2)}-${String.format("%02d", p3)}")
    }

    override fun onPause() {
        super.onPause()
        viewModel.resetNavigation()
    }
}