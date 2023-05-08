package com.example.selfmade

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.selfmade.ui.theme.SelfMadeTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.*
import java.util.jar.Attributes.Name

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelfMadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()){
                        Image(painter = painterResource(id = R.drawable.mapmakd), contentDescription ="mapmak",
                        modifier = Modifier.fillMaxSize(),
                        contentScale= ContentScale.Fit
                        )

                        Card(modifier = Modifier
                            .fillMaxSize()
                            .padding(26.dp)
                            .border(width = 2.dp, color = Color.Blue, shape = RoundedCornerShape(15.dp))
                            .alpha(0.95f), backgroundColor = Color.White,
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(top = 7.dp)) {

                                Text(text = "Welcome to", fontSize = 20.sp, color = Color.Black)
                                Spacer(modifier = Modifier.padding(7.dp))
                                Text(text = "WeLearn", fontSize = 38.sp, color = Color.Blue, fontWeight = FontWeight.ExtraBold)
                                Text(text = "A School Management System")
                                Spacer(modifier = Modifier.padding(7.dp))

                                Text(text = "Sign Up", fontSize = 28.sp, color = Color.Blue, fontWeight = FontWeight.ExtraBold)
                                Spacer(modifier = Modifier.padding(7.dp))

                                Column() {
                                    var fullName by remember { mutableStateOf("") }
                                    var SchoolName by remember { mutableStateOf("") }
                                    var DateOfBirth by remember { mutableStateOf(TextFieldValue("")) }
                                    var Emailss by remember { mutableStateOf("") }
                                    var Passwordd by remember { mutableStateOf("") }

                                    OutlinedTextFields(
                                        value = fullName,
                                        labelName = "Enter your name",
                                        onValueChange = {
                                            fullName=it
                                        }
                                    )


                                    OutlinedTextFields(
                                        value = SchoolName,
                                        labelName = "Enter school name",
                                        onValueChange = {
                                            SchoolName=it
                                        }
                                    )
                                    DateInput(
                                        label = "Enter Date of Birth",
                                        value = DateOfBirth,
                                        onValueChange = {DateOfBirth=it},
                                        onDateSelected = { timestamp ->
                                            val calendar = Calendar.getInstance().apply {
                                                timeInMillis = timestamp
                                            }
                                            DateOfBirth = TextFieldValue(text = java.text.SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time))
                                        }
                                    )
                                    DemoField(
                                        value = Emailss,
                                        label = "Username",
                                        placeholder = "Enter your email",
                                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
                                        trailingIcon = {  },
                                        onValueChange = {Emailss=it},
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)

                                    )

                                    DemoField(
                                        value = Passwordd,
                                        label = "password",
                                        placeholder = "Enter your password",
                                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password")},
                                        trailingIcon = { /*TODO*/ },
                                        onValueChange = {Passwordd=it},
                                        visualTransformation = PasswordVisualTransformation(),
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next)

                                    )



                                }
                                Spacer(modifier = Modifier.padding(15.dp))

                                Button(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 35.dp, end = 35.dp)
                                    ,onClick = { /*TODO*/ },
                                    shape = RoundedCornerShape(40.dp),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)) {
                                    Text(text = "Sign Up", color = Color.White)
                                }




                                Row(modifier = Modifier.fillMaxWidth()){
                                    TextButton(modifier = Modifier.padding(start =140.dp),onClick = { /*TODO*/ }, ) {
                                        Text(text = "Already have account" , color = Color.Blue)
                                    }
                                }


                                }



                            }
                        }
                        
                    }
                }
            }
        }
    }


@Composable
fun OutlinedTextFields(value: String,
            labelName: String, onValueChange: (String)->Unit){
    OutlinedTextField(
        value = value,
        label = { Text(text = labelName, color = Color.Blue) },
        onValueChange = onValueChange,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color.Blue
        ),
        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
    )
}


@Composable
fun DateInput(
    label: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    onDateSelected: (Long) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Define the date picker dialog
    val datePicker = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                }
                onDateSelected(calendar.timeInMillis)
            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
    }

    // Define the text field with date picker icon
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.Blue) },
        leadingIcon = {
            Icon(
                Icons.Filled.DateRange,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        },


        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color.Blue
        ),
        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),

        trailingIcon = {
            Icon(
                Icons.Filled.DateRange,
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        showDialog = true
                    }
                    .size(24.dp)
            )
        },
    )

    // Show the date picker dialog when the icon is clicked
    if (showDialog) {
        datePicker.show()
        showDialog = false
    }
}


@Composable
fun DemoField(value: String,
              label: String, placeholder:String,
              visualTransformation: VisualTransformation = VisualTransformation.None,
              keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
              leadingIcon: @Composable () -> Unit,
              trailingIcon: @Composable () ->Unit,
              onValueChange: (String)->Unit,
){
    OutlinedTextField(value = value, onValueChange = onValueChange, label={
        Text(text = label,color = Color.Blue )
    }, placeholder = {
        Text(text = placeholder)
    },
        visualTransformation = visualTransformation,
        keyboardOptions =keyboardOptions,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color.Blue
        ),
        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)

    )
}

