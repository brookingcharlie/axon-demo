package com.example.axondemo.api

import com.example.axondemo.query.ApplicationView
import com.example.axondemo.query.ApplicationsQuery
import com.example.axondemo.query.ApplicationsResponse
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QueryController(@Autowired val queryGateway: QueryGateway) {
    @GetMapping("/applications")
    fun getAllApplications(): List<ApplicationView> =
        queryGateway.query(ApplicationsQuery(), ApplicationsResponse::class.java).get().applications
}