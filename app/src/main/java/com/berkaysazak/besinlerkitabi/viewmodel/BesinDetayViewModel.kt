package com.berkaysazak.besinlerkitabi.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berkaysazak.besinlerkitabi.model.Besin
import com.berkaysazak.besinlerkitabi.servis.BesinDatabase
import kotlinx.coroutines.launch

class BesinDetayViewModel(application : Application) : BaseViewModel(application) {

    val besinLiveData = MutableLiveData<Besin>()

    fun roomVerisiniAl(uuid : Int){
        launch {
            val dao = BesinDatabase(getApplication()).besinDao()
            val besin = dao.getBesin(uuid)
            besinLiveData.value = besin
        }
    }

}