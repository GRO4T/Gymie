package com.example.gymie

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.gymie.ui.theme.GymieTheme
import kotlinx.coroutines.delay

const val TAG = "LiveWorkoutActivity";

@Composable
internal fun LiveWorkoutRoute() {
    LiveWorkoutScreen(LiveWorkoutViewModel())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LiveWorkoutScreen(viewModel: LiveWorkoutViewModel) {
    var elapsedTimeInSeconds by remember { mutableStateOf(0L) }

    LaunchedEffect(key1 = true) {
        while (true) {
            delay(1000)
            elapsedTimeInSeconds++
        }
    }

    GymieTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Surface() {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row() {
                        TimerText(elapsedTimeInSeconds)
                    }
                    Row() {
                        ExerciseInput(viewModel)
                    }
                    Row() {
                        Column() {
                            Row() {
                                TextField(
                                    modifier = Modifier
                                        .width(150.dp)
                                        .padding(10.dp),
                                    value = viewModel.weight,
                                    onValueChange = { value ->
                                        if (value.length <= 3) {
                                            viewModel.updateWeight(value.filter { it.isDigit() })
                                        }
                                    },
                                    label = { Text("Weight [kg]") },
                                )
                            }
                        }
                        Column() {
                            Row() {
                                TextField(
                                    modifier = Modifier
                                        .width(100.dp)
                                        .padding(10.dp),
                                    value = viewModel.repCount,
                                    onValueChange = { value ->
                                        if (value.length <= 3) {
                                            viewModel.updateRepCount(value.filter { it.isDigit() })
                                        }
                                    },
                                    label = { Text("Reps") },
                                )
                            }
                        }
                    }
                    Row() {
                        Button(onClick = {
                            viewModel.finishSet()
                        }) {
                            Text(
                                text = "+",
                                fontSize = 80.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TimerText(elapsedTimeInSeconds: Long) {
    val minutes = elapsedTimeInSeconds / 60
    val seconds = elapsedTimeInSeconds % 60
    val formattedTime = String.format("%02d:%02d", minutes, seconds)

    Text(
        text = formattedTime,
        fontSize = 80.sp,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseInput(viewModel: LiveWorkoutViewModel) {
    var exerciseList = listOf("Pull up", "Push up", "Squat", "Deadlift")
    var isSuggestionsVisible by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    Column {
        TextField(
            modifier = Modifier
                .width(300.dp)
                .onGloballyPositioned { coordinates -> textFieldSize = coordinates.size.toSize() },
            value = viewModel.exercise,
            onValueChange = {
                isSuggestionsVisible = !exerciseList.contains(viewModel.exercise)
                viewModel.updateExercise(it)
            },
            label = { Text("Exercise") },
        )

        AnimatedVisibility(visible = isSuggestionsVisible) {
            Card(
                modifier = Modifier
                    .width(300.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 15.dp
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                LazyColumn() {
                    items(exerciseList.filter {
                        it.lowercase().contains(viewModel.exercise.lowercase())
                    }.sorted()) {
                        CategoryItems(title = it) { exercise ->
                            viewModel.updateExercise(exercise)
                            isSuggestionsVisible = false
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryItems(
    title: String,
    onSelect: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(title)
            }
            .padding(10.dp)
    ) {
        Text(text = title, fontSize = 16.sp)
    }

}
