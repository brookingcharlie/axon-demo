package com.example.axondemo.api

import com.example.axondemo.tracker.ApplicationView2
import com.example.axondemo.tracker.ApplicationsQuery2
import com.example.axondemo.tracker.ApplicationsResponse2
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QueryController2(@Autowired val queryGateway: QueryGateway) {
    @GetMapping("/applications2")
    fun getAllApplications2(): List<ApplicationView2> =
        queryGateway.query(ApplicationsQuery2(), ApplicationsResponse2::class.java).get().applications
}