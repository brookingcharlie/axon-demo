package com.example.axondemo.tracker

import com.example.axondemo.domain.ApplicationCreated
import com.example.axondemo.domain.ApplicationSubmitted
import com.example.axondemo.domain.CourseSelected
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ApplicationProjection2(@Autowired private val repository: ApplicationViewRepository2) {
    @EventHandler
    fun on(event: ApplicationCreated) {
        logger.debug("on($event)")
        repository.save(ApplicationView2().apply { id = event.applicationId; customer = event.customer })
    }

    @EventHandler
    fun on(event: CourseSelected) {
        logger.debug("on($event)")
        repository.getOne(event.applicationId).apply { course = event.course }
    }

    @EventHandler
    fun on(event: ApplicationSubmitted) {
        logger.debug("on($event)")
        throw Exception("Deliberate submitted failure to test transactions")
    }

    @QueryHandler
    fun handle(query: ApplicationsQuery2): ApplicationsResponse2 {
        logger.debug("handle($query)")
        return ApplicationsResponse2(repository.findAll())
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ApplicationProjection2::class.java)
    }
}