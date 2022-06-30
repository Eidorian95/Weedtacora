package com.eidorian.weedtacora.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eidorian.weedtacora.presentation.navigation.Navigation
import com.eidorian.weedtacora.ui.theme.WeedtacoraTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeedtacoraTheme {
               Navigation()
            }
        }
    }
}