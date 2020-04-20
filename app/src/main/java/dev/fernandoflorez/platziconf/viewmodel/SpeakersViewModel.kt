package dev.fernandoflorez.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.fernandoflorez.platziconf.model.Speaker
import dev.fernandoflorez.platziconf.network.Callback
import dev.fernandoflorez.platziconf.network.FirestoreService
import java.lang.Exception

class SpeakersViewModel: ViewModel() {
    private val firestoreService = FirestoreService()
    val speakersList: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getSpeakersFromFirebase()
    }

    fun getSpeakersFromFirebase() {
        firestoreService.getSpeakers(object: Callback<List<Speaker>> {
            override fun onSuccess(response: List<Speaker>?) {
                speakersList.postValue(response)
                processFinished()
            }

            override fun onError(exception: Exception) {
                processFinished()
            }
        })
    }

    private fun processFinished() {
        isLoading.value = true
    }
}