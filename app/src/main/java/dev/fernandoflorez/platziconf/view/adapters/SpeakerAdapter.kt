package dev.fernandoflorez.platziconf.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.fernandoflorez.platziconf.R
import dev.fernandoflorez.platziconf.model.Speaker

interface SpeakerListener {
    fun onSpeakerSelected(speaker: Speaker, position: Int)
}

class SpeakerAdapter(private val listener: SpeakerListener): RecyclerView.Adapter<SpeakerAdapter.ViewHolder>() {
    var speakerList = ArrayList<Speaker>()

    fun updateData(speakers: List<Speaker>) {
        speakerList.clear()
        speakerList.addAll(speakers)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_speaker,
        parent,
        false
    ))

    override fun getItemCount() = speakerList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val speaker = speakerList[position]

        Glide.with(holder.itemView.context)
            .load(speaker.imageUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.imgSpeakerPhoto)

        holder.lblSpeakerName.text = speaker.name
        holder.lblSpeakerWork.text = speaker.jobtitle

        holder.itemView.setOnClickListener {
            listener.onSpeakerSelected(speaker, position)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgSpeakerPhoto: ImageView = itemView.findViewById(R.id.imgSpeakerPhoto)
        val lblSpeakerName: TextView = itemView.findViewById(R.id.lblSpeakerName)
        val lblSpeakerWork: TextView = itemView.findViewById(R.id.lblSpeakerWork)
    }
}