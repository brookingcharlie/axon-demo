package com.example.axondemo.query

import com.example.axondemo.core.DocumentAttachedEvent
import com.example.axondemo.core.LoanApplicationCreatedEvent
import com.example.axondemo.core.PersonalDetailsSubmittedEvent
import org.axonframework.eventhandling.EventHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LoanApplicationProjection(@Autowired private val repository: LoanApplicationRepository) {
    companion object {
        private val logger = LoggerFactory.getLogger(LoanApplicationProjection::class.java)
    }

    @EventHandler
    fun on(event: LoanApplicationCreatedEvent) {
        logger.debug("Handling ${event}")
    }

    @EventHandler
    fun on(event: PersonalDetailsSubmittedEvent) {
        logger.debug("Handling ${event}")
    }

    @EventHandler
    fun on(event: DocumentAttachedEvent) {
        logger.debug("Handling ${event}")
    }
}
