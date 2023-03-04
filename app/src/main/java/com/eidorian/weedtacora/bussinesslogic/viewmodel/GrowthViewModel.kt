package com.eidorian.weedtacora.bussinesslogic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eidorian.weedtacora.bussinesslogic.usecase.DeleteGrowthUseCase
import com.eidorian.weedtacora.bussinesslogic.usecase.GetAllGrowthsUseCase
import com.eidorian.weedtacora.data.entities.Growth
import com.eidorian.weedtacora.di.IoDispatcher
import com.eidorian.weedtacora.presentation.uimodel.GrowthUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GrowthViewModel @Inject constructor(
    private val getAllGrowths: GetAllGrowthsUseCase,
    private val deleteGrowth: DeleteGrowthUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _userGrowths = MutableSharedFlow<List<GrowthUiModel>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val userGrowths: Flow<List<GrowthUiModel>>
        get() = _userGrowths

    init {
        fetchUserGrowths()
    }

    fun fetchUserGrowths() {
        viewModelScope.launch(context = dispatcher) {
            val result = getAllGrowths()
            _userGrowths.emit(result)
        }
    }

    fun onDeleteGrowth(id: Int) {
        viewModelScope.launch(context = dispatcher) {
            deleteGrowth(id).run {
                fetchUserGrowths()
            }
        }
    }

}