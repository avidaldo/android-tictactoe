package com.avidaldo.android_tictactoe2.ui.tresenrayaview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avidaldo.android_tictactoe2.model.TresEnRayaModel

class TresEnRayaViewModel : ViewModel() {

    private var model = TresEnRayaModel()
    val modelLiveData = MutableLiveData(model)


    fun onButtonSelected(row: Int, col: Int) {
        model.marcar(row, col)
        modelLiveData.value = model
    }

    fun onResetSelected() {
        model.reiniciar()
        modelLiveData.value = model
    }
}