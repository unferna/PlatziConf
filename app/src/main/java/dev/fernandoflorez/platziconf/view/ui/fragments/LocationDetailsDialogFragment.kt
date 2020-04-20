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

import dev.fernandoflorez.platziconf.R
import dev.fernandoflorez.platziconf.model.Location
import kotlinx.android.synthetic.main.fragment_location_details_dialog.*

class LocationDetailsDialogFragment : DialogFragment(), View.OnClickListener {
    private lateinit var location: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FullscreenDialogStyle)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_details_dialog, container, false)
    }

    private fun setToolbar(view: View, name: String?) {
        toolbarLocationDetails.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolbarLocationDetails.navigationIcon?.setTint(Color.WHITE)
        toolbarLocationDetails.setTitleTextColor(Color.WHITE)
        toolbarLocationDetails.setNavigationOnClickListener {
            dismiss()
        }

        toolbarLocationDetails.title = name
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        location = arguments?.getSerializable("location") as Location
        setToolbar(view, location.name)

        lblDetailConferenceName.text = location.name
        lblDetailConferenceAddress.text = location.address
        lblDetailConferencePhone.text = location.phone
        lblDetailConferenceWebsite.text = location.website

        placeContainerView.setOnClickListener(this)
        websiteContainerView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.placeContainerView -> {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("tel:${ location.phone }")
                }

                startActivity(intent)
            }

            R.id.websiteContainerView -> {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(location.website)
                }

                startActivity(intent)
            }
        }
    }
}
