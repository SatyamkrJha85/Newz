package com.theapplicationpad.newz.Presentation.Component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.theapplicationpad.ViewModel.NewsViewModel
import com.theapplicationpad.newz.ui.theme.Yello
import kotlinx.coroutines.launch
import java.util.Locale.Category

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryTabRow(
    pagerState: PagerState,
    categories: List<String>,
    onTabSelected: (Int) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 0.dp,
        containerColor = Color.Transparent,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                color = Yello, // Custom indicator color
                height = 4.dp // Adjust the height of the indicator
            )
        },

        modifier = Modifier.fillMaxWidth() // Ensures the Tab Row takes full width
    ) {
        categories.forEachIndexed { index, category ->
            val isSelected = pagerState.currentPage == index

            Tab(

                selected = pagerState.currentPage == index,

                onClick = {
                    onTabSelected(index)
                    // Update the pager state when a tab is selected
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                       // viewModel.searchNews(category)

                    }
                },
                content = {
                    Text(
                        text = category,
                        color = if (isSelected) Color.White else Color.Gray,
                        fontSize = if (isSelected) 18.sp else 14.sp,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
            )
        }
    }
}
