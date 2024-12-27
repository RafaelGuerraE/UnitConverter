package guerra.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import guerra.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    //When we use the "by" keyword, it must be a var. On the other hand, if we don't use it, it must be a val

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }

    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }

    var isInputExpanded by remember { mutableStateOf(false) }
    var isOutputExpanded by remember { mutableStateOf(false) }

    val conversionFactor = remember { mutableDoubleStateOf(1.0) }
    val outputConversionFactor = remember { mutableDoubleStateOf(1.0) }

    fun convertUnits(){

        // Elvis Operator: "  ?: 0.0  "

        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0

        val result = (inputValueDouble * conversionFactor.doubleValue * 100.0/ outputConversionFactor.doubleValue).roundToInt() / 100.0

        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            convertUnits() },
            placeholder = {Text("Enter the value")})

        Row {
            Box{
                Button(onClick = {
                        isInputExpanded = true
                }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, "")
                }

                DropdownMenu(expanded = isInputExpanded, onDismissRequest = {isInputExpanded = false}) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        isInputExpanded = false
                        inputUnit = "Centimeters"
                        conversionFactor.doubleValue = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        isInputExpanded = false
                        inputUnit = "Meters"
                        conversionFactor.doubleValue = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        isInputExpanded = false
                        inputUnit = "Feet"
                        conversionFactor.doubleValue = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        isInputExpanded = false
                        inputUnit = "Millimeters"
                        conversionFactor.doubleValue = 0.001
                        convertUnits()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))

            Box{
                Button(onClick = {
                        isOutputExpanded = true
                }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, "")
                }

                DropdownMenu(expanded = isOutputExpanded, onDismissRequest = { isOutputExpanded = false}) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        isOutputExpanded = false
                        outputUnit = "Centimeters"
                        outputConversionFactor.doubleValue = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        isOutputExpanded = false
                        outputUnit = "Meters"
                        outputConversionFactor.doubleValue = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        isOutputExpanded = false
                        outputUnit = "Feet"
                        outputConversionFactor.doubleValue = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        isOutputExpanded = false
                        outputUnit = "Millimeters"
                        outputConversionFactor.doubleValue = 0.001
                        convertUnits()
                    })
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Result: $outputValue $outputUnit", style = MaterialTheme.typography.titleMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    UnitConverter()
}