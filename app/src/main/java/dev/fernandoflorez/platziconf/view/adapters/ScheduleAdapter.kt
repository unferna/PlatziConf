package dev.fernandoflorez.platziconf.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.fernandoflorez.platziconf.R
import dev.fernandoflorez.platziconf.model.Conference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

interface ScheduleListener {
    fun onConferenceSelected(conference: Conference, position: Int)
}

open class ScheduleAdapter(private val listener: ScheduleListener): RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    var conferenceList = ArrayList<Conference>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_schedule,
        parent,
        false
    ))

    override fun getItemCount() = conferenceList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val conference = conferenceList[position]
        holder.lblScheduleConferenceName.text = conference.title
        holder.lblScheduleConferenceSpeaker.text = conference.speaker
        holder.lblTopic.text = conference.tag

        val time = SimpleDateFormat("HH:mm", Locale.getDefault())
        val AMPM = SimpleDateFormat("a",  Locale.getDefault())

        val hourFormat = time.format(conference.dateTime)
        val AMPMFormat = AMPM.format(conference.dateTime).toUpperCase(Locale.getDefault())

        holder.lblItemScheduleHour.text = hourFormat
        holder.lblItemScheduleHourAMPM.text = AMPMFormat

        holder.itemView.setOnClickListener {
            listener.onConferenceSelected(conference, position)
        }
    }

    fun updateData(conferences: List<Conference>) {
        conferenceList.clear()
        conferenceList.addAll(conferences)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val lblScheduleConferenceName: TextView = itemView.findViewById(R.id.lblScheduleConferenceName)
        val lblItemScheduleHour: TextView = itemView.findViewById(R.id.lblItemScheduleHour)
        val lblItemScheduleHourAMPM: TextView = itemView.findViewById(R.id.lblItemScheduleHourAMPM)
        val lblScheduleConferenceSpeaker: TextView = itemView.findViewById(R.id.lblScheduleConferenceSpeaker)
        val lblTopic: TextView = itemView.findViewById(R.id.lblTopic)
    }
}