package com.example.axondemo.api

import com.example.axondemo.domain.CreateApplication
import com.example.axondemo.domain.SelectCourse
import com.example.axondemo.domain.SubmitApplication
import org.axonframework.commandhandling.gateway.CommandGateway
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class CommandController(@Autowired val commandGateway: CommandGateway) {
    @PostMapping("/applications")
    fun create(@RequestHeader("Authorization") customer: String): CreateApplicationResponse {
        logger.debug("request(create($customer))")
        val id = commandGateway.sendAndWait<String>(CreateApplication(customer))
        return CreateApplicationResponse(id)
    }

    @PostMapping("/applications/{applicationId}/course")
    fun selectCourse(
            @RequestHeader("Authorization") customer: String,
            @PathVariable("applicationId") applicationId: String,
            @RequestBody request: SelectCourseRequest
    ) {
        logger.debug("request(selectCourse($customer, $applicationId, $request))")
        commandGateway.sendAndWait<Void>(SelectCourse(applicationId, request.course, customer))
    }

    @PostMapping("/applications/{applicationId}/submit")
    fun submit(
            @RequestHeader("Authorization") customer: String,
            @PathVariable("applicationId") applicationId: String
    ) {
        logger.debug("request(submit($customer, $applicationId))")
        commandGateway.sendAndWait<Void>(SubmitApplication(applicationId, customer))
    }

    data class SelectCourseRequest(val course: String)
    data class CreateApplicationResponse(val id: String)

    companion object {
        private val logger = LoggerFactory.getLogger(CommandController::class.java)
    }
}
