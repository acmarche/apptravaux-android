package be.marche.apptravaux.screens.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import be.marche.apptravaux.R
import be.marche.apptravaux.entities.Avaloir
import be.marche.apptravaux.navigation.TravauxRoutes
import be.marche.apptravaux.ui.theme.ScreenSizeTheme
import be.marche.apptravaux.utils.DateUtils.Companion.formatDateTime
import coil.compose.rememberAsyncImagePainter

class AvaloirWidget {
    @Composable
    fun LoadAvaloirs(
        avaloirs: List<Avaloir>,
        streetNameState: MutableState<TextFieldValue>?,
        numberIdState: MutableState<TextFieldValue>?,
        navController: NavController
    ) {

        LazyColumn {
            val streetSearchedText = streetNameState?.value?.text
            val numberSearchedId = numberIdState?.value?.text

            val filteredList = avaloirs.filter { avaloir ->
                (numberSearchedId == null || numberSearchedId.isEmpty() || (numberSearchedId.toIntOrNull()
                    ?: -1) == avaloir.idReferent) &&
                        (streetSearchedText == null || streetSearchedText.isEmpty() || avaloir.rue?.contains(
                            streetSearchedText,
                            ignoreCase = true
                        ) ?: false)
            }

            items(filteredList) { avaloir ->
                ItemAvaloir(avaloir) {
                    navController.navigate(TravauxRoutes.AvaloirDetailScreen.route + "/${avaloir.idReferent}")
                }
            }
        }
    }

    @Composable
    fun ItemAvaloir(
        avaloir: Avaloir,
        onItemCLick: (Int) -> Unit
    ) {
        Card(
            modifier = Modifier
                .clickable {
                    onItemCLick(avaloir.idReferent)
                }
                .padding(10.dp)
                .fillMaxSize(),
            elevation = 5.dp,
            shape = RoundedCornerShape(5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                ImageAvaloir(
                    avaloir.imageUrl,
                    ScreenSizeTheme.dimens.width,
                    ScreenSizeTheme.dimens.height
                )
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    val texteRue = avaloir.rue ?: "non déterminé"
                    Text(
                        text = texteRue,
                        style = ScreenSizeTheme.textStyle.fontStyle_1,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.padding(5.dp))
                    val texteLocalite = avaloir.localite ?: "non déterminé"

                    Text(
                        text = texteLocalite,
                        style = ScreenSizeTheme.textStyle.fontStyle_1,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "Ajouté le ${formatDateTime(avaloir.createdAt)}",
                        style = ScreenSizeTheme.textStyle.fontStyle_1,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = "Numéro ${avaloir.idReferent}",
                        style = ScreenSizeTheme.textStyle.fontStyle_1,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.padding(5.dp))
                }
            }
        }
    }

    @Composable
    fun ImageAvaloir(
        imagePath: String?,
        imageWidth: Dp,
        imageHeight: Dp,
        contentScale: ContentScale = ContentScale.Crop,
        padding: Dp = 5.dp
    ) {
        val painterImg: Painter
        if (imagePath == null) {
            painterImg = painterResource(R.drawable.profile_picture)
        } else {
            painterImg = rememberAsyncImagePainter(imagePath)
        }

        Image(
            painterImg,
            contentDescription = "Image",
            contentScale = contentScale,
            modifier = Modifier
                .width(imageWidth)
                .height(imageHeight)
                .padding(padding)
        )
    }
}