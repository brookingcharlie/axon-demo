package com.example.axondemo.domain

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateApplication(
        val customer: String
)

data class SelectCourse(
        @TargetAggregateIdentifier val applicationId: String,
        val course: String,
        val customer: String
)

data class SubmitApplication(
        @TargetAggregateIdentifier val applicationId: String,
        val customer: String
)