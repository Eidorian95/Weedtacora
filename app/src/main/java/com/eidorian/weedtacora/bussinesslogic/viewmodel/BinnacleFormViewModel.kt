package com.eidorian.weedtacora.bussinesslogic.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eidorian.weedtacora.bussinesslogic.usecase.CreateBinnacleUseCase
import com.eidorian.weedtacora.data.entities.Binnacle
import com.eidorian.weedtacora.di.IoDispatcher
import com.eidorian.weedtacora.presentation.events.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinnacleFormViewModel @Inject constructor(
    private val useCase: CreateBinnacleUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiEvents = MutableSharedFlow<UiState>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val uiEvents: Flow<UiState>
        get() = _uiEvents

    var date by mutableStateOf("")
    var stage by mutableStateOf("")
    var observation by mutableStateOf("")


    fun onCreateNewBinnacle(growthId: Int) {
        viewModelScope.launch(context = dispatcher) {
            when (useCase(buildBinnacle(growthId))) {
                true -> _uiEvents.emit(UiState.Success)
                else -> _uiEvents.emit(UiState.Failure)
            }
        }
    }

    private fun buildBinnacle(growthId: Int) = Binnacle(
        date = date,
        observation = observation,
        stage = stage,
        fkGrowthId = growthId,
    )
}