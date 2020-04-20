package dev.fernandoflorez.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.fernandoflorez.platziconf.model.Conference
import dev.fernandoflorez.platziconf.network.Callback
import dev.fernandoflorez.platziconf.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel: ViewModel() {
    private val firestoreService = FirestoreService()
    var scheduleList: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getSchedulesFromFirebase()
    }

    fun getSchedulesFromFirebase() {
        firestoreService.getSchedules(object: Callback<List<Conference>> {
            override fun onSuccess(response: List<Conference>?) {
                scheduleList.postValue(response)
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