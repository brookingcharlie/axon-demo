package com.example.axondemo.domain

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.common.IdentifierFactory
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.LoggerFactory

@Aggregate
class Application {
    @AggregateIdentifier lateinit var id: String
    lateinit var customer: String
    var course: String? = null
    var submitted: Boolean = false

    @CommandHandler
    constructor(command: CreateApplication) {
        logger.debug("handle($command)")
        val id = IdentifierFactory.getInstance().generateIdentifier()
        apply(ApplicationCreated(id, command.customer))
    }

    @CommandHandler
    fun handle(command: SelectCourse) {
        logger.debug("handle($command)")
        if (this.customer != command.customer) {
            throw InvalidCustomerException("Only the applicant can select a course")
        }
        apply(CourseSelected(this.id, command.course))
    }

    @CommandHandler
    fun handle(command: SubmitApplication) {
        logger.debug("handle($command)")
        if (this.customer != command.customer) {
            throw InvalidCustomerException("Only the applicant can submit their application")
        }
        apply(ApplicationSubmitted(this.id))
    }

    constructor() {
        logger.debug("constructor()")
    }

    @EventSourcingHandler
    fun source(event: ApplicationCreated) {
        logger.debug("source($event)")
        this.id = event.applicationId
        this.customer = event.customer
    }

    @EventSourcingHandler
    fun source(event: CourseSelected) {
        logger.debug("source($event)")
        this.course = event.course
    }

    @EventSourcingHandler
    fun source(event: ApplicationSubmitted) {
        logger.debug("source($event)")
        this.submitted = true
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Application::class.java)
    }
}