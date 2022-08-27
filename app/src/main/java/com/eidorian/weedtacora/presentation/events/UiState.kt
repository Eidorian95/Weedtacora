package com.eidorian.weedtacora.presentation.events

sealed class UiState {
    object Success : UiState()
    object Failure : UiState()
}
