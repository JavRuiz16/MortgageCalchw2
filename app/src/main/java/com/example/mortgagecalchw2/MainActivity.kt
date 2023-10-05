package com.example.mortgagecalchw2

import android.icu.text.NumberFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mortgagecalchw2.ui.theme.MortgageCalcHw2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MortgageCalcHw2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MortgageCalcHw2Layout()
                }
            }
        }
    }
}

@Composable
fun MortgageCalcHw2Layout() {
    var amountInput by remember { mutableStateOf("") }
    var tipInput by remember { mutableStateOf("") }
    var roundUp by remember { mutableStateOf(false) }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val interestPercent = tipInput.toDoubleOrNull() ?: 0.0

    val tip = calculateMortgage(amount, interestPercent)
    Column(
            modifier = Modifier
                .padding(40.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.calculate_mortgage),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(alignment = Alignment.Start)
            )
            EditNumberField(
                label = R.string.mortgage_amount,
                leadingIcon = R.drawable.null,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                value = amountInput,
                onValueChange = { amountInput = it},
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
            )
            EditNumberField(
                label = R.string.loan_amount,
                leadingIcon = R.drawable.null,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                value = tipInput,
                onValueChange = { tipInput = it },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(R.string.mortgage_amount),
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp
            )
            Spacer(modifier = Modifier.height(150.dp))
        }
}

@Composable
fun EditNumberField(label: Any, leadingIcon: Any, keyboardOptions: Any, value: String, onValueChange: () -> Unit, modifier: Any) {

}


private fun calculateMortgage(amount: Double, interestPercent: Double = 5.0): String {
    val interest = interestPercent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(interest)
}

@Preview(showBackground = true)
@Composable
fun MortgageCalcHw2Preview() {
    MortgageCalcHw2Theme {
        MortgageCalcHw2Layout()
    }
}
