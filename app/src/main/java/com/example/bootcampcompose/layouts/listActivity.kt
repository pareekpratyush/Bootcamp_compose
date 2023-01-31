package com.example.bootcampcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun listView(itemList: List<Item>, modifier:Modifier=Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 34.dp, end = 34.dp),
        contentPadding = PaddingValues(top = 26.dp)
    ) {
        items(itemList) { item ->
            listItem(item)
        }
    }
}

@Composable
private fun listItemImage(img: Int) {
    Image(
        painter = painterResource(id = img),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(corner = CornerSize(8.dp)))
    )
}


@Composable
fun listItem(item: Item) {
    val pic = item.image
    val name = item.name
    val price = item.price
    val sameDayShipping = item.sds
    Surface() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 13.dp)
        ) {
            listItemImage(pic)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
            ) {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = Color(0, 0, 0)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "MRP: ",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400,
                            color = Color(163, 163, 163)
                        )
                        Text(
                            text = "\u20B9" + price.toString(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400,
                            color = Color(0, 0, 0)
                        )
                    }
                    Text(
                        modifier = Modifier.weight(1f),
                        text = if (sameDayShipping) "Same Day Shipping" else "",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = Color(163, 163, 163)
                    )
                }
                Divider(
                    modifier = Modifier.padding(top = 14.dp),
                    color = Color(232, 232, 232),
                    thickness = 1.dp
                )
            }
        }
    }

}
