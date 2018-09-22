package com.example.jpaaxondemo.command

import com.example.jpaaxondemo.core.CreateApplicationCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Future


@RestController
class CommandController(@Autowired val commandGateway: CommandGateway) {
    data class ApplicationCreateRequest(val id: String)

    @PostMapping("/applications")
    fun create(@RequestBody request: ApplicationCreateRequest): Future<Void> {
        return commandGateway.send(CreateApplicationCommand(request.id))
    }
}