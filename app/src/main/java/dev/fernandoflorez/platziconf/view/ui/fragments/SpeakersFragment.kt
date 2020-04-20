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
import androidx.recyclerview.widget.GridLayoutManager

import dev.fernandoflorez.platziconf.R
import dev.fernandoflorez.platziconf.model.Speaker
import dev.fernandoflorez.platziconf.view.adapters.SpeakerAdapter
import dev.fernandoflorez.platziconf.view.adapters.SpeakerListener
import dev.fernandoflorez.platziconf.viewmodel.SpeakersViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*

class SpeakersFragment: Fragment(), SpeakerListener {
    private lateinit var speakerAdapter: SpeakerAdapter
    private lateinit var viewModel: SpeakersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SpeakersViewModel::class.java)
        viewModel.refresh()

        speakerAdapter = SpeakerAdapter(this)
        rvSpeakers.apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = speakerAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.speakersList.observe(viewLifecycleOwner, Observer {
            speakerAdapter.updateData(it)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                rlBaseSpeakers.visibility = View.INVISIBLE
            }
        })
    }

    override fun onSpeakerSelected(speaker: Speaker, position: Int) {
        val bundle = bundleOf("speaker" to speaker)
        findNavController().navigate(R.id.speakerDetailsDialogFragment, bundle)
    }
}
