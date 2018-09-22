package com.example.axondemo.core

data class LoanApplicationCreatedEvent(
    val id: String
)

data class PersonalDetailsSubmittedEvent(
    val applicationId: String,
    val familyName: String,
    val givenNames: String
)

data class DocumentAttachedEvent(
    val applicationId: String,
    val id: String,
    val name: String,
    val content: String
)