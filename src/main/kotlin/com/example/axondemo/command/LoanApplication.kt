package com.example.axondemo.command

import com.example.axondemo.core.LoanApplicationCreatedEvent
import com.example.axondemo.core.CreateLoanApplicationCommand
import com.example.axondemo.core.PersonalDetailsSubmittedEvent
import com.example.axondemo.core.SubmitPersonalDetailsCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.LoggerFactory
import java.io.Serializable

@Aggregate
class LoanApplication() : Serializable {
    companion object {
        private val logger = LoggerFactory.getLogger(LoanApplication::class.java)
    }

    @AggregateIdentifier
    lateinit var id: String

    var familyName: String? = null
    var givenNames: String? = null

    @CommandHandler
    constructor(command: CreateLoanApplicationCommand): this() {
        AggregateLifecycle.apply(LoanApplicationCreatedEvent(command.id))
    }

    @CommandHandler
    fun handle(command: SubmitPersonalDetailsCommand) {
        AggregateLifecycle.apply(PersonalDetailsSubmittedEvent(command.applicationId, command.familyName, command.givenNames))
    }

    @EventSourcingHandler
    protected fun on(event: LoanApplicationCreatedEvent) {
        logger.debug("Applying ${event}")
        this.id = event.id
    }

    @EventSourcingHandler
    protected fun on(event: PersonalDetailsSubmittedEvent) {
        logger.debug("Applying ${event}")
        this.familyName = event.familyName
        this.givenNames = event.givenNames
    }
}
