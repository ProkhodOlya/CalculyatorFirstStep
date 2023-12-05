package com.example.calculyator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculyator.ui.theme.CalculyatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculyatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Calculate()
                }
            }
        }
    }
}

@Composable
fun Calculate() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var sign by remember { mutableStateOf(Sign.PLUS) }
    var result by remember { mutableStateOf("") }
    result = calculate(sign, number1, number2).checkFloat().toString()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = number1,
                    { firstNumber ->
                        number1 = firstNumber.toFloatOrNull()?.checkFloat()?.toString() ?: number1
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(0.4f),
                )
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .weight(0.1f),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = sign.value,
                    )
                }
                TextField(
                    value = number2,
                    { secondNumber ->
                        number2 = secondNumber.toFloatOrNull()?.checkFloat()?.toString() ?: number2
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(0.4f),
                )
                Text(
                    text = "=",
                    modifier = Modifier
                        .weight(0.1f),
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f),
            ) {
                Text(
                    text = result,
                    modifier = Modifier
//                        .weight(1f, fill = false),
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .border(width = 4.dp, color = Gray, shape = RoundedCornerShape(16.dp))
                    .background(
                        color = getBackground(buttonSign = Sign.PLUS, selectedSign = sign),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable(onClick = { sign = Sign.PLUS }),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = Sign.PLUS.value,
                    textAlign = TextAlign.Center
                )
            }
            Box(Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .border(width = 4.dp, color = Gray, shape = RoundedCornerShape(16.dp))
                    .background(
                        color = getBackground(buttonSign = Sign.MINUS, selectedSign = sign),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable(onClick = { sign = Sign.MINUS }),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = Sign.MINUS.value,
                    textAlign = TextAlign.Center
//                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )
            }
            Box(Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .border(width = 4.dp, color = Gray, shape = RoundedCornerShape(16.dp))
                    .background(
                        color = getBackground(buttonSign = Sign.DIVIDE, selectedSign = sign),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable(onClick = { sign = Sign.DIVIDE }),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = Sign.DIVIDE.value,
                    textAlign = TextAlign.Center
//            modifier = Modifier.weight(0.5f, fill = false)
                )
            }
            Box(Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .border(width = 4.dp, color = Gray, shape = RoundedCornerShape(16.dp))
                    .background(
                        color = getBackground(buttonSign = Sign.MULTIPLY, selectedSign = sign),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable(onClick = { sign = Sign.MULTIPLY }),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = Sign.MULTIPLY.value,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

fun calculate(sign: Sign, number1: String, number2: String): Float {
    return when (sign) {
        Sign.PLUS -> (number1.toFloatOrNull() ?: 0.0f) + (number2.toFloatOrNull() ?: 0.0f)
        Sign.MINUS -> (number1.toFloatOrNull() ?: 0.0f) - (number2.toFloatOrNull() ?: 0.0f)
        Sign.DIVIDE -> (number1.toFloatOrNull() ?: 0.0f) / (number2.toFloatOrNull() ?: 0.0f)
        Sign.MULTIPLY -> (number1.toFloatOrNull() ?: 0.0f) * (number2.toFloatOrNull() ?: 0.0f)
    }
}

fun getBackground(buttonSign: Sign, selectedSign: Sign): Color {
    return if (buttonSign == selectedSign) {
        Color.Red
    } else {
        Color.Cyan
    }
}

enum class Sign(val value: String) {
    PLUS("+"),
    MINUS("-"),
    DIVIDE("/"),
    MULTIPLY("*")
}

fun Float.checkFloat(): Float {
    return if (isFinite()
    ) {
        this
    } else {
        Float.NaN
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatePreview() {
    CalculyatorTheme {
        Calculate()
    }
}