package com.example.axondemo.core

import org.axonframework.commandhandling.TargetAggregateIdentifier

data class CreateLoanApplicationCommand(
    @TargetAggregateIdentifier val id: String
)

data class SubmitPersonalDetailsCommand(
    @TargetAggregateIdentifier val applicationId: String,
    val familyName: String,
    val givenNames: String
)

data class AttachDocumentCommand(
    @TargetAggregateIdentifier val applicationId: String,
    val id: String,
    val name: String,
    val content: String
)