package com.example.bootcampcompose.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bootcampcompose.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Init(itemList: List<Item>) {
    val scope = rememberCoroutineScope()
    val pageState = rememberPagerState(initialPage = 0)

    Scaffold(
        bottomBar = {
            Surface(modifier = Modifier.height(90.dp))
            {
                BottomAppBar(
                    backgroundColor = Color(250, 250, 250),
                    content = {
                        for (page in 0..4) {
                            BottomNavigationItem(
                                selected = page == pageState.currentPage,
                                onClick = {
                                    scope.launch {
                                        pageState.animateScrollToPage(page = page)
                                    }
                                },
                                icon = {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(shape = CircleShape)
                                            .background(
                                                if (page == pageState.currentPage)
                                                    Color(93, 176, 117)
                                                else
                                                    Color(232, 232, 232)
                                            )
                                    )
                                },
                            )
                        }
                    }
                )
            }
        },
        topBar = { Header() }
    ) { padding ->
        HorizontalPager(count = 5, state = pageState) { it ->
            when (it) {
                0 -> listView(itemList, modifier = Modifier.padding(padding))
                1 -> gridView(itemList, modifier = Modifier.padding(padding))
                2 -> activity3(modifier = Modifier.padding(padding))
                3 -> activity4(modifier = Modifier.padding(padding))
                4 -> activity5(modifier = Modifier.padding(padding))
            }
        }

    }
}
