package com.example.axondemo.query

import com.example.axondemo.core.DocumentAttachedEvent
import com.example.axondemo.core.LoanApplicationCreatedEvent
import com.example.axondemo.core.PersonalDetailsSubmittedEvent
import org.axonframework.eventhandling.EventHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LoanApplicationProjection(@Autowired private val repository: LoanApplicationViewRepository) {
    companion object {
        private val logger = LoggerFactory.getLogger(LoanApplicationProjection::class.java)
    }

    @EventHandler
    fun on(event: LoanApplicationCreatedEvent) {
        logger.debug("Handling ${event}")
        repository.save(LoanApplicationView(event.id))
    }

    @EventHandler
    fun on(event: PersonalDetailsSubmittedEvent) {
        logger.debug("Handling ${event}")
        val application = repository.getOne(event.applicationId)
        application.familyName = event.familyName
        application.givenNames = event.givenNames
    }

    @EventHandler
    fun on(event: DocumentAttachedEvent) {
        logger.debug("Handling ${event}")
        val application = repository.getOne(event.applicationId)
        application.documents.add(DocumentView(application = application, id = event.id, name = event.name, content = event.content))
    }
}
