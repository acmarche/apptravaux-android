package be.marche.apptravaux.screens.widgets

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.marche.apptravaux.R
import be.marche.apptravaux.ui.theme.ScreenSizeTheme
import be.marche.apptravaux.ui.theme.green
import be.marche.apptravaux.ui.theme.red

@Composable
fun CardRow(
    texte: String,
    onItemCLick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clickable {
                onItemCLick()
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = texte, fontSize = ScreenSizeTheme.textStyle.fontWidth_1)
        }
    }
}

@Composable
fun ErrorDialog(message: String) {
    Text(
        text = message,
        modifier = Modifier
            .padding(start = 24.dp, top = 50.dp, end = 24.dp, bottom = 50.dp),
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
    )
}

@Composable
fun ConnectivityStatusBox(
    isConnected: Boolean
) {
    val backgroundColor by animateColorAsState(targetValue = if (isConnected) green else red)
    val message = if (isConnected) "Connexion Internet ok" else "Pas de connexion Internet"
    val iconResource = if (isConnected) {
        R.drawable.ic_connectivity_available
    } else {
        R.drawable.ic_connectivity_unavailable
    }
    if (!isConnected) {
        Box(
            modifier = Modifier
                .background(backgroundColor)
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = iconResource),
                    contentDescription = "Connection Image",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = message,
                    color = Color.White,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun CircularProgressIndicatorSample() {
    val progress by remember { mutableStateOf(0.1f) }
    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(30.dp))
        CircularProgressIndicator()
    }
}

@Composable
fun OutlinedTextFieldJf(textStateThree: MutableState<TextFieldValue>) {
    OutlinedTextField(
        value = textStateThree.value,
        onValueChange = { textStateThree.value = it },
        label = { Text(text = "Comment") },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        maxLines = 5,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        )
    )
}

@Composable
fun MyNumberField(number: String, onChange: (String) -> Unit) {

    var inputValue by remember { mutableStateOf(number) }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = inputValue,
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Quantité") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(
            onDone = {
                onChange(inputValue)
                keyboardController?.hide()
            }
        ),
        onValueChange = {
            inputValue = it
            //   onChange(it)
        }
        // keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
    )
}
