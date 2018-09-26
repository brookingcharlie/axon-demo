package com.example.axondemo.command

import com.example.axondemo.core.DocumentAttachedEvent
import com.example.axondemo.core.LoanApplicationCreatedEvent
import com.example.axondemo.core.PersonalDetailsSubmittedEvent
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle.apply
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

    constructor(id: String) : this() {
        apply(LoanApplicationCreatedEvent(id))
    }

    fun setPersonalDetails(familyName: String, givenNames: String) {
        apply(PersonalDetailsSubmittedEvent(this.id, familyName, givenNames))
    }

    fun attachDocument(id: String, name: String, content: String) {
        apply(DocumentAttachedEvent(this.id, id, name, content))
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
