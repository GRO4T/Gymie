package com.example.gymie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.gymie.ui.theme.GymieTheme
import kotlinx.coroutines.delay


class LiveWorkoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            buildLiveWorkoutActivity()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LiveWorkoutComposablePreview() {
    buildLiveWorkoutActivity()
}

@Composable
fun buildLiveWorkoutActivity(): Unit {
    return GymieTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            LiveWorkoutComposable("00:00")
        }
    }
}

@Composable
fun LiveWorkoutComposable(time: String) {
    var elapsedTimeInSeconds by remember { mutableStateOf(0L) }

    LaunchedEffect(key1 = true) {
        while (true) {
            delay(1000)
            elapsedTimeInSeconds++
        }
    }

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
                Text(
                    text = "chin ups",
                    fontSize = 30.sp
                )
            }
            Row() {
                Column() {
                    Text(
                        text = "0kg",
                        fontSize = 40.sp
                    )
                }
                Column() {
                    Text(
                        text = "12 reps",
                        fontSize = 40.sp
                    )
                }
            }
            Row() {
                Text(
                    text = "+",
                    fontSize = 80.sp
                )
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
