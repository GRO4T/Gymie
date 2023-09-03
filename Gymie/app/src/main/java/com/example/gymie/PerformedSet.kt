package com.example.gymie

/**
 * Single perfomed set of an exercise, e.g. 12 reps of squats with 50kg barbell
 */
data class PerformedSet(
    val exercise: String = "",
    val repCount: Int = 0,
    val weight: Double = 0.0,
    val unit: String = "kg"
)
