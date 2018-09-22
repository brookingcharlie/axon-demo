package com.example.jpaaxondemo.command

import com.example.jpaaxondemo.core.CreateApplicationCommand
import com.example.jpaaxondemo.core.SubmitPersonalDetailsCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Future


@RestController
class CommandController(@Autowired val commandGateway: CommandGateway) {
    @PostMapping("/applications")
    fun create(@RequestBody body: CreateApplicationRequest): Future<Void> {
        return commandGateway.send(CreateApplicationCommand(body.id))
    }

    @PostMapping("/applications/{applicationId}/personal-details")
    fun submitPersonalDetails(@PathVariable applicationId: String, @RequestBody body: SubmitPersonalDetailsRequest): Future<Void> {
        return commandGateway.send(SubmitPersonalDetailsCommand(applicationId, body.familyName, body.givenNames))
    }

    data class CreateApplicationRequest(val id: String)
    data class SubmitPersonalDetailsRequest(val familyName: String, val givenNames: String)
}
