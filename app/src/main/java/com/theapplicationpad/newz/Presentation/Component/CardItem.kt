package com.theapplicationpad.newz.Presentation.Component

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.service.autofill.OnClickAction
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Carpenter
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Pending
import androidx.compose.material.icons.filled.ReadMore
import androidx.compose.material.icons.filled.SaveAs
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Source
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.theapplicationpad.newz.Navigation.Routes
import com.theapplicationpad.newz.Presentation.Component.Constants.cornerRadiusBig
import com.theapplicationpad.newz.Presentation.Component.Constants.normalElevation
import com.theapplicationpad.newz.R
import com.theapplicationpad.newz.Room.ArticleEntity
import com.theapplicationpad.newz.ui.theme.LightBlack
import com.theapplicationpad.newz.ui.theme.Lightbright


@Composable
fun CardItem(
    navController: NavController,
    modifier: Modifier = Modifier,
    cardIndex: Int,
    article: ArticleEntity,
) {
    // Define a list of 10 colors
    val cardColors = listOf(
        Color(0xFFFFC107), // Amber
        Color(0xFF03A9F4), // Light Blue
        Color(0xFFFFEB3B), // Yellow
        Color(0xFF00BCD4), // Cyan
        Color(0xFFFF9800), // Orange
        Color(0xFF4CAF50), // Green
        Color(0xFFE91E63)  // Pink
    )

    // Use the cardIndex to select a color from the list
    val cardColor = cardColors[cardIndex % cardColors.size]
    val context = LocalContext.current
    var selectedArticle by remember { mutableStateOf<ArticleEntity?>(null) }
    var showSheet by remember { mutableStateOf(false) }


    Card(
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadiusBig),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor) // Set the card color

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Row (
                modifier=Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Box(modifier = Modifier
                    .padding(10.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(Color.Red)
                    .padding(5.dp) ){
                    Text(text = "Live")
                }

                Box(modifier = Modifier
                    .padding(10.dp)
                    .clickable {

                        article.url?.let { url ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        } ?: run {
                            Log.e("CardItem", "Article URL is null, cannot open.")
                        }

                    }
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(LightBlack)
                    .padding(5.dp) ){
                    Row {

                        Text(text = "Read full article")
                        Icon(imageVector = Icons.Filled.ReadMore, contentDescription =null )
                    }

                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Text(
                    text = article.title ?: "No Title",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            
            Row {

                Box(modifier = Modifier
                    .padding(10.dp)
                    .clip(shape = CircleShape)
                    .background(Color(0xFFFFC107))
                    .padding(5.dp)  ) {
                    Icon(imageVector = Icons.Filled.Pending, contentDescription =null, tint = Color.Black)
                }
                Column {
                    Text(text = "Author", fontWeight = FontWeight.Normal, fontSize = 14.sp, color = Color.Black)
                    article.author?.let { Text(text = it, fontWeight = FontWeight.Bold, color = Color.Black) }

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                article?.description?.let {
                    Text(
                        text = it,
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            color = Color.Black,
                            fontSize = 13.sp
                        )
                    )
                }
            }

            Row {

                Box(modifier = Modifier
                    .padding(10.dp)
                    .clip(shape = CircleShape)
                    .background(Color(0xFFFFEB3B))
                    .padding(5.dp)  ) {
                    Icon(imageVector = Icons.Filled.Source, contentDescription =null, tint = Color.Black)
                }
                Column {
                    Text(text = "Source name", fontWeight = FontWeight.Normal, fontSize = 14.sp, color = Color.Black)
                    article.sourceName?.let { Text(text = it, fontWeight = FontWeight.Bold, color = Color.Black) }

                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Column {
                    Text(text = "Published At", fontWeight = FontWeight.Normal, fontSize = 14.sp, color = Color.Black)
                    article.publishedAt?.let { Text(text = it, fontWeight = FontWeight.Bold, color = Color.Black) }

                }
            }
            Row (
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ){
                Box(modifier = Modifier
                    .size(45.dp)
                    .padding(4.dp)
                    .clip(shape = CircleShape)
                    .background(Lightbright)
                    .padding(5.dp) , contentAlignment = Alignment.Center ) {
                    Text(text = cardIndex.toString(), color = Color.Black)
                }

                Box(modifier = Modifier
                    .padding(4.dp)
                    .clickable {
                        shareArticle(
                            context = context, url = article.url
                        )
                    }
                    .clip(shape = CircleShape)
                    .background(Lightbright)
                    .padding(5.dp)  ) {
                    Icon(imageVector = Icons.Filled.Share, contentDescription =null, tint = Color.Black)
                }
                Box(modifier = Modifier
                    .padding(4.dp)
                    .clickable {
                        selectedArticle = article

                        showSheet = true
                    }
                    .clip(shape = CircleShape)
                    .background(Lightbright)
                    .padding(5.dp)  ) {
                    Icon(imageVector = Icons.Filled.MenuBook, contentDescription =null, tint = Color.Black)
                }
            }



        }
    }
    if (showSheet) {
        BottomSheet(onDismiss = { showSheet = false }, article = selectedArticle!!)
    }

}
fun shareArticle(context: Context, url: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, "Check out this article: $url")
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share via"))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(onDismiss: () -> Unit, article: ArticleEntity) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = article.urlToImage,
                        placeholder = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(22.dp))
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = article.title ?: "No Title",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = article.description ?: "No Description",
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = article.content ?: "No Content Available",
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}












