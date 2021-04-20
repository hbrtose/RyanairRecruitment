package com.ryanair.hosewski.presentation.scene.list

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryanair.hosewski.R
import com.ryanair.hosewski.databinding.FragmentListBinding
import com.ryanair.hosewski.presentation.base.BaseDataBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment: BaseDataBindingFragment<FragmentListBinding>(R.layout.fragment_list) {

    private val args: ListFragmentArgs by navArgs()
    private val viewModel by viewModels<ListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.let {
                it.title = "${args.origin} - ${args.destination}"
        }
        binding.listViewModel = viewModel
        val adapter = ListAdapter(viewModel.list, viewLifecycleOwner, binding.price.progress) {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailsFragment(it.origin, it.destination, it.infantsLeft, it.fare, it.discount))
        }
        binding.flightList.adapter = adapter
        binding.flightList.layoutManager = LinearLayoutManager(requireContext())
        binding.price.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                adapter.updatePrice(p1)
                binding.priceLabel.text = "Price: $p1"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        binding.priceLabel.text = "Price: ${binding.price.progress}"
        viewModel.search(args)

    }
}