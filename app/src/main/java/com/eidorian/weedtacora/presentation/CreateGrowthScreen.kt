package com.eidorian.weedtacora.presentation

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eidorian.weedtacora.bussinesslogic.viewmodel.CreatorViewModel
import com.eidorian.weedtacora.presentation.components.Footer
import com.eidorian.weedtacora.presentation.navigation.Screen
import com.eidorian.weedtacora.ui.theme.WeedtacoraTheme
import java.util.*

@Composable
fun CreateGrowthScreen(
    viewModel: CreatorViewModel = viewModel()
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
            ColumnForm()
        }
        Row {
            Footer("Agregar") {
                viewModel.onCreateNewGrowth("1 cultivo test", "10/07/2022","1 descripcion del cultivo")
            }
        }
    }
}

@Composable
private fun ColumnForm() {
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "Nombre") }
        )
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        DatePicker()
        Spacer(modifier = Modifier.padding(vertical = 16.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(112.dp),
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "Descripción") }
        )
    }
}

@Composable
private fun DatePicker() {
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
        CreateGrowthScreen()
    }
}