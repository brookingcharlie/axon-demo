package com.example.axondemo.command

import com.example.axondemo.core.*
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

    class Document(val id: String, val name: String, val content: String)

    @AggregateIdentifier
    lateinit var id: String

    var familyName: String? = null
    var givenNames: String? = null

    var documents: MutableList<Document> = mutableListOf()

    @CommandHandler
    constructor(command: CreateLoanApplicationCommand): this() {
        AggregateLifecycle.apply(LoanApplicationCreatedEvent(command.id))
        logger.debug("Finished processing $command")
    }

    @CommandHandler
    fun handle(command: SubmitPersonalDetailsCommand) {
        AggregateLifecycle.apply(PersonalDetailsSubmittedEvent(this.id, command.familyName, command.givenNames))
        logger.debug("Finished processing $command")
    }

    @CommandHandler
    fun handle(command: AttachDocumentCommand) {
        AggregateLifecycle.apply(DocumentAttachedEvent(this.id, command.id, command.name, command.content))
        logger.debug("Finished processing $command")
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

    @EventSourcingHandler
    protected fun on(event: DocumentAttachedEvent) {
        logger.debug("Applying ${event}")
        this.documents.add(Document(event.id, event.name, event.content))
    }
}
