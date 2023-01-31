package com.example.bootcampcompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Header() {
    Surface(color = Color(230, 233, 246)) {
        Column(
            modifier = Modifier.padding(34.dp, 35.dp, 34.dp, 21.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Explore",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600,
                    color = Color(0, 0, 0),
                    modifier = Modifier.padding(start = 15.dp)
                )
                Text(
                    text = "Filter",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(0XFF5DB075)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            SearchBar()
        }
    }
}

@Composable
fun SearchBar(){
    TextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(50))
            .border(BorderStroke(width = 1.dp, color = Color(232, 232, 232))),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(246, 246, 246),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        singleLine = true,
        placeholder = {
            Text(text="Search",
                modifier = Modifier.alpha(ContentAlpha.medium)
            )
        }
    )
}

@Composable
fun SearchBarExp(
    text:String,
    onTextChange:(String)->Unit,
    onCloseClicked:()->Unit,
    onSearchClicked:(String)->Unit
){
    TextField(
        value = text,
        onValueChange = {onTextChange(it)},
        modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .clip(RoundedCornerShape(50))
        .border(BorderStroke(width = 1.dp, color = Color(232, 232, 232))),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(246, 246, 246),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        singleLine = true,
        placeholder = {
            Text(text="Search",
                modifier = Modifier.alpha(ContentAlpha.medium)
            )
        },
        //KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        //KeyboardActions = KeyboardActions(onSearch = {onSearchClicked(text)})
    )
}

@Preview
@Composable
fun ihatemyself(){
    SearchBarExp("",{},{},{})
}