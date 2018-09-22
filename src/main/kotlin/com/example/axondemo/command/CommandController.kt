package com.example.axondemo.command

import com.example.axondemo.core.CreateLoanApplicationCommand
import com.example.axondemo.core.SubmitPersonalDetailsCommand
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
    fun create(@RequestBody body: CreateLoanApplicationRequest): Future<Void> {
        return commandGateway.send(CreateLoanApplicationCommand(body.id))
    }

    @PostMapping("/applications/{applicationId}/personal-details")
    fun submitPersonalDetails(@PathVariable applicationId: String, @RequestBody body: SubmitPersonalDetailsRequest): Future<Void> {
        return commandGateway.send(SubmitPersonalDetailsCommand(applicationId, body.familyName, body.givenNames))
    }

    data class CreateLoanApplicationRequest(val id: String)
    data class SubmitPersonalDetailsRequest(val familyName: String, val givenNames: String)
}
