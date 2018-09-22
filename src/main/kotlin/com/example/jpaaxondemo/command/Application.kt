package com.example.jpaaxondemo.command

import com.example.jpaaxondemo.core.ApplicationCreatedEvent
import com.example.jpaaxondemo.core.CreateApplicationCommand
import com.example.jpaaxondemo.core.PersonalDetailsSubmittedEvent
import com.example.jpaaxondemo.core.SubmitPersonalDetailsCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.LoggerFactory
import java.io.Serializable

@Aggregate
class Application() : Serializable {
    companion object {
        private val logger = LoggerFactory.getLogger(Application::class.java)
    }

    @AggregateIdentifier
    lateinit var id: String

    var familyName: String? = null
    var givenNames: String? = null

    @CommandHandler
    constructor(command: CreateApplicationCommand): this() {
        AggregateLifecycle.apply(ApplicationCreatedEvent(command.id))
    }

    @CommandHandler
    fun handle(command: SubmitPersonalDetailsCommand) {
        AggregateLifecycle.apply(PersonalDetailsSubmittedEvent(command.applicationId, command.familyName, command.givenNames))
    }

    @EventSourcingHandler
    protected fun on(event: ApplicationCreatedEvent) {
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
