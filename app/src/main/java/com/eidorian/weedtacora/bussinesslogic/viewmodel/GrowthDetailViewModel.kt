package com.eidorian.weedtacora.bussinesslogic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eidorian.weedtacora.bussinesslogic.usecase.GetGrowthDetailsUseCase
import com.eidorian.weedtacora.data.entities.Binnacle
import com.eidorian.weedtacora.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GrowthDetailViewModel @Inject constructor(
    private val useCase: GetGrowthDetailsUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _growthDetails = MutableSharedFlow<List<Binnacle>>()
    val growthDetails: Flow<List<Binnacle>>
        get() = _growthDetails

    fun fetchGrowthDetails(growthId: Int) {
        viewModelScope.launch(context = dispatcher) {
            val result = useCase.invoke(growthId)
            _growthDetails.emit(result)
        }
    }

}