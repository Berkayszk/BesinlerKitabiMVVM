package com.berkaysazak.besinlerkitabi.servis

import com.berkaysazak.besinlerkitabi.model.Besin
import io.reactivex.Single
import retrofit2.http.GET

interface BesinAPI {

    //GET , POST kullanacağız Get ile verileri alıcağız post ile oyllayacağız öyle düşünmemiz lazım.

    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getBesin() : Single<List<Besin>>
}