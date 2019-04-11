package com.example.axondemo.api

import com.example.axondemo.tracker.ApplicationView2
import com.example.axondemo.tracker.ApplicationsQuery2
import com.example.axondemo.tracker.ApplicationsResponse2
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QueryController2(@Autowired val queryGateway: QueryGateway) {
    @GetMapping("/applications2")
    fun getApplications2(): List<ApplicationView2> {
        logger.debug("request(getApplications2())")
        return queryGateway.query(ApplicationsQuery2(), ApplicationsResponse2::class.java).get().applications
    }

    companion object {
        private val logger = LoggerFactory.getLogger(QueryController2::class.java)
    }
}