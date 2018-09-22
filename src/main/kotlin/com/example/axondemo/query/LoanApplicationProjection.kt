package com.example.axondemo.query

import com.example.axondemo.core.LoanApplicationCreatedEvent
import com.example.axondemo.core.PersonalDetailsSubmittedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LoanApplicationProjection(@Autowired private val repository: LoanApplicationViewRepository) {
    @EventHandler
    fun on(event: LoanApplicationCreatedEvent) {
        repository.save(LoanApplicationView(event.id))
    }

    @EventHandler
    fun on(event: PersonalDetailsSubmittedEvent) {
        val application = repository.getOne(event.applicationId)
        application.familyName = event.familyName
        application.givenNames = event.givenNames
    }
}
