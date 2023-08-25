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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.gymie.ui.theme.GymieTheme


class LiveWorkoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            buildLiveWorkoutActivity()
        }
    }
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
    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Text(
                    text = "$time",
                    fontSize = 80.sp
                )
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

@Preview(showBackground = true)
@Composable
fun LiveWorkoutComposablePreview() {
    buildLiveWorkoutActivity()
}