package com.eidorian.weedtacora.presentation.events

sealed class UiEvent {
    object Success : UiEvent()
    object Failure : UiEvent()
}
