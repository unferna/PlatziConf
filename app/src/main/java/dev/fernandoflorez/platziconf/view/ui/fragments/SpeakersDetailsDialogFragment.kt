package dev.fernandoflorez.platziconf.view.ui.fragments


import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import dev.fernandoflorez.platziconf.R
import dev.fernandoflorez.platziconf.model.Speaker
import kotlinx.android.synthetic.main.fragment_speakers_details_dialog.*

interface SpeakersDetailsDialogFragmentDelegate {
    fun goToTwitter(url: String)
}

class SpeakersDetailsDialogFragment: DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_TITLE, R.style.FullscreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers_details_dialog, container, false)
    }

    private fun setToolbar(view: View, speakerName: String?) {
        toolbarSpeaker.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolbarSpeaker.navigationIcon?.setTint(Color.WHITE)
        toolbarSpeaker.setTitleTextColor(Color.WHITE)
        toolbarSpeaker.setNavigationOnClickListener {
            dismiss()
        }

        toolbarSpeaker.title = speakerName
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val speaker = arguments?.getSerializable("speaker") as Speaker
        setToolbar(view, speaker.name)

        // Image
        if( speaker.imageUrl.isNotEmpty() ) {
            Glide.with(view.context)
                .load(speaker.imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(imgSpeakerPhoto)
        }

        lblDetailSpeakerName.text = speaker.name
        lblDetailSpeakerJobTitle.text = speaker.jobtitle

        if( speaker.twitter.isNotEmpty() ) {
           btnGoTwitter.setOnClickListener {
               goToTwitter(speaker.twitter)
           }
        }

        lblDetailSpeakerWork.text = speaker.workPlace
        lblDetailSpeakerDescription.text = speaker.biography
    }

    private fun goToTwitter(nickname: String) {
        val openURL = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("https://twitter.com/$nickname")
        }

        startActivity(openURL)
    }
}
