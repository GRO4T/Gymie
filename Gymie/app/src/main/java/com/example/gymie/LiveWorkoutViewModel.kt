package com.example.gymie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LiveWorkoutViewModel : ViewModel() {
    private val _performedSetList = MutableStateFlow(mutableListOf<PerformedSet>())
    val performedSetList: StateFlow<MutableList<PerformedSet>> = _performedSetList.asStateFlow()

    var exercise by mutableStateOf("")
        private set

    var weight by mutableStateOf("0")
        private set

    var repCount by mutableStateOf("0")
        private set

    fun updateExercise(input: String) {
        exercise = input
    }

    fun updateWeight(input: String) {
        weight = input
    }

    fun updateRepCount(input: String) {
        repCount = input
    }

    fun finishSet() {
        if (exercise.isEmpty() || weight.isEmpty() || repCount.isEmpty())
            return

        _performedSetList.value.add(
            PerformedSet(
                exercise = exercise,
                weight = weight.toDouble(),
                repCount = repCount.toInt()
            )
        )
        exercise = ""
        weight = "0"
        repCount = "0"
    }
}