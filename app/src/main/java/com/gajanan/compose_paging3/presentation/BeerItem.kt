package com.gajanan.compose_paging3.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gajanan.compose_paging3.domain.modals.Beer

@Composable
fun BeerItem(
    beer: Beer,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(IntrinsicSize.Max)
        ) {
            AsyncImage(
                model = beer.image_url,
                contentDescription = beer.description,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = beer.name,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = beer.tagline,
                    fontStyle = FontStyle.Italic,
                    color = Color.LightGray,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = beer.description,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = "First brewed in ${beer.first_brewed}",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    fontSize = 10.sp
                )
            }
        }

    }

}
