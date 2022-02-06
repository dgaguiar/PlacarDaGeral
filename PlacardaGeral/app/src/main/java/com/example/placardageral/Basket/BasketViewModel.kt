package com.example.placardageral.Basket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BasketViewModel: ViewModel() {
    var scoreHomePoint: MutableLiveData<Int> = MutableLiveData()
    var scoreVisitPoint: MutableLiveData<Int> = MutableLiveData()

    init {
        start()
    }

    fun goalHome(point: Int){
        scoreHomePoint.value = scoreHomePoint.value?.plus(point)
    }

    fun goalVisit(point: Int){
        scoreVisitPoint.value = scoreVisitPoint.value?.plus(point)
    }

    fun start() {
        scoreHomePoint.value = 0
        scoreVisitPoint.value = 0
    }
}