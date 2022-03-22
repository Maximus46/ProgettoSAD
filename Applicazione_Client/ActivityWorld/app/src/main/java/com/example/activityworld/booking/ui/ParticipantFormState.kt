package com.example.activityworld.booking.ui

data class ParticipantFormState(
    val nameError: Int? = null,
    val surnameError: Int? = null,
    val isDataValid: Boolean = false
)