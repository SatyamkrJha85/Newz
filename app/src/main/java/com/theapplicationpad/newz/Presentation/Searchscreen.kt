package com.theapplicationpad.newz.Presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.theapplicationpad.ViewModel.NewsViewModel
import com.theapplicationpad.newz.Presentation.Component.SwipeableCard
import com.theapplicationpad.newz.ui.theme.LightBlack
import com.theapplicationpad.newz.ui.theme.Lightbright

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Searchscreen(modifier: Modifier = Modifier, viewModel: NewsViewModel = hiltViewModel(),
                 navController: NavController) {
    val articles by viewModel.articles.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    var searchnewz = remember {
        mutableStateOf("health")
    }

    viewModel.searchNews(searchnewz.value)

    Scaffold(containerColor = Color.Black,
        topBar = {
            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            ), title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(
                        modifier
                            .size(40.dp)
                            .clip(shape = CircleShape)
                            .background(LightBlack)
                    ) {
                        IconButton(onClick = {

                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                    Spacer(modifier = modifier.width(10.dp))
                    Text(text = "Search Newz", color = Color.White, fontSize = 20.sp)
                }

            })
        },
        content = { paddingValues ->

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(paddingValues)
            ) {

                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextField(
                        value = searchnewz.value, onValueChange = { searchnewz.value = it },
                        modifier
                            .width(280.dp)
                            .height(54.dp)
                            .clip(shape = RoundedCornerShape(25.dp)),
                        placeholder = { Text(text = "Search Your Newz...", fontSize = 14.sp) },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = LightBlack,
                            focusedTextColor = Color.White,
                            disabledTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedPlaceholderColor = Color.White,
                            disabledPlaceholderColor = Color.White,
                            unfocusedPlaceholderColor = Color.White,
                            disabledIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )


                    Box(
                        modifier
                            .size(50.dp)
                            .clip(shape = CircleShape)
                            .background(LightBlack)
                    ) {
                        IconButton(onClick = {
                            searchnewz.value=""
                            viewModel.searchNews(searchnewz.value)

                        }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }

                }

                Column {

                    when {
                        loading -> Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = LightBlack)
                        }

                        error != null -> Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = error ?: "Unknown error",
                                modifier = Modifier.padding(16.dp)
                            )
                        }

                        else -> {


                            Column {


                                SwipeableCard(
                                    navController = navController,
                                    dataSource = (0..articles.size).map { 0 }.toList(),
                                    articles = articles,

                                )

                            }
                        }
                    }
                }


            }

        }
    )
}