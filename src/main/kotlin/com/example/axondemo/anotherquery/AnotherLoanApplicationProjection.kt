package com.example.axondemo.anotherquery

import com.example.axondemo.core.LoanApplicationCreatedEvent
import com.example.axondemo.core.PersonalDetailsSubmittedEvent
import org.axonframework.eventhandling.EventHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AnotherLoanApplicationProjection(@Autowired private val repository: AnotherLoanApplicationViewRepository) {
    companion object {
        private val logger = LoggerFactory.getLogger(AnotherLoanApplicationProjection::class.java)
    }

    @EventHandler
    fun on(event: LoanApplicationCreatedEvent) {
        logger.debug("Handling ${event}")
        repository.save(AnotherLoanApplicationView(event.id))
    }

    @EventHandler
    fun on(event: PersonalDetailsSubmittedEvent) {
        logger.debug("Handling ${event}")
        val application = repository.getOne(event.applicationId)
        application.name = "${event.givenNames} ${event.familyName}"
    }
}
