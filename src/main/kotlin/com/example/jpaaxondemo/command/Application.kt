package com.example.jpaaxondemo.command

import com.example.jpaaxondemo.core.ApplicationCreatedEvent
import com.example.jpaaxondemo.core.CreateApplicationCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate
import java.io.Serializable

@Aggregate
class Application() : Serializable {
    @AggregateIdentifier
    lateinit var id: String

    @CommandHandler
    constructor(command: CreateApplicationCommand): this() {
        AggregateLifecycle.apply(ApplicationCreatedEvent(command.id))
    }

    @EventSourcingHandler
    protected fun on(event: ApplicationCreatedEvent){
        this.id = event.id
    }
}
