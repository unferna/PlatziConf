package dev.fernandoflorez.platziconf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import dev.fernandoflorez.platziconf.R
import dev.fernandoflorez.platziconf.model.Conference
import dev.fernandoflorez.platziconf.view.adapters.ScheduleAdapter
import dev.fernandoflorez.platziconf.view.adapters.ScheduleListener
import dev.fernandoflorez.platziconf.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*

class ScheduleFragment: Fragment(), ScheduleListener {
    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        viewModel.refresh()

        scheduleAdapter = ScheduleAdapter(this)
        rvSchedule.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = scheduleAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.scheduleList.observe(viewLifecycleOwner, Observer {
            scheduleAdapter.updateData(it)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                rvBaseSchedules.visibility = View.INVISIBLE
            }
        })
    }

    override fun onConferenceSelected(conference: Conference, position: Int) {
        val bundle = bundleOf("conference" to conference)
        findNavController().navigate(R.id.scheduleDetailsDialogFragment, bundle)
    }
}
