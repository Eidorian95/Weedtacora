package com.eidorian.weedtacora.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.eidorian.weedtacora.data.entities.Growth
import com.eidorian.weedtacora.data.repository.GrowthRepository
import com.eidorian.weedtacora.data.repository.GrowthRepositoryImpl
import com.eidorian.weedtacora.ui.theme.WeedtacoraTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var repo: GrowthRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeedtacoraTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }

//        lifecycleScope.launchWhenCreated {
//            val growth = Growth(initialDate = "today", notes = "sin notas", name = "mi cultivo")
//            repo.insertGrowth(growth)
//            val growth2 = Growth(initialDate = "toda2y", notes = "sin notas2", name = "mi cultivo2")
//            repo.insertGrowth(growth2)
//            val growth3 = Growth(initialDate = "today3", notes = "sin notas3", name = "mi cultivo3")
//            repo.insertGrowth(growth3)
//            val growth4 = Growth(initialDate = "today4", notes = "sin notas4", name = "mi cultivo4")
//            repo.insertGrowth(growth4)
//
//
//            val growths = repo.getAllGrowths()
//            Log.d("GROWTHS", "${growths.count()} ${growths.toString()}")
//        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeedtacoraTheme {
        Greeting("Android")
    }
}