package com.example.axondemo.command

import com.example.axondemo.core.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.LoggerFactory
import java.io.Serializable
import javax.persistence.*

@Aggregate
@Entity(name = "application")
class LoanApplication() : Serializable {
    companion object {
        private val logger = LoggerFactory.getLogger(LoanApplication::class.java)
    }

    @Id
    @AggregateIdentifier
    lateinit var id: String

    var familyName: String? = null
    var givenNames: String? = null

    @OneToMany(cascade = [CascadeType.ALL], mappedBy="application")
    var documents: MutableList<Document> = mutableListOf()

    @CommandHandler
    constructor(command: CreateLoanApplicationCommand): this() {
        this.id = command.id
        AggregateLifecycle.apply(LoanApplicationCreatedEvent(command.id))
        logger.debug("Finished processing $command")
    }

    @CommandHandler
    fun handle(command: SubmitPersonalDetailsCommand) {
        this.familyName = command.familyName
        this.givenNames = command.givenNames
        AggregateLifecycle.apply(PersonalDetailsSubmittedEvent(this.id, command.familyName, command.givenNames))
        logger.debug("Finished processing $command")
    }

    @CommandHandler
    fun handle(command: AttachDocumentCommand) {
        this.documents.add(Document(this, command.id, command.name, command.content))
        AggregateLifecycle.apply(DocumentAttachedEvent(this.id, command.id, command.name, command.content))
        logger.debug("Finished processing $command")
    }
}

@Entity(name = "document")
class Document() {
    @ManyToOne
    lateinit var application: LoanApplication

    @Id
    lateinit var id: String

    lateinit var name: String

    lateinit var content: String

    constructor(application: LoanApplication, id: String, name: String, content: String) : this() {
        this.application = application
        this.id = id
        this.name = name
        this.content = content
    }
}
