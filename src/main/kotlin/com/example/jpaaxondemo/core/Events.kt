package com.example.jpaaxondemo.core

data class ApplicationCreatedEvent(
    val id: String
)

data class PersonalDetailsSubmittedEvent(
    val applicationId: String,
    val familyName: String,
    val givenNames: String
)