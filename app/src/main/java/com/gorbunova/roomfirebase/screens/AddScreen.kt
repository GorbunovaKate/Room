package com.gorbunova.roomfirebase.screens
import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gorbunova.roomfirebase.MainViewModel
import com.gorbunova.roomfirebase.MainViewModelFactory
import com.gorbunova.roomfirebase.model.Note
import com.gorbunova.roomfirebase.navigation.NavRoute
import com.gorbunova.roomfirebase.ui.theme.RoomFirebaseTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddScreen(navController: NavHostController, viewModel: MainViewModel) {
    var title by remember { mutableStateOf("")}
    var subtitle by remember { mutableStateOf("")}
    var text by remember { mutableStateOf("")}

    var isButtonEnabled by remember { mutableStateOf(false)}

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Add new note",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = title,
                onValueChange =  {
                    title = it
                    isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty() && text.isNotEmpty()
                },
                label = { Text(text = "Note title") },
                isError = title.isEmpty()
            )
            OutlinedTextField(
                value = subtitle,
                onValueChange =  {
                    subtitle = it
                    isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty() && text.isNotEmpty()
                                 },
                label = { Text(text = "Note subtitle") },
                isError = subtitle.isEmpty()
            )
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                    isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty() && text.isNotEmpty()
                                },
                label = { Text(text = "Note text") },
                isError = text.isEmpty()
            )
            Button(
                modifier = Modifier.padding(top = 16.dp),
                enabled = isButtonEnabled,
                onClick = {

                    viewModel.addNote(note =  Note(title = title, subtitle = subtitle, text = text)) {
                        navController.navigate(NavRoute.Main.route)
                    }
                }
            ) {
                Text(text = "Add note")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevAddScreen() {
    RoomFirebaseTheme {

        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

        AddScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}