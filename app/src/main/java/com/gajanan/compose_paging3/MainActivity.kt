package com.gajanan.compose_paging3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.gajanan.compose_paging3.presentation.BeerScreen
import com.gajanan.compose_paging3.presentation.BeerViewModel
import com.gajanan.compose_paging3.ui.theme.Compose_Paging3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_Paging3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel = hiltViewModel<BeerViewModel>()
                    val beerList = viewModel.beerPagingFlow.collectAsLazyPagingItems()
                    BeerScreen(beers = beerList)
                }
            }
        }
    }
}
