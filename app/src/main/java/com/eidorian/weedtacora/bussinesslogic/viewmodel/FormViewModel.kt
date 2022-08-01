package com.eidorian.weedtacora.bussinesslogic.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eidorian.weedtacora.bussinesslogic.usecase.CreateGrowthUseCase
import com.eidorian.weedtacora.data.entities.Growth
import com.eidorian.weedtacora.di.IoDispatcher
import com.eidorian.weedtacora.presentation.events.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val useCase: CreateGrowthUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiEvents = MutableSharedFlow<UiEvent>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val uiEvents: Flow<UiEvent>
        get() = _uiEvents

    var name by mutableStateOf("")
    var date by mutableStateOf("")
    var description by mutableStateOf("")


    fun onCreateNewGrowth() {
        viewModelScope.launch(context = dispatcher) {
            when (useCase(buildGrowth())) {
                true -> _uiEvents.emit(UiEvent.Success)
                else -> _uiEvents.emit(UiEvent.Failure)
            }
        }
    }

    private fun buildGrowth() = Growth(
        name = name,
        initialDate = date,
        notes = description
    )

}