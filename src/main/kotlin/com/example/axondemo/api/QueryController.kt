package com.example.axondemo.api

import com.example.axondemo.query.ApplicationView
import com.example.axondemo.query.ApplicationsQuery
import com.example.axondemo.query.ApplicationsResponse
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QueryController(@Autowired val queryGateway: QueryGateway) {
    @GetMapping("/applications")
    fun getApplications(): List<ApplicationView> {
        logger.debug("request(getApplications())")
        return queryGateway.query(ApplicationsQuery(), ApplicationsResponse::class.java).get().applications
    }

    companion object {
        private val logger = LoggerFactory.getLogger(QueryController::class.java)
    }
}