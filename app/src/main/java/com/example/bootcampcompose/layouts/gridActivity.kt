package com.example.bootcampcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun gridView(itemList: List<Item>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 32.dp, end = 32.dp),
        contentPadding = PaddingValues(top = 39.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(29.dp),
        horizontalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        items(itemList) { item ->
            gridItem(item)
        }
    }
}

@Composable
private fun gridItemImage(img: Int) {
    Image(
        painter = painterResource(id = img),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(110.dp)
            .clip(RoundedCornerShape(corner = CornerSize(8.dp)))
    )
}


@Composable
fun gridItem(item: Item) {
    Surface() {
        val pic = item.image
        val name = item.name
        val price = item.price
        val sameDayShipping = item.sds

        Column() {
            gridItemImage(pic)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                color = Color(0, 0, 0)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "\u20B9" + price.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                color = Color(0, 0, 0)
            )
        }

    }
}

@Preview
@Composable
private fun preview(){
    gridView(listOf(
        Item("Item 1", R.drawable.img, 100.00, true),
        Item("Item 2", R.drawable.img, 50.00, false),
        Item("Item 1", R.drawable.img, 100.00, true),
        Item("Item 2", R.drawable.img, 50.00, false),
        Item("Item 1", R.drawable.img, 100.00, true),
        Item("Item 2", R.drawable.img, 50.00, false),
        Item("Item 1", R.drawable.img, 100.00, true),
        Item("Item 2", R.drawable.img, 50.00, false),
        Item("Item 1", R.drawable.img, 100.00, true),
        Item("Item 2", R.drawable.img, 50.00, false),
        Item("Item 1", R.drawable.img, 100.00, true),
        Item("Item 2", R.drawable.img, 50.00, false)
        )
    )
}
