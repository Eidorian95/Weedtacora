package com.eidorian.weedtacora.presentation

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eidorian.weedtacora.bussinesslogic.viewmodel.FormViewModel
import com.eidorian.weedtacora.presentation.components.Footer
import com.eidorian.weedtacora.ui.theme.WeedtacoraTheme
import java.util.*

@Composable
fun CreateGrowthScreen(
    viewModel: FormViewModel = viewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .weight(1f)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.name,
                    onValueChange = { viewModel.name = it },
                    placeholder = { Text(text = "Nombre") }
                )
                Spacer(modifier = Modifier.padding(vertical = 16.dp))

                DatePicker { viewModel.date = it }

                Spacer(modifier = Modifier.padding(vertical = 16.dp))

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(112.dp),
                    value = viewModel.description,
                    onValueChange = { viewModel.description = it },
                    placeholder = { Text(text = "Descripción") }
                )
            }
        }
        Row {
            Footer("Agregar") {
                viewModel.onCreateNewGrowth()
            }
        }
    }
}

@Composable
private fun DatePicker(onSelectedDate: (String) -> Unit) {
    val mContext = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDate = remember { mutableStateOf("dd/mm/aa") }

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
            onSelectedDate(mDate.value)
        }, mYear, mMonth, mDay
    )

    Row(
        verticalAlignment = Alignment.CenterVertically

    ) {
        Button(
            onClick = {
                mDatePickerDialog.show()
            },
        ) {
            Text(text = "Fecha de inicio", color = Color.White)
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = mDate.value,
            fontSize = 16.sp,
            textAlign = TextAlign.End,
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    WeedtacoraTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ){
            CreateGrowthScreen()
        }
    }
}