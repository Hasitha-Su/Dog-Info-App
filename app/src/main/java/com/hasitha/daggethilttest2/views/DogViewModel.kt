package com.hasitha.daggethilttest2.views

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasitha.daggethilttest2.model.DogApiResponse
import com.hasitha.daggethilttest2.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val dogRepository: DogRepository
) : ViewModel(){

    private val _dogData = MutableLiveData<DogApiResponse>(null)
    val dogData : LiveData<DogApiResponse>
        get() = _dogData

//    fun getDogInfo(){
//
//        viewModelScope.launch {
//            val dogInfo = dogRepository.getDogDataFromDataSource()
//            _dogData.value = dogInfo
//        }
//    }

    fun getDogInfo() {
        viewModelScope.launch {
            try {
                val dogInfo = dogRepository.getDogInfo()
                _dogData.value = dogInfo
            } catch (e: Exception) {
                Log.e("DogViewModel", "Error getting dog info", e)
            }
        }
    }
}