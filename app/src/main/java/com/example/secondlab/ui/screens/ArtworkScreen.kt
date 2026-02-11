package com.example.secondlab.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.secondlab.R
import com.example.secondlab.data.ArtworkRepository
import com.example.secondlab.ui.theme.ArtworkTheme

@Composable
fun ArtworkScreen() {
    val artworks = ArtworkRepository.artworks
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    var currentIndex by rememberSaveable { mutableStateOf(0) }
    val currentArtwork = artworks[currentIndex]

    val imageHeight = if (isLandscape) 200.dp else 300.dp
    val horizontalPadding = if (isLandscape) 48.dp else 16.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = horizontalPadding, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Изображение
            Image(
                painter = painterResource(id = currentArtwork.imageResId),
                contentDescription = stringResource(id = currentArtwork.descriptionResId), // Accessibility!
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Название
            Text(
                text = stringResource(id = currentArtwork.titleResId),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Автор
            Text(
                text = stringResource(id = currentArtwork.authorResId),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Год
            Text(
                text = stringResource(id = currentArtwork.yearResId),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Описание
            Text(
                text = stringResource(id = currentArtwork.descriptionResId),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = if (isLandscape) TextAlign.Center else TextAlign.Start
            )

            Spacer(modifier = Modifier.weight(1f))
            // Кнопки навигации
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(
                    onClick = { if (currentIndex > 0) currentIndex-- },
                    enabled = currentIndex > 0,
                    modifier = Modifier.width(140.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (currentIndex > 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                        contentColor = if (currentIndex > 0) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    )
                ) {
                    Text(stringResource(id = R.string.button_previous))
                }

                Button(
                    onClick = { if (currentIndex < artworks.size - 1) currentIndex++ },
                    enabled = currentIndex < artworks.size - 1,
                    modifier = Modifier.width(140.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (currentIndex < artworks.size - 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                        contentColor = if (currentIndex < artworks.size - 1) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    )
                ) {
                    Text(stringResource(id = R.string.button_next))
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun PreviewArtSpacePortrait() {
    ArtworkTheme {
        ArtworkScreen()
    }
}

@Preview(showBackground = true, widthDp = 640, heightDp = 360)
@Composable
fun PreviewArtSpaceLandscape() {
    ArtworkTheme {
        ArtworkScreen()
    }
}