package com.example.axondemo.query

import com.example.axondemo.domain.ApplicationCreated
import com.example.axondemo.domain.ApplicationSubmitted
import com.example.axondemo.domain.CourseSelected
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ApplicationProjection(@Autowired private val repository: ApplicationViewRepository) {
    @EventHandler
    fun project(event: ApplicationCreated) {
        logger.debug("project($event)")
        repository.save(ApplicationView().apply { id = event.applicationId; customer = event.customer })
    }

    @EventHandler
    fun project(event: CourseSelected) {
        logger.debug("project($event)")
        if (event.course == "Science") {
            throw Exception("Deliberate failure to test transactions")
        }
        repository.getOne(event.applicationId).apply { course = event.course }
    }

    @EventHandler
    fun project(event: ApplicationSubmitted) {
        logger.debug("project($event)")
        repository.getOne(event.applicationId).apply { submitted = true }
    }

    @QueryHandler
    fun handle(query: ApplicationsQuery): ApplicationsResponse {
        logger.debug("handle($query)")
        return ApplicationsResponse(repository.findAll())
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ApplicationProjection::class.java)
    }
}