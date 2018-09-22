package com.example.jpaaxondemo.core

import org.axonframework.commandhandling.TargetAggregateIdentifier

data class CreateApplicationCommand(@TargetAggregateIdentifier val id: String)
