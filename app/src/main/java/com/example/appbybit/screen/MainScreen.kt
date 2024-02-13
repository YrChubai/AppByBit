
package com.example.appbybit.screen


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appbybit.AnnouncementsViewModel
import com.example.appbybit.data.models.ModifAnnouncementsItem
import com.example.appbybit.ui.theme.BtnYellow
import com.example.appbybit.ui.theme.GreyBorder
import com.example.appbybit.ui.theme.GreyTag


@Composable
fun MainScreen(navController: NavController = rememberNavController(), viewModel: AnnouncementsViewModel = viewModel()
) {

    val dataLoaded = remember {
        viewModel.dataLoaded
    }
        when(dataLoaded.value){
            "start" -> LoadedScreen()
            "loaded" -> AnnouncementsScreen()
            "error" -> ErrorScreen()
        }
}
@Composable
fun AnnouncementsScreen(viewModel: AnnouncementsViewModel = viewModel()
) {
    val ListLoaded = remember {
        viewModel.liveDataList
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        itemsIndexed(
            ListLoaded.value
        ) { _, item ->
            ItemCard(item)
        }
    }
}
@Composable
fun ErrorScreen(viewModel: AnnouncementsViewModel = viewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment =  Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                    .fillMaxSize()

        ) {
            Text(text = "Error! Please click on Refresh Button", fontSize = 20.sp)
            Button(
            onClick = { viewModel.loadData() },
            colors = ButtonDefaults.buttonColors(
                containerColor = BtnYellow,
                contentColor = Color.Black
            )
        ) {
            Text(text = "Refresh", fontSize = 20.sp)
        }
        }
    }
}

@Composable
fun ItemCard(daysList: ModifAnnouncementsItem
) {
    val handlerOpener = LocalUriHandler.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
                containerColor = Color.White)
    ){
        Box(
            modifier = Modifier
            .fillMaxWidth()
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(text = daysList.dateTimestamp)
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(text = daysList.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Row {
                        for (i in 0..daysList.tags.size-1){
                            Text(text = daysList.tags[i],
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = GreyTag,
                                modifier = Modifier
                                    .border(2.dp, GreyBorder, RoundedCornerShape(5.dp))
                                    .padding(4.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(text = daysList.description)
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(text = "Event Period:")
                    Row {

                        Text(text = daysList.startDateTimestamp)
                        Text(text = " - ")
                        Text(text = daysList.endDateTimestamp)
                    }
                    Spacer(modifier = Modifier.height(7.dp))
                    Row ( horizontalArrangement = Arrangement.End, modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 15.dp))
                    {
                        Button(onClick = { handlerOpener.openUri(daysList.url) }, colors = ButtonDefaults.buttonColors(containerColor = BtnYellow, contentColor = Color.Black)) {
                            Text(text = "Open")
                        }
                    }
                }
            }
        }
    }
}