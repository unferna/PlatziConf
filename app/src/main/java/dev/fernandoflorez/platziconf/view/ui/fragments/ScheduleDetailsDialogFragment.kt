package dev.fernandoflorez.platziconf.view.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

import dev.fernandoflorez.platziconf.R
import dev.fernandoflorez.platziconf.model.Conference
import kotlinx.android.synthetic.main.fragment_schedule_details_dialog.*
import java.text.SimpleDateFormat
import java.util.*

class ScheduleDetailsDialogFragment : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FullscreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_details_dialog, container, false)
    }

    private fun setToolbar(view: View, conferenceName: String?) {
        toolbarConference.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolbarConference.navigationIcon?.setTint(Color.WHITE)
        toolbarConference.setTitleTextColor(Color.WHITE)
        toolbarConference.setNavigationOnClickListener {
            dismiss()
        }

        toolbarConference.title = conferenceName
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val conference = arguments?.getSerializable("conference") as Conference
        setToolbar(view, conference.title)

        lblScheduleConferenceName.text = conference.title

        val timePattern = "dd/MM/yyyy hh:mm a"
        val df = SimpleDateFormat(timePattern, Locale.getDefault())

        lblDetailConferenceHour.text = df.format(conference.dateTime)
        lblDetailConferenceSpeaker.text = conference.speaker
        lblDetailConferenceTag.text = conference.tag
        txtDetailConferenceDescription.text = conference.description
    }
}
