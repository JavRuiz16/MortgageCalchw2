package com.example.mortgagecalchw2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mortgagecalchw2.ui.theme.MortgageCalcHw2Theme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MortgageCalcHw2Theme {
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MortgageCalcHw2Layout() {
    var loanAmountInput by remember { mutableStateOf("") }
    var interestRateInput by remember { mutableStateOf("") }
    var yearsInput by remember { mutableStateOf("") }

    val loanAmount = loanAmountInput.toIntOrNull() ?: 0
    val interestRate = (interestRateInput.toDoubleOrNull() ?: 0.0) / 100.0
    val years = yearsInput.toIntOrNull() ?: 0


    Column(
        modifier = Modifier
            .padding(40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        Text(
            text = stringResource(R.string.calculate_mortgage),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.Start)
        )
        TextField(
            value = loanAmountInput,
            leadingIcon = { Icon(
                painter = painterResource(R.drawable.baseline_attach_money_24),
                contentDescription = stringResource(R.string.loan_amount)
            ) },
            onValueChange = { loanAmountInput = it },
            singleLine = true,
            label = { Text(stringResource(R.string.loan_amount)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        TextField(
            value = interestRateInput,
            leadingIcon = { Icon(
                painter = painterResource(R.drawable.baseline_percent_24),
                contentDescription = stringResource(id = R.string.interest_rate)
            ) },
            onValueChange = { interestRateInput = it },
            singleLine = true,
            label = { Text(stringResource(R.string.interest_rate)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        TextField(
            value = yearsInput,
            leadingIcon = { Icon(
                painter = painterResource(R.drawable.baseline_calendar_month_24),
                contentDescription = stringResource(id = R.string.years)
            ) },
            onValueChange = { yearsInput = it },
            singleLine = true,
            label = { Text(stringResource(R.string.years)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text(text = stringResource(id = label)) },
        keyboardOptions = keyboardOptions
    )
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
