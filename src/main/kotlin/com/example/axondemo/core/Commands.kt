package com.example.axondemo.core

import org.axonframework.commandhandling.TargetAggregateIdentifier

data class CreateApplicationCommand(
    @TargetAggregateIdentifier val id: String
)

data class SubmitPersonalDetailsCommand(
    @TargetAggregateIdentifier val applicationId: String,
    val familyName: String,
    val givenNames: String
)