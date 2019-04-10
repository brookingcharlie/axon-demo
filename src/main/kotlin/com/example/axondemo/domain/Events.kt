package com.example.axondemo.domain

data class ApplicationCreated(
        val applicationId: String,
        val customer: String
)

data class CourseSelected(
        val applicationId: String,
        val course: String
)

data class ApplicationSubmitted(
        val applicationId: String
)