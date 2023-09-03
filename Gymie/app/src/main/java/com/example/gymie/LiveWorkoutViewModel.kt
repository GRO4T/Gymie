package com.example.gymie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LiveWorkoutViewModel: ViewModel() {
    private val _performedSetList = MutableStateFlow(mutableListOf<PerformedSet>())
    val performedSetList: StateFlow<MutableList<PerformedSet>> = _performedSetList.asStateFlow()

    var exercise by mutableStateOf("")
        private set

    fun updateExercise(input: String) {
        exercise = input
    }

    fun finishSet() {
        _performedSetList.value.add(PerformedSet(exercise = exercise))
        exercise = ""
    }
}