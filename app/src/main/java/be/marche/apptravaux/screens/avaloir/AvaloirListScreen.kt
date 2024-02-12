package be.marche.apptravaux.screens.avaloir

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import be.marche.apptravaux.entities.AvaloirUiState
import be.marche.apptravaux.navigation.TravauxRoutes
import be.marche.apptravaux.screens.widgets.AvaloirWidget
import be.marche.apptravaux.screens.widgets.ErrorDialog
import be.marche.apptravaux.screens.widgets.TopAppBarJf
import be.marche.apptravaux.ui.theme.MEDIUM_PADDING
import be.marche.apptravaux.ui.theme.ScreenSizeTheme
import be.marche.apptravaux.viewModel.AvaloirViewModel

class AvaloirListScreen(val navController: NavController) {

    @Composable
    fun ListScreen(
        avaloirViewModel: AvaloirViewModel = viewModel()
    ) {
        LaunchedEffect(true) {
            avaloirViewModel.fetchAvaloirsFromDb()
        }

        val streetTextState = remember { mutableStateOf(TextFieldValue("")) }
        val avaloirNumberState = remember { mutableStateOf(TextFieldValue("")) }

        Scaffold(
            topBar = {
                TopAppBarJf(
                    "Liste des avaloirs"
                ) { navController.navigate(TravauxRoutes.AvaloirHomeScreen.route) }
            }
        ) { contentPadding ->
            Box(modifier = Modifier.padding(contentPadding)) {

                when (val dataUiState = avaloirViewModel.uiState.collectAsState().value) {
                    is AvaloirUiState.Loading -> {
                    }

                    is AvaloirUiState.Error -> {
                        ErrorDialog(dataUiState.message)
                    }

                    is AvaloirUiState.Loaded -> {
                        val widget = AvaloirWidget()
                        Column {
                            SearchView(streetTextState, avaloirNumberState, {})
                            widget.LoadAvaloirs(dataUiState.data, streetTextState, avaloirNumberState, navController)
                        }
                    }

                    is AvaloirUiState.Empty -> {
                        Column {
                            ErrorDialog("La liste est vide")
                            Divider(
                                modifier = Modifier.height(MEDIUM_PADDING),
                                color = MaterialTheme.colors.background
                            )

                            Button(
                                onClick = { navController.navigate(TravauxRoutes.SyncScreen.route) }
                            ) {
                                Text(text = "Synchroniser les données")
                            }
                            Divider(
                                modifier = Modifier.height(MEDIUM_PADDING),
                                color = MaterialTheme.colors.background
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun SearchView(
        streetState: MutableState<TextFieldValue>,
        numberState: MutableState<TextFieldValue>,
        onChange: (TextFieldValue) -> Unit
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        OutlinedTextField(
            value = streetState.value,
            label = { Text(text = "Rue") },
            onValueChange = { value ->
                streetState.value = value
                //onChange(value)
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            textStyle = ScreenSizeTheme.textStyle.fontStyleSearch,
            // textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(24.dp)
                )
            },
            trailingIcon = {
                if (streetState.value != TextFieldValue("")) {
                    IconButton(
                        onClick = {
                            streetState.value =
                                TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                        }
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }
                }
            },
            singleLine = true,
            shape = RectangleShape,
        )
        OutlinedTextField(
            value = numberState.value,
            onValueChange = { numberState.value = it },
            label = { Text("Numéro avaloir") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            textStyle = ScreenSizeTheme.textStyle.fontStyleSearch,
        )
    }


}